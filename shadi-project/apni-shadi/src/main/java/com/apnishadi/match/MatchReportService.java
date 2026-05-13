package com.apnishadi.match;

import com.apnishadi.ai.AiAdvisorService;
import com.apnishadi.profile.Profile;
import com.apnishadi.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MatchReportService {
	private final ProfileRepository profileRepository;
	private final AiAdvisorService aiAdvisorService;

	public MatchReportService(ProfileRepository profileRepository, AiAdvisorService aiAdvisorService) {
		this.profileRepository = profileRepository;
		this.aiAdvisorService = aiAdvisorService;
	}

	public MatchReport createReport(Long profileOneId, Long profileTwoId) {
		MatchReport report = createCalculatedReport(profileOneId, profileTwoId);

		return new MatchReport(
				report.profileOne(),
				report.profileTwo(),
				report.overallScore(),
				report.confidence(),
				report.summary(),
				report.statistics(),
				report.strengths(),
				report.concerns(),
				report.questionsToAsk(),
				report.recommendation(),
				aiAdvisorService.writeNarrative(report),
				report.disclaimer()
		);
	}

	public MatchReport createCalculatedReport(Long profileOneId, Long profileTwoId) {
		Profile one = getProfile(profileOneId);
		Profile two = getProfile(profileTwoId);

		List<CompatibilityScore> scores = List.of(
				score("Location", locationScore(one, two), locationReason(one, two)),
				score("Lifestyle", lifestyleScore(one, two), lifestyleReason(one, two)),
				score("Family expectations", exactScore(one.getFamilyType(), two.getFamilyType(), 82, 62),
						"Compares nuclear/joint family comfort and day-to-day expectations."),
				score("Children plans", childrenScore(one, two), "Checks whether both profiles speak similarly about children."),
				score("Career alignment", careerScore(one, two), "Estimates whether both people appear career-focused and supportive."),
				score("Communication readiness", 78, "Both profiles include expectations, goals and personal context instead of only biodata.")
		);

		int overallScore = (int) Math.round(scores.stream()
				.mapToInt(CompatibilityScore::score)
				.average()
				.orElse(0));

		List<String> strengths = strengths(one, two);
		List<String> concerns = concerns(one, two);

		return new MatchReport(
				one,
				two,
				overallScore,
				overallScore >= 75 ? "Medium-high" : "Medium",
				summary(one, two, overallScore),
				scores,
				strengths,
				concerns,
				questions(one, two),
				recommendation(overallScore, concerns),
				null,
				"This report estimates compatibility from profile data. It cannot predict marriage success or replace family, legal, medical, financial or relationship counseling."
		);
	}

	public List<MatchCandidate> getMatchesForProfile(Long profileId) {
		Profile profileForMatching = getProfile(profileId);

		return profileRepository.findAll().stream()
				.filter(profile -> !profile.getId().equals(profileId))
				.filter(profile -> isOppositeGender(profileForMatching, profile))
				.map(profile -> createCalculatedReport(profileId, profile.getId()))
				.map(report -> new MatchCandidate(
						report.profileTwo(),
						report.overallScore(),
						report.confidence(),
						topReasons(report),
						report.recommendation()
				))
				.sorted(Comparator.comparingInt(MatchCandidate::overallScore).reversed())
				.toList();
	}

	private Profile getProfile(Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Profile not found"));
	}

	private static List<String> topReasons(MatchReport report) {
		List<String> reasons = new ArrayList<>();
		reasons.addAll(report.strengths().stream().limit(2).toList());
		if (!report.concerns().isEmpty()) {
			reasons.add("Discuss: " + report.concerns().getFirst());
		}
		return reasons;
	}

	private static CompatibilityScore score(String label, int score, String reason) {
		return new CompatibilityScore(label, clamp(score), reason);
	}

	private static int locationScore(Profile one, Profile two) {
		if (same(one.getCity(), two.getCity())) {
			return 94;
		}
		if (same(one.getState(), two.getState())) {
			return 84;
		}
		return one.getRelocation().toLowerCase().contains("open") || two.getRelocation().toLowerCase().contains("open") ? 74 : 56;
	}

	private static String locationReason(Profile one, Profile two) {
		if (same(one.getCity(), two.getCity())) {
			return "Both profiles are based in " + one.getCity() + ".";
		}
		return one.getFullName() + " is in " + one.getCity() + " and " + two.getFullName() + " is in " + two.getCity()
				+ ". Relocation expectations should be discussed early.";
	}

	private static int lifestyleScore(Profile one, Profile two) {
		int score = 55;
		score += exactScore(one.getDiet(), two.getDiet(), 15, 5);
		score += exactScore(one.getSmoking(), two.getSmoking(), 15, 3);
		score += exactScore(one.getDrinking(), two.getDrinking(), 15, 5);
		return score;
	}

	private static String lifestyleReason(Profile one, Profile two) {
		return "Diet: " + one.getDiet() + " / " + two.getDiet()
				+ ", smoking: " + one.getSmoking() + " / " + two.getSmoking()
				+ ", drinking: " + one.getDrinking() + " / " + two.getDrinking() + ".";
	}

	private static int childrenScore(Profile one, Profile two) {
		if (same(one.getWantsChildren(), two.getWantsChildren())) {
			return 90;
		}
		if ("unsure".equalsIgnoreCase(one.getWantsChildren()) || "unsure".equalsIgnoreCase(two.getWantsChildren())) {
			return 68;
		}
		return 45;
	}

	private static int careerScore(Profile one, Profile two) {
		int score = 70;
		if (containsAny(one.getLifeGoals(), "career", "leadership", "independent", "startup", "medicine")
				&& containsAny(two.getLifeGoals(), "career", "leadership", "independent", "startup", "medicine")) {
			score += 18;
		}
		if (containsAny(one.getPartnerExpectations(), "work", "career", "support")
				|| containsAny(two.getPartnerExpectations(), "work", "career", "support")) {
			score += 8;
		}
		return score;
	}

	private static List<String> strengths(Profile one, Profile two) {
		List<String> strengths = new ArrayList<>();
		if (same(one.getReligion(), two.getReligion())) {
			strengths.add("Shared religion can make rituals, festivals and family conversations easier.");
		}
		if (same(one.getCity(), two.getCity())) {
			strengths.add("Same city makes in-person meetings and family introductions easier.");
		}
		if (same(one.getSmoking(), two.getSmoking())) {
			strengths.add("Smoking preference is aligned.");
		}
		if (same(one.getWantsChildren(), two.getWantsChildren())) {
			strengths.add("Both profiles show similar intent about children.");
		}
		strengths.add("Both profiles describe life goals, which gives you useful topics for a serious pre-marriage conversation.");
		return strengths;
	}

	private static List<String> concerns(Profile one, Profile two) {
		List<String> concerns = new ArrayList<>();
		if (!same(one.getCity(), two.getCity())) {
			concerns.add("Different current cities: decide the first city after marriage before committing.");
		}
		if (!same(one.getFamilyType(), two.getFamilyType())) {
			concerns.add("Family setup differs: discuss joint family, nuclear family and parent-care responsibilities clearly.");
		}
		if (!same(one.getDiet(), two.getDiet())) {
			concerns.add("Food preferences differ: agree on kitchen expectations and comfort with each other's diet.");
		}
		if (!same(one.getWantsChildren(), two.getWantsChildren())) {
			concerns.add("Children plans are not fully aligned in the profiles.");
		}
		if (concerns.isEmpty()) {
			concerns.add("No major profile-level concern found. Still validate emotional fit, conflict style and family expectations in person.");
		}
		return concerns;
	}

	private static List<String> questions(Profile one, Profile two) {
		return List.of(
				"Which city would feel fair for the first two years after marriage?",
				"How often should parents or extended family be involved in major decisions?",
				"What does financial transparency mean for both of you: shared budget, separate accounts, investments and family support?",
				"How do you behave during conflict: silence, quick discussion, space first, or family mediation?",
				"How will household work be divided when one person has a demanding work week?",
				"What are non-negotiables that are not written in " + one.getFullName() + " or " + two.getFullName() + "'s profile?"
		);
	}

	private static String summary(Profile one, Profile two, int overallScore) {
		return one.getFullName() + " and " + two.getFullName() + " have an estimated profile compatibility of "
				+ overallScore + "/100. Treat this as a conversation map: the strongest matches are the topics you can trust, and the lower scores are the topics you should discuss slowly.";
	}

	private static String recommendation(int overallScore, List<String> concerns) {
		if (overallScore >= 80) {
			return "Promising match. Plan a focused conversation around the listed concerns before families move to the next step.";
		}
		if (overallScore >= 65) {
			return "Possible match. Do not reject quickly, but discuss location, family, finances and children plans before emotional commitment.";
		}
		return "Proceed carefully. The profiles show important gaps; clarify expectations before considering engagement.";
	}

	private static int exactScore(String left, String right, int match, int mismatch) {
		return same(left, right) ? match : mismatch;
	}

	private static boolean same(String left, String right) {
		return left != null && right != null && left.equalsIgnoreCase(right);
	}

	private static boolean isOppositeGender(Profile one, Profile two) {
		return one.getGender() != null
				&& two.getGender() != null
				&& !one.getGender().equalsIgnoreCase(two.getGender());
	}

	private static boolean containsAny(String value, String... needles) {
		if (value == null) {
			return false;
		}
		String normalized = value.toLowerCase();
		for (String needle : needles) {
			if (normalized.contains(needle)) {
				return true;
			}
		}
		return false;
	}

	private static int clamp(int score) {
		return Math.max(0, Math.min(100, score));
	}
}
