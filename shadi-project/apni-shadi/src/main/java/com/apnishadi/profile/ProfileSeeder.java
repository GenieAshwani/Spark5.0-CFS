package com.apnishadi.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class ProfileSeeder implements CommandLineRunner {
	private final ProfileRepository profileRepository;

	public ProfileSeeder(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public void run(String... args) {
		List.of(
				profile("Aarav Mehta", "Male", 28, 178, "Mumbai", "Maharashtra", "Hindu", "Gujarati", "Gujarati",
						"MBA, University of Mumbai", "Product Manager", "INR 32 LPA", "Vegetarian", "No", "Socially",
						"Nuclear family", "Yes", "Open to metro cities",
						"Never married. Calm, ambitious and close to family. Enjoys building products that make daily life easier.",
						"cricket, fintech, long drives, street food, podcasts",
						"Build a stable home in a metro city, support both careers, travel twice a year.",
						"A warm partner who values communication, career growth and respectful family bonds."),
				profile("Ananya Sharma", "Female", 28, 164, "Jaipur", "Rajasthan", "Hindu", "Brahmin", "Hindi",
						"M.Tech Computer Science", "Software Engineer", "INR 28 LPA", "Vegetarian", "No", "No",
						"Joint family", "Yes", "Prefers India",
						"Practical, affectionate and curious. Likes meaningful conversations more than small talk.",
						"classical music, trekking, reading, temple visits, cooking",
						"Grow into engineering leadership, stay connected with parents, raise children with Indian values.",
						"An honest, emotionally mature partner who respects work and family responsibilities."),
				profile("Kabir Khan", "Male", 29, 181, "Bengaluru", "Karnataka", "Muslim", "Sunni", "Urdu",
						"B.Tech Electronics", "Cloud Architect", "INR 34 LPA", "Non-vegetarian", "No", "No",
						"Nuclear family", "Unsure", "Open to abroad for 2-3 years",
						"Never married. Thoughtful and health focused. Believes marriage works best when expectations are discussed early.",
						"cycling, biryani, open source, documentaries, gym",
						"Become financially independent, buy a home, and keep weekends protected for family.",
						"A kind partner who is comfortable with Bengaluru life and clear financial planning."),
				profile("Meera Iyer", "Female", 29, 160, "Bengaluru", "Karnataka", "Hindu", "Tamil Iyer", "Tamil",
						"CA", "Finance Controller", "INR 30 LPA", "Vegetarian", "No", "No",
						"Nuclear family", "Yes", "Not open to relocating outside South India",
						"Organized, caring and direct. Loves music, numbers and hosting simple dinners for friends.",
						"carnatic music, yoga, mutual funds, gardening, filter coffee",
						"Balance a serious finance career with a peaceful family life close to parents.",
						"A patient partner who is financially responsible and comfortable sharing household work."),
				profile("Rohan Singh", "Male", 27, 176, "Delhi", "Delhi", "Sikh", "Jat Sikh", "Punjabi",
						"MS Data Science", "Data Scientist", "INR 30 LPA", "Eggetarian", "No", "Socially",
						"Joint family", "Yes", "Open to Delhi NCR or Pune",
						"Never married. Cheerful, analytical and family oriented. Wants friendship to come before big decisions.",
						"photography, hockey, machine learning, cafes, volunteering",
						"Create a respectful partnership, care for parents, and build a small analytics startup someday.",
						"A grounded partner who likes learning, laughter and transparent conversations."),
				profile("Priya Nair", "Female", 27, 162, "Pune", "Maharashtra", "Hindu", "Nair", "Malayalam",
						"MBBS, MD Pediatrics", "Pediatrician", "INR 24 LPA", "Non-vegetarian", "No", "No",
						"Nuclear family", "Yes", "Prefers Pune or Mumbai",
						"Empathetic doctor with a playful side. Values kindness, punctuality and emotional safety.",
						"children's health, swimming, malayalam cinema, baking, travel",
						"Serve children well, keep learning medicine, and build a home where feelings are spoken clearly.",
						"A supportive partner who understands hospital schedules and wants an equal marriage."),
				profile("Arjun Malhotra", "Male", 26, 180, "Gurugram", "Haryana", "Hindu", "Punjabi Khatri", "Hindi",
						"B.Tech Computer Science", "Frontend Engineer", "INR 24 LPA", "Vegetarian", "No", "No",
						"Nuclear family", "Yes", "Open to Delhi NCR or Bengaluru",
						"Never married. Young, focused and cheerful. Likes clean design, family dinners and weekend badminton.",
						"badminton, design systems, ghazals, coffee, hill stations",
						"Grow into product engineering, buy a home near family and keep marriage equal and friendly.",
						"A warm partner who respects career ambition and can speak honestly when something hurts."),
				profile("Dev Patel", "Male", 29, 174, "Ahmedabad", "Gujarat", "Hindu", "Patel", "Gujarati",
						"M.Com", "Family Business Partner", "INR 28 LPA", "Vegetarian", "No", "No",
						"Joint family", "Yes", "Prefers Gujarat or Mumbai",
						"Never married. Grounded, modern and family-minded. Works in exports and prefers simple honest conversations.",
						"garba, exports, cricket, vegetarian food, investing",
						"Scale the family business, travel with spouse and build a respectful home with space for both families.",
						"A partner who values family but also wants privacy, friendship and shared decision-making."),
				profile("Ishaan Rao", "Male", 27, 177, "Hyderabad", "Telangana", "Hindu", "Telugu Brahmin", "Telugu",
						"M.S. Information Systems", "Cybersecurity Analyst", "INR 26 LPA", "Vegetarian", "No", "No",
						"Nuclear family", "Yes", "Open to Hyderabad, Pune or Bengaluru",
						"Never married. Soft-spoken, curious and emotionally steady. Enjoys music, security research and evening walks.",
						"security research, violin, running, dosa places, chess",
						"Keep learning, support two careers and create a calm home where difficult topics are discussed early.",
						"A thoughtful partner who wants mutual respect, emotional safety and practical financial planning."),
				profile("Neel Bhatia", "Male", 30, 179, "Pune", "Maharashtra", "Hindu", "Sindhi", "Hindi",
						"PGDM Marketing", "Brand Strategist", "INR 31 LPA", "Eggetarian", "No", "Socially",
						"Nuclear family", "Yes", "Open to Pune, Mumbai or remote-first life",
						"Never married. Creative, optimistic and close to his younger sister. Likes brand work, cooking breakfast and road trips.",
						"branding, tennis, breakfast experiments, road trips, indie music",
						"Build a creative career, stay fit and create a marriage where both people keep their individuality.",
						"A confident, kind partner who enjoys conversation, travel and planning life as a team."),
				profile("Samar Verma", "Male", 28, 182, "Lucknow", "Uttar Pradesh", "Hindu", "Kayastha", "Hindi",
						"MBBS, MD Medicine", "Internal Medicine Doctor", "INR 22 LPA", "Vegetarian", "No", "No",
						"Joint family", "Yes", "Open to Lucknow or Delhi NCR",
						"Never married. Patient, disciplined and sincere. Hospital schedules are busy, so he protects family time intentionally.",
						"medicine, poetry, morning runs, old hindi songs, tea",
						"Become a compassionate consultant, support parents and build a peaceful home with honest communication.",
						"A patient partner who understands medical life and values kindness over show-off."),
				profile("Tara Kapoor", "Female", 26, 163, "Delhi", "Delhi", "Hindu", "Khatri", "Hindi",
						"M.Des", "UX Designer", "INR 22 LPA", "Vegetarian", "No", "No",
						"Nuclear family", "Yes", "Open to Delhi NCR, Mumbai or Bengaluru",
						"Never married. Design-minded, affectionate and clear about boundaries. Likes art museums and slow Sunday breakfasts.",
						"ux research, museums, yoga, cafes, journaling",
						"Lead a design team, keep close ties with parents and build a home where both careers are respected.",
						"A kind partner who is emotionally available, ambitious and willing to share household work."),
				profile("Nisha Menon", "Female", 27, 161, "Kochi", "Kerala", "Hindu", "Menon", "Malayalam",
						"MBA Finance", "Investment Analyst", "INR 21 LPA", "Non-vegetarian", "No", "No",
						"Nuclear family", "Yes", "Open to Kochi, Bengaluru or Mumbai",
						"Never married. Warm, financially disciplined and playful with close friends. Values calm disagreement over drama.",
						"markets, swimming, malayalam music, seafood, travel planning",
						"Build long-term wealth, support both families and raise children with confidence and empathy.",
						"A steady partner who discusses money clearly and makes room for laughter and family.")
		).forEach(this::saveOrUpdate);
	}

	private void saveOrUpdate(Profile seedProfile) {
		profileRepository.findByFullName(seedProfile.getFullName())
				.ifPresentOrElse(existingProfile -> {
					seedProfile.setId(existingProfile.getId());
					profileRepository.save(seedProfile);
				}, () -> profileRepository.save(seedProfile));
	}

	private static Profile profile(String fullName, String gender, int age, int heightCm, String city, String state,
			String religion, String community, String motherTongue, String education, String profession,
			String annualIncome, String diet, String smoking, String drinking, String familyType, String wantsChildren,
			String relocation, String about, String interests, String lifeGoals, String partnerExpectations) {
		Profile profile = new Profile();
		profile.setFullName(fullName);
		profile.setGender(gender);
		profile.setAge(age);
		profile.setHeightCm(heightCm);
		profile.setCity(city);
		profile.setState(state);
		profile.setReligion(religion);
		profile.setCommunity(community);
		profile.setMotherTongue(motherTongue);
		profile.setEducation(education);
		profile.setProfession(profession);
		profile.setAnnualIncome(annualIncome);
		profile.setDiet(diet);
		profile.setSmoking(smoking);
		profile.setDrinking(drinking);
		profile.setFamilyType(familyType);
		profile.setWantsChildren(wantsChildren);
		profile.setRelocation(relocation);
		profile.setAbout(about);
		profile.setInterests(interests);
		profile.setLifeGoals(lifeGoals);
		profile.setPartnerExpectations(partnerExpectations);
		return profile;
	}
}
