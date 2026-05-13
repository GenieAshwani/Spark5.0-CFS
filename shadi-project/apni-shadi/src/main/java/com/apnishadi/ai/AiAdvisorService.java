package com.apnishadi.ai;

import com.apnishadi.match.MatchReport;
import com.apnishadi.profile.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AiAdvisorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AiAdvisorService.class);

	private static final String ADVISOR_INSTRUCTIONS = """
			You are Vivaah.com's AI compatibility advisor for an Indian matrimony app.
			Be warm, practical, respectful and direct.
			Do not promise marriage success. Do not say a marriage will definitely last.
			You may answer normal user questions that are not about marriage or the opened match.
			When the user asks about the opened match, compare the two profiles and backend statistics.
			When comparing profiles, give balanced guidance: strengths, concerns, exact topics to discuss, and a next-step suggestion.
			Keep advice suitable for a user and their family to read.
			""";

	private final AiClientRouter aiClientRouter;

	public AiAdvisorService(AiClientRouter aiClientRouter) {
		this.aiClientRouter = aiClientRouter;
	}

	public String writeNarrative(MatchReport report) {
		String input = """
				Create a detailed but concise marriage compatibility report.
				Use clear short sections with these labels: Best signs, Discuss carefully, Questions to ask, Next step.
				Do not use markdown tables.
				Keep bullet points short enough for a mobile screen.

				Profile A:
				%s

				Profile B:
				%s

				Backend compatibility summary:
				Overall score: %d/100
				Statistics: %s
				Strengths: %s
				Concerns: %s
				Recommended questions: %s
				Recommendation: %s
				""".formatted(
				profileText(report.profileOne()),
				profileText(report.profileTwo()),
				report.overallScore(),
				report.statistics(),
				report.strengths(),
				report.concerns(),
				report.questionsToAsk(),
				report.recommendation()
		);
		return createTextResponse(input).text();
	}

	public AiChatResponse chat(Profile userProfile, Profile matchProfile, String userMessage, MatchReport report) {
		String input = """
				The logged-in user is Profile A. The opened match is Profile B.
				First decide intent from the user question.
				If the question is general or unrelated, answer it naturally and briefly. You can mention that you can compare the opened match if they want.
				If the question asks about this person, marriage, match quality, compatibility, profile risk, family, money, city, career, children, lifestyle or what to ask next, use both profiles and the backend report.

				Profile A:
				%s

				Profile B:
				%s

				Backend report:
				Score: %d/100
				Statistics: %s
				Strengths: %s
				Concerns: %s

				User question:
				%s
				""".formatted(
				profileText(userProfile),
				profileText(matchProfile),
				report.overallScore(),
				report.statistics(),
				report.strengths(),
				report.concerns(),
				userMessage
		);

		AiGeneration generation = createTextResponse(input);
		return new AiChatResponse(generation.text(), generation.generatedByAi(), generation.provider() + ":" + generation.model());
	}

	private AiGeneration createTextResponse(String input) {
		AiTextClient preferredClient = aiClientRouter.preferredClient();
		AiTextClient fallbackClient = aiClientRouter.fallbackClient();
		AiTextClient secondaryClient = aiClientRouter.secondaryClient();

		for (AiTextClient client : new AiTextClient[] { preferredClient, fallbackClient, secondaryClient }) {
			if (client.isConfigured()) {
				try {
					return new AiGeneration(client.createTextResponse(ADVISOR_INSTRUCTIONS, input), true, client.provider(), client.model());
				} catch (RuntimeException exception) {
					LOGGER.warn("{} AI provider failed: {}", client.provider(), exception.getMessage());
				}
			}
		}

		return new AiGeneration(aiFallback(preferredClient.provider()), false, preferredClient.provider(), preferredClient.model());
	}

	private static String profileText(Profile profile) {
		return """
				Name: %s
				Gender: %s
				Age: %d
				Location: %s, %s
				Religion/community: %s / %s
				Education: %s
				Profession: %s
				Income: %s
				Diet/smoking/drinking: %s / %s / %s
				Family type: %s
				Children: %s
				Relocation: %s
				About: %s
				Life goals: %s
				Partner expectations: %s
				""".formatted(
				profile.getFullName(),
				profile.getGender(),
				profile.getAge(),
				profile.getCity(),
				profile.getState(),
				profile.getReligion(),
				profile.getCommunity(),
				profile.getEducation(),
				profile.getProfession(),
				profile.getAnnualIncome(),
				profile.getDiet(),
				profile.getSmoking(),
				profile.getDrinking(),
				profile.getFamilyType(),
				profile.getWantsChildren(),
				profile.getRelocation(),
				profile.getAbout(),
				profile.getLifeGoals(),
				profile.getPartnerExpectations()
		);
	}

	private static String aiFallback(String provider) {
		return "AI is wired in the backend, but " + provider + " is not responding right now. Backend calculation still works: use the compatibility score, concerns and pre-marriage questions as a structured discussion guide. Check the Spring Boot console for the AI provider warning.";
	}

	private record AiGeneration(String text, boolean generatedByAi, String provider, String model) {
	}
}
