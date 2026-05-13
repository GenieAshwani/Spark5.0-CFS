import { StrictMode, useEffect, useMemo, useRef, useState } from "react";
import { createRoot } from "react-dom/client";
import "./app.css";

const API_BASE_URL =
  import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080/api";

const WS_BASE_URL = API_BASE_URL.replace(/^http/, "ws").replace(/\/api$/, "");

const LOGIN_HERO_PHOTO =
  "https://images.pexels.com/photos/30482896/pexels-photo-30482896.jpeg?auto=compress&cs=tinysrgb&w=1800";

const maleProfilePhotos = [
  "https://images.pexels.com/photos/35542192/pexels-photo-35542192.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/34423730/pexels-photo-34423730.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/30124921/pexels-photo-30124921.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/33261949/pexels-photo-33261949.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/36342211/pexels-photo-36342211.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/35785944/pexels-photo-35785944.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/19186829/pexels-photo-19186829.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/8770958/pexels-photo-8770958.jpeg?auto=compress&cs=tinysrgb&w=1200",
];

const femaleProfilePhotos = [
  "https://images.pexels.com/photos/32711516/pexels-photo-32711516.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/29049358/pexels-photo-29049358.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/33556310/pexels-photo-33556310.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/33230960/pexels-photo-33230960.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/35354231/pexels-photo-35354231.jpeg?auto=compress&cs=tinysrgb&w=1200",
  "https://images.pexels.com/photos/33703566/pexels-photo-33703566.jpeg?auto=compress&cs=tinysrgb&w=1200",
];

const profilePhotoByName = {
  "Aarav Mehta": maleProfilePhotos[0],
  "Kabir Khan": maleProfilePhotos[1],
  "Rohan Singh": maleProfilePhotos[2],
  "Arjun Malhotra": maleProfilePhotos[3],
  "Dev Patel": maleProfilePhotos[4],
  "Ishaan Rao": maleProfilePhotos[5],
  "Neel Bhatia": maleProfilePhotos[6],
  "Samar Verma": maleProfilePhotos[7],
  "Ananya Sharma": femaleProfilePhotos[0],
  "Meera Iyer": femaleProfilePhotos[1],
  "Priya Nair": femaleProfilePhotos[2],
  "Tara Kapoor": femaleProfilePhotos[3],
  "Nisha Menon": femaleProfilePhotos[4],
};

const fallbackProfiles = [
  profile(
    1,
    "Aarav Mehta",
    "Male",
    30,
    178,
    "Mumbai",
    "Maharashtra",
    "Hindu",
    "Gujarati",
    "Product Manager",
    "MBA, University of Mumbai",
    "INR 32 LPA",
    "Vegetarian",
    "Nuclear family",
    "Yes",
    "Open to metro cities",
    "Calm, ambitious and close to family. Enjoys building products that make daily life easier.",
    "Build a stable home in a metro city, support both careers, travel twice a year.",
  ),
  profile(
    2,
    "Ananya Sharma",
    "Female",
    28,
    164,
    "Jaipur",
    "Rajasthan",
    "Hindu",
    "Brahmin",
    "Software Engineer",
    "M.Tech Computer Science",
    "INR 28 LPA",
    "Vegetarian",
    "Joint family",
    "Yes",
    "Prefers India",
    "Practical, affectionate and curious. Likes meaningful conversations more than small talk.",
    "Grow into engineering leadership, stay connected with parents, raise children with Indian values.",
  ),
  profile(
    3,
    "Kabir Khan",
    "Male",
    32,
    181,
    "Bengaluru",
    "Karnataka",
    "Muslim",
    "Sunni",
    "Cloud Architect",
    "B.Tech Electronics",
    "INR 40 LPA",
    "Non-vegetarian",
    "Nuclear family",
    "Unsure",
    "Open to abroad for 2-3 years",
    "Thoughtful and health focused. Believes marriage works best when expectations are discussed early.",
    "Become financially independent, buy a home, and keep weekends protected for family.",
  ),
  profile(
    4,
    "Meera Iyer",
    "Female",
    29,
    160,
    "Bengaluru",
    "Karnataka",
    "Hindu",
    "Tamil Iyer",
    "Finance Controller",
    "CA",
    "INR 30 LPA",
    "Vegetarian",
    "Nuclear family",
    "Yes",
    "Not open to relocating outside South India",
    "Organized, caring and direct. Loves music, numbers and hosting simple dinners for friends.",
    "Balance a serious finance career with a peaceful family life close to parents.",
  ),
  profile(
    5,
    "Rohan Singh",
    "Male",
    31,
    176,
    "Delhi",
    "Delhi",
    "Sikh",
    "Jat Sikh",
    "Data Scientist",
    "MS Data Science",
    "INR 36 LPA",
    "Eggetarian",
    "Joint family",
    "Yes",
    "Open to Delhi NCR or Pune",
    "Cheerful, analytical and family oriented. Wants friendship to come before big decisions.",
    "Create a respectful partnership, care for parents, and build a small analytics startup someday.",
  ),
  profile(
    6,
    "Priya Nair",
    "Female",
    27,
    162,
    "Pune",
    "Maharashtra",
    "Hindu",
    "Nair",
    "Pediatrician",
    "MBBS, MD Pediatrics",
    "INR 24 LPA",
    "Non-vegetarian",
    "Nuclear family",
    "Yes",
    "Prefers Pune or Mumbai",
    "Empathetic doctor with a playful side. Values kindness, punctuality and emotional safety.",
    "Serve children well, keep learning medicine, and build a home where feelings are spoken clearly.",
  ),
];

function App() {
  const [profiles, setProfiles] = useState(fallbackProfiles);
  const [loggedInProfileId, setLoggedInProfileId] = useState(null);
  const [authError, setAuthError] = useState("");
  const [isAuthLoading, setIsAuthLoading] = useState(false);
  const [matches, setMatches] = useState([]);
  const [selectedProfileId, setSelectedProfileId] = useState(null);
  const [report, setReport] = useState(null);
  const [aiMessages, setAiMessages] = useState([]);
  const [isAssistantOpen, setIsAssistantOpen] = useState(false);
  const [status, setStatus] = useState("Connecting to profile database...");
  const [isReportLoading, setIsReportLoading] = useState(false);
  const [interestRequests, setInterestRequests] = useState([]);
  const [conversations, setConversations] = useState([]);
  const [isRequestCenterOpen, setIsRequestCenterOpen] = useState(false);
  const [selectedConversationId, setSelectedConversationId] = useState(null);
  const [personMessages, setPersonMessages] = useState([]);
  const knownIncomingRequestIds = useRef(new Set());
  const didLoadRequests = useRef(false);

  useEffect(() => {
    fetchProfiles();
  }, []);

  useEffect(() => {
    if (!loggedInProfileId) {
      return undefined;
    }
    fetchRequests(loggedInProfileId);
    fetchConversations(loggedInProfileId);
    const intervalId = window.setInterval(() => {
      fetchRequests(loggedInProfileId);
      fetchConversations(loggedInProfileId);
    }, 15000);
    return () => window.clearInterval(intervalId);
  }, [loggedInProfileId]);

  useEffect(() => {
    if (!selectedConversationId) {
      return undefined;
    }
    const socket = new WebSocket(
      `${WS_BASE_URL}/ws/conversations/${selectedConversationId}`,
    );
    socket.onmessage = (event) => {
      const nextMessage = JSON.parse(event.data);
      if (nextMessage.senderProfileId !== Number(loggedInProfileId)) {
        playNotificationTone("message");
      }
      setPersonMessages((currentMessages) =>
        upsertChatMessage(currentMessages, nextMessage),
      );
    };
    return () => socket.close();
  }, [loggedInProfileId, selectedConversationId]);

  const loggedInProfile = useMemo(
    () =>
      profiles.find((candidate) => candidate.id === Number(loggedInProfileId)),
    [profiles, loggedInProfileId],
  );

  const selectedProfile = useMemo(
    () =>
      profiles.find((candidate) => candidate.id === Number(selectedProfileId)),
    [profiles, selectedProfileId],
  );

  const visibleMatches =
    matches.length > 0
      ? matches
      : buildFallbackMatches(profiles, loggedInProfileId);

  const pendingIncomingRequests = interestRequests.filter(
    (request) =>
      request.status === "PENDING" &&
      request.receiverProfile.id === Number(loggedInProfileId),
  );

  const selectedConversation = conversations.find(
    (conversation) => conversation.id === Number(selectedConversationId),
  );

  async function fetchProfiles() {
    try {
      const response = await fetch(`${API_BASE_URL}/profiles`);
      if (!response.ok) {
        throw new Error("Profile API unavailable");
      }
      const apiProfiles = await response.json();
      setProfiles(withPhotos(apiProfiles));
      setStatus("Live profile database connected");
    } catch {
      setStatus(
        "Preview mode. Spring Boot will replace these profiles when it is running.",
      );
    }
  }

  async function enterSession(sessionProfile) {
    knownIncomingRequestIds.current = new Set();
    didLoadRequests.current = false;
    const decoratedProfile = withPhoto(sessionProfile);
    setProfiles((currentProfiles) =>
      upsertProfile(currentProfiles, decoratedProfile),
    );
    setLoggedInProfileId(decoratedProfile.id);
    setSelectedProfileId(null);
    setReport(null);
    setIsAssistantOpen(false);
    setAiMessages([]);
    await fetchMatches(decoratedProfile.id);
    await fetchRequests(decoratedProfile.id);
    await fetchConversations(decoratedProfile.id);
  }

  async function loginWithPassword(credentials) {
    setIsAuthLoading(true);
    setAuthError("");
    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(credentials),
      });
      if (!response.ok) {
        throw new Error("Login failed");
      }
      const auth = await response.json();
      await enterSession(auth.profile);
      setStatus("Logged in with backend account");
    } catch {
      const demoProfile = demoLogin(credentials);
      if (!demoProfile) {
        setAuthError(
          "Login failed. Try a registered account, or use an MVP test account: ananya@example.com / Password@123",
        );
      } else {
        await enterSession(demoProfile);
        setStatus("Preview login. Start the API for real backend login.");
      }
    } finally {
      setIsAuthLoading(false);
    }
  }

  async function signup(signupData) {
    setIsAuthLoading(true);
    setAuthError("");
    try {
      const response = await fetch(`${API_BASE_URL}/auth/signup`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(signupData),
      });
      if (!response.ok) {
        throw new Error("Signup failed");
      }
      const auth = await response.json();
      await enterSession(auth.profile);
      setStatus("Signup complete. Your match list is ready.");
    } catch {
      const localProfile = {
        ...signupData.profile,
        id: Math.max(...profiles.map((profile) => profile.id)) + 1,
        photo: photoForProfile(signupData.profile, profiles.length),
      };
      await enterSession(localProfile);
      setStatus(
        "Preview signup created in browser. Start the API to save new accounts.",
      );
    } finally {
      setIsAuthLoading(false);
    }
  }

  async function fetchMatches(profileId) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/matches?profileId=${profileId}`,
      );
      if (!response.ok) {
        throw new Error("Matches API unavailable");
      }
      const apiMatches = await response.json();
      setMatches(
        apiMatches.map((match) => ({
          ...match,
          profile: withPhoto(match.profile),
        })),
      );
      setStatus("Your match list is ranked from the backend report engine");
    } catch {
      setMatches(buildFallbackMatches(profiles, profileId));
      setStatus(
        "Preview matches shown. Start the API for ranked backend recommendations.",
      );
    }
  }

  function openProfile(profileId) {
    setSelectedProfileId(Number(profileId));
    setReport(null);
    setIsAssistantOpen(true);
    setAiMessages([
      {
        role: "assistant",
        content:
          "Let me help you. Ask me whether this profile is suitable, what may be good, what may be risky, or what to ask before family talks.",
      },
    ]);
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  async function createReport(matchProfileId = selectedProfileId) {
    if (!loggedInProfileId || !matchProfileId) {
      return;
    }
    setIsReportLoading(true);
    setIsAssistantOpen(false);
    try {
      const response = await fetch(`${API_BASE_URL}/match-reports`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          profileOneId: Number(loggedInProfileId),
          profileTwoId: Number(matchProfileId),
        }),
      });
      if (!response.ok) {
        throw new Error("Report API unavailable");
      }
      setReport(await response.json());
      setStatus("Detailed AI-style report is ready");
    } catch {
      const matchProfile =
        profiles.find((profile) => profile.id === Number(matchProfileId)) ??
        selectedProfile;
      setReport(buildFallbackReport(loggedInProfile, matchProfile));
      setStatus(
        "Preview report shown. Start the backend for profile-specific scoring.",
      );
    } finally {
      setIsReportLoading(false);
    }
  }

  async function sendAiMessage(message) {
    const userMessage = { role: "user", content: message };
    setAiMessages((currentMessages) => [
      ...currentMessages,
      userMessage,
      { role: "assistant", content: "Reading both profiles..." },
    ]);
    try {
      const response = await fetch(`${API_BASE_URL}/ai/chat`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          profileOneId: Number(loggedInProfileId),
          profileTwoId: Number(selectedProfileId),
          message,
        }),
      });
      if (!response.ok) {
        throw new Error("AI chat failed");
      }
      const answer = await response.json();
      setAiMessages((currentMessages) =>
        replaceLastAssistantMessage(currentMessages, answer.reply),
      );
      setStatus(
        answer.generatedByOpenAi
          ? `AI answered with ${answer.model}`
          : "AI fallback answered. Check Gemini key and backend console for real model output.",
      );
    } catch (error) {
      setAiMessages((currentMessages) =>
        replaceLastAssistantMessage(
          currentMessages,
          "I could not reach the Spring Boot AI endpoint. Start the backend on port 8080, then ask again. If the backend is running, check its console for the Gemini provider warning.",
        ),
      );
      setStatus(`AI request did not finish: ${error.message}`);
    }
  }

  async function fetchRequests(profileId) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/interest-requests?profileId=${profileId}&box=all`,
      );
      if (!response.ok) {
        throw new Error("Requests API unavailable");
      }
      const apiRequests = await response.json();
      const decoratedRequests = apiRequests.map(withPhotosOnRequest);
      notifyAboutNewIncomingRequests(decoratedRequests, profileId);
      setInterestRequests(decoratedRequests);
    } catch {
      setInterestRequests([]);
    }
  }

  async function fetchConversations(profileId) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/conversations?profileId=${profileId}`,
      );
      if (!response.ok) {
        throw new Error("Conversations API unavailable");
      }
      const apiConversations = await response.json();
      setConversations(apiConversations.map(withPhotosOnConversation));
    } catch {
      setConversations([]);
    }
  }

  async function sendInterest(receiverProfileId) {
    try {
      const response = await fetch(`${API_BASE_URL}/interest-requests`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          senderProfileId: Number(loggedInProfileId),
          receiverProfileId: Number(receiverProfileId),
        }),
      });
      if (!response.ok) {
        throw new Error("Interest request failed");
      }
      const request = withPhotosOnRequest(await response.json());
      setInterestRequests((currentRequests) =>
        upsertInterestRequest(currentRequests, request),
      );
      setStatus(`Interest sent to ${request.receiverProfile.fullName}`);
    } catch (error) {
      setStatus(`Could not send interest: ${error.message}`);
    }
  }

  async function acceptInterest(requestId) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/interest-requests/${requestId}/accept`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ profileId: Number(loggedInProfileId) }),
        },
      );
      if (!response.ok) {
        throw new Error("Accept request failed");
      }
      const conversation = withPhotosOnConversation(await response.json());
      await fetchRequests(loggedInProfileId);
      await fetchConversations(loggedInProfileId);
      openConversation(conversation);
      setStatus("Request accepted. Your private chat is open.");
    } catch (error) {
      setStatus(`Could not accept request: ${error.message}`);
    }
  }

  async function declineInterest(requestId) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/interest-requests/${requestId}/decline`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ profileId: Number(loggedInProfileId) }),
        },
      );
      if (!response.ok) {
        throw new Error("Decline request failed");
      }
      const request = withPhotosOnRequest(await response.json());
      setInterestRequests((currentRequests) =>
        upsertInterestRequest(currentRequests, request),
      );
      setStatus("Request declined.");
    } catch (error) {
      setStatus(`Could not decline request: ${error.message}`);
    }
  }

  async function openConversation(conversation) {
    const decoratedConversation = withPhotosOnConversation(conversation);
    setConversations((currentConversations) =>
      upsertConversation(currentConversations, decoratedConversation),
    );
    setSelectedConversationId(conversation.id);
    setIsRequestCenterOpen(false);
    try {
      const response = await fetch(
        `${API_BASE_URL}/conversations/${conversation.id}/messages?profileId=${loggedInProfileId}`,
      );
      if (!response.ok) {
        throw new Error("Messages API unavailable");
      }
      setPersonMessages(await response.json());
    } catch {
      setPersonMessages([]);
    }
  }

  async function sendPersonMessage(message) {
    if (!selectedConversationId || !message.trim()) {
      return;
    }
    try {
      const response = await fetch(
        `${API_BASE_URL}/conversations/${selectedConversationId}/messages`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            senderProfileId: Number(loggedInProfileId),
            message: message.trim(),
          }),
        },
      );
      if (!response.ok) {
        throw new Error("Message API unavailable");
      }
      const savedMessage = await response.json();
      setPersonMessages((currentMessages) =>
        upsertChatMessage(currentMessages, savedMessage),
      );
    } catch (error) {
      setStatus(`Message was not sent: ${error.message}`);
    }
  }

  function notifyAboutNewIncomingRequests(requests, profileId) {
    const incomingRequestIds = requests
      .filter(
        (request) =>
          request.status === "PENDING" &&
          request.receiverProfile.id === Number(profileId),
      )
      .map((request) => request.id);

    const hasNewRequest = incomingRequestIds.some(
      (requestId) => !knownIncomingRequestIds.current.has(requestId),
    );

    if (didLoadRequests.current && hasNewRequest) {
      playNotificationTone("request");
    }

    knownIncomingRequestIds.current = new Set(incomingRequestIds);
    didLoadRequests.current = true;
  }

  if (!loggedInProfile) {
    return (
      <LoginScreen
        onLogin={loginWithPassword}
        onSignup={signup}
        status={status}
        error={authError}
        isLoading={isAuthLoading}
      />
    );
  }

  return (
    <main>
      <TopNav
        profile={loggedInProfile}
        pendingRequestCount={pendingIncomingRequests.length}
        conversationCount={conversations.length}
        onOpenRequestCenter={() => setIsRequestCenterOpen((open) => !open)}
        onLogout={() => {
          setLoggedInProfileId(null);
          setSelectedProfileId(null);
          setMatches([]);
          setReport(null);
          setIsAssistantOpen(false);
          setIsRequestCenterOpen(false);
          setSelectedConversationId(null);
          setPersonMessages([]);
        }}
      />

      {isRequestCenterOpen && (
        <RequestCenter
          loggedInProfile={loggedInProfile}
          requests={interestRequests}
          conversations={conversations}
          onClose={() => setIsRequestCenterOpen(false)}
          onAccept={acceptInterest}
          onDecline={declineInterest}
          onOpenConversation={openConversation}
        />
      )}

      {selectedProfile ? (
        <ProfileDetail
          loggedInProfile={loggedInProfile}
          profile={selectedProfile}
          request={requestBetween(
            interestRequests,
            loggedInProfile.id,
            selectedProfile.id,
          )}
          conversation={conversationBetween(
            conversations,
            loggedInProfile.id,
            selectedProfile.id,
          )}
          report={report}
          isAssistantOpen={isAssistantOpen}
          isReportLoading={isReportLoading}
          aiMessages={aiMessages}
          onBack={() => {
            setSelectedProfileId(null);
            setReport(null);
            setIsAssistantOpen(false);
            setAiMessages([]);
          }}
          onToggleAssistant={() => setIsAssistantOpen((open) => !open)}
          onReport={() => createReport(selectedProfile.id)}
          onSendAiMessage={sendAiMessage}
          onSendInterest={() => sendInterest(selectedProfile.id)}
          onOpenConversation={openConversation}
        />
      ) : (
        <Dashboard
          profile={loggedInProfile}
          matches={visibleMatches}
          status={status}
          onOpenProfile={openProfile}
          onReport={(matchId) => {
            setSelectedProfileId(Number(matchId));
            createReport(matchId);
          }}
        />
      )}

      {selectedConversation && (
        <PersonChatPopover
          conversation={selectedConversation}
          loggedInProfile={loggedInProfile}
          messages={personMessages}
          onClose={() => {
            setSelectedConversationId(null);
            setPersonMessages([]);
          }}
          onSendMessage={sendPersonMessage}
        />
      )}
    </main>
  );
}

function LoginScreen({ onLogin, onSignup, status, error, isLoading }) {
  const [mode, setMode] = useState("login");
  const [credentials, setCredentials] = useState({
    email: "ananya@example.com",
    password: "Password@123",
  });
  const [signupData, setSignupData] = useState({
    email: "",
    phone: "",
    password: "",
    profile: {
      fullName: "",
      gender: "Female",
      age: 26,
      heightCm: 162,
      city: "",
      state: "",
      religion: "",
      community: "",
      motherTongue: "",
      education: "",
      profession: "",
      annualIncome: "",
      diet: "Vegetarian",
      smoking: "No",
      drinking: "No",
      familyType: "Nuclear family",
      wantsChildren: "Yes",
      relocation: "",
      about: "",
      interests: "",
      lifeGoals: "",
      partnerExpectations: "",
    },
  });
  const spotlight = fallbackProfiles[1];

  function updateSignupProfile(field, value) {
    setSignupData((currentData) => ({
      ...currentData,
      profile: {
        ...currentData.profile,
        [field]:
          field === "age" || field === "heightCm" ? Number(value) : value,
      },
    }));
  }

  return (
    <main className="login-page">
      <section className="login-visual">
        <img
          src={LOGIN_HERO_PHOTO}
          alt="Indian bride and groom smiling at their wedding"
        />
        <div className="login-brand-lockup">
          <span className="brand-mark">V</span>
          <span>Shadi.com</span>
        </div>
        <div className="login-photo-caption">
          <span className="caption-kicker">Invitation-only matrimony</span>
          <strong>Meet with clarity before families meet.</strong>
          <span>
            Profile context, family expectations and AI-guided questions in one
            private match room.
          </span>
        </div>
      </section>

      <section className="login-panel">
        <div className="login-panel-header">
          <p className="eyebrow">Vivaah.com private beta</p>
          <h1>
            {mode === "login"
              ? "Enter your private match room."
              : "Create a profile worthy of the right introduction."}
          </h1>
          <p className="intro-text">
            Login to review ranked Indian profiles, open a detailed biodata and
            ask the AI advisor for a balanced compatibility view.
          </p>
        </div>

        <div className="login-proof-strip">
          <div>
            <strong>12</strong>
            <span>compatibility signals</span>
          </div>
          <div>
            <strong>AI</strong>
            <span>private advisor</span>
          </div>
          <div>
            <strong>1:1</strong>
            <span>profile comparison</span>
          </div>
        </div>

        <p className="login-status">{status}</p>

        {error && <p className="form-error">{error}</p>}

        <div className="private-access">
          <div className="private-access-heading">
            <span>Private access</span>
            <small>Secure MVP login</small>
          </div>

          <div className="auth-tabs">
            <button
              className={mode === "login" ? "tab-button active" : "tab-button"}
              onClick={() => setMode("login")}
            >
              Login
            </button>
            <button
              className={mode === "signup" ? "tab-button active" : "tab-button"}
              onClick={() => setMode("signup")}
            >
              Signup
            </button>
          </div>

          {mode === "login" ? (
            <form
              className="auth-form"
              onSubmit={(event) => {
                event.preventDefault();
                onLogin(credentials);
              }}
            >
              <TextInput
                label="Email"
                type="email"
                value={credentials.email}
                onChange={(value) =>
                  setCredentials({ ...credentials, email: value })
                }
              />
              <TextInput
                label="Password"
                type="password"
                value={credentials.password}
                onChange={(value) =>
                  setCredentials({ ...credentials, password: value })
                }
              />
              <button className="primary-button" disabled={isLoading}>
                {isLoading
                  ? "Opening your match room..."
                  : "Open my private match room"}
              </button>
              <small className="form-hint">
                MVP test account: ananya@example.com / Password@123
              </small>
            </form>
          ) : (
            <form
              className="auth-form signup-form"
              onSubmit={(event) => {
                event.preventDefault();
                onSignup(signupData);
              }}
            >
              <TextInput
                label="Email"
                type="email"
                value={signupData.email}
                onChange={(value) =>
                  setSignupData({ ...signupData, email: value })
                }
              />
              <TextInput
                label="Phone"
                value={signupData.phone}
                onChange={(value) =>
                  setSignupData({ ...signupData, phone: value })
                }
              />
              <TextInput
                label="Password"
                type="password"
                value={signupData.password}
                onChange={(value) =>
                  setSignupData({ ...signupData, password: value })
                }
              />
              <TextInput
                label="Full name"
                value={signupData.profile.fullName}
                onChange={(value) => updateSignupProfile("fullName", value)}
              />

              <div className="form-row">
                <TextInput
                  label="Age"
                  type="number"
                  value={signupData.profile.age}
                  onChange={(value) => updateSignupProfile("age", value)}
                />
                <TextInput
                  label="Height cm"
                  type="number"
                  value={signupData.profile.heightCm}
                  onChange={(value) => updateSignupProfile("heightCm", value)}
                />
              </div>

              <div className="form-row">
                <SelectInput
                  label="Gender"
                  value={signupData.profile.gender}
                  options={["Female", "Male"]}
                  onChange={(value) => updateSignupProfile("gender", value)}
                />
                <SelectInput
                  label="Diet"
                  value={signupData.profile.diet}
                  options={["Vegetarian", "Non-vegetarian", "Eggetarian"]}
                  onChange={(value) => updateSignupProfile("diet", value)}
                />
              </div>

              <div className="form-row">
                <TextInput
                  label="City"
                  value={signupData.profile.city}
                  onChange={(value) => updateSignupProfile("city", value)}
                />
                <TextInput
                  label="State"
                  value={signupData.profile.state}
                  onChange={(value) => updateSignupProfile("state", value)}
                />
              </div>

              <div className="form-row">
                <TextInput
                  label="Religion"
                  value={signupData.profile.religion}
                  onChange={(value) => updateSignupProfile("religion", value)}
                />
                <TextInput
                  label="Community"
                  value={signupData.profile.community}
                  onChange={(value) => updateSignupProfile("community", value)}
                />
              </div>

              <TextInput
                label="Mother tongue"
                value={signupData.profile.motherTongue}
                onChange={(value) => updateSignupProfile("motherTongue", value)}
              />
              <TextInput
                label="Education"
                value={signupData.profile.education}
                onChange={(value) => updateSignupProfile("education", value)}
              />
              <TextInput
                label="Profession"
                value={signupData.profile.profession}
                onChange={(value) => updateSignupProfile("profession", value)}
              />
              <TextInput
                label="Annual income"
                value={signupData.profile.annualIncome}
                onChange={(value) => updateSignupProfile("annualIncome", value)}
              />
              <TextInput
                label="Relocation preference"
                value={signupData.profile.relocation}
                onChange={(value) => updateSignupProfile("relocation", value)}
              />
              <TextArea
                label="About you"
                value={signupData.profile.about}
                onChange={(value) => updateSignupProfile("about", value)}
              />
              <TextArea
                label="Life goals"
                value={signupData.profile.lifeGoals}
                onChange={(value) => updateSignupProfile("lifeGoals", value)}
              />
              <TextArea
                label="Partner expectations"
                value={signupData.profile.partnerExpectations}
                onChange={(value) =>
                  updateSignupProfile("partnerExpectations", value)
                }
              />

              <button className="primary-button" disabled={isLoading}>
                {isLoading
                  ? "Creating profile..."
                  : "Signup and see my matches"}
              </button>
            </form>
          )}
        </div>

        <div className="trust-row">
          <span>Profile-first</span>
          <span>AI guidance</span>
          <span>Family-ready questions</span>
        </div>
      </section>
    </main>
  );
}

function TextInput({ label, type = "text", value, onChange }) {
  return (
    <label className="login-field">
      {label}
      <input
        type={type}
        value={value}
        onChange={(event) => onChange(event.target.value)}
        required
      />
    </label>
  );
}

function TextArea({ label, value, onChange }) {
  return (
    <label className="login-field">
      {label}
      <textarea
        value={value}
        onChange={(event) => onChange(event.target.value)}
        rows="4"
        required
      />
    </label>
  );
}

function SelectInput({ label, value, options, onChange }) {
  return (
    <label className="login-field">
      {label}
      <select value={value} onChange={(event) => onChange(event.target.value)}>
        {options.map((option) => (
          <option key={option} value={option}>
            {option}
          </option>
        ))}
      </select>
    </label>
  );
}

function TopNav({
  profile,
  pendingRequestCount,
  conversationCount,
  onOpenRequestCenter,
  onLogout,
}) {
  return (
    <nav className="top-nav">
      <button className="brand-button" onClick={onLogout}>
        <span className="brand-mark small">V</span>
        Vivaah.com
      </button>
      <div className="nav-links">
        <a href="#matches">Matches</a>
        <a href="#advisor">AI advisor</a>
      </div>
      <button className="nav-request-button" onClick={onOpenRequestCenter}>
        Requests
        {pendingRequestCount > 0 && (
          <span className="nav-badge">{pendingRequestCount}</span>
        )}
        <small>{conversationCount} chats</small>
      </button>
      <div className="nav-profile">
        <img src={profile.photo} alt="" />
        <span>{profile.fullName}</span>
      </div>
    </nav>
  );
}

function Dashboard({ profile, matches, status, onOpenProfile, onReport }) {
  const bestScore = matches[0]?.overallScore ?? 0;

  return (
    <>
      <section className="dashboard-hero">
        <div>
          <p className="eyebrow">Your match room</p>
          <h1>
            Namaste {firstName(profile.fullName)}. Your strongest conversations
            start here.
          </h1>
          <p className="intro-text">{status}</p>
          <div className="hero-stats">
            <Metric number={matches.length} label="recommended profiles" />
            <Metric number={`${bestScore}%`} label="strongest score today" />
            <Metric number="6" label="pre-marriage questions in each report" />
          </div>
        </div>
      </section>

      <section className="match-section" id="matches">
        <div className="section-heading">
          <p className="eyebrow">Recommended matches</p>
          <h3>
            Ranked for your city, lifestyle, family plan and long-term goals.
          </h3>
        </div>
        <div className="match-grid">
          {matches.map((match) => (
            <MatchCard
              match={match}
              key={match.profile.id}
              onOpenProfile={onOpenProfile}
              onReport={onReport}
            />
          ))}
        </div>
      </section>
    </>
  );
}

function MatchCard({ match, onOpenProfile, onReport }) {
  const profile = match.profile;

  return (
    <article className="match-card">
      <button
        className="image-button match-image-wrap"
        onClick={() => onOpenProfile(profile.id)}
      >
        <img src={profile.photo} alt={`${profile.fullName} profile`} />
        <span className="image-chip">{profile.city}</span>
        <span className="score-chip">{match.overallScore}% match</span>
      </button>
      <div className="match-card-body">
        <div className="match-title-row">
          <div>
            <h3>{profile.fullName}</h3>
            <p>
              {profile.age} / {profile.profession} / {profile.city}
            </p>
          </div>
          <b className="score-pill">{match.overallScore}%</b>
        </div>
        <p>{profile.about}</p>
        <ul className="reason-list">
          {match.topReasons.map((reason) => (
            <li key={reason}>{reason}</li>
          ))}
        </ul>
        <div className="card-actions">
          <button
            className="secondary-button"
            onClick={() => onOpenProfile(profile.id)}
          >
            Open profile
          </button>
          <button
            className="primary-button small"
            onClick={() => onReport(profile.id)}
          >
            Ask AI
          </button>
        </div>
      </div>
    </article>
  );
}

function ProfileDetail({
  loggedInProfile,
  profile,
  request,
  conversation,
  report,
  isAssistantOpen,
  isReportLoading,
  aiMessages,
  onBack,
  onToggleAssistant,
  onReport,
  onSendAiMessage,
  onSendInterest,
  onOpenConversation,
}) {
  return (
    <>
      <section className="detail-hero">
        <button className="text-button" onClick={onBack}>
          Back to my matches
        </button>
        <div className="detail-layout">
          <div className="detail-photo-frame">
            <img
              className="detail-photo"
              src={profile.photo}
              alt={`${profile.fullName} profile`}
            />
            <div className="detail-photo-caption">
              <span>
                {profile.city}, {profile.state}
              </span>
              <strong>{profile.profession}</strong>
            </div>
          </div>
          <div className="detail-copy">
            <p className="eyebrow">Profile detail</p>
            <h1>{profile.fullName}</h1>
            <p className="intro-text">{profile.about}</p>
            <div className="profile-facts">
              <span>{profile.age} years</span>
              <span>{profile.heightCm} cm</span>
              <span>{profile.religion}</span>
              <span>{profile.community}</span>
              <span>{profile.city}</span>
              <span>{profile.annualIncome}</span>
            </div>
            <button
              className="primary-button"
              onClick={onReport}
              disabled={isReportLoading}
            >
              {isReportLoading
                ? "Preparing report..."
                : `AI report with ${firstName(loggedInProfile.fullName)}`}
            </button>
            <button
              className="secondary-button detail-chat-button"
              onClick={onToggleAssistant}
              id="advisor"
            >
              Chat with AI advisor
            </button>
            <ConnectionActions
              request={request}
              conversation={conversation}
              onSendInterest={onSendInterest}
              onOpenConversation={onOpenConversation}
            />
          </div>
        </div>
      </section>

      <section className="bio-section">
        <ProfileInfo
          title="Career"
          lines={[profile.profession, profile.education, profile.annualIncome]}
        />
        <ProfileInfo
          title="Lifestyle"
          lines={[
            profile.diet,
            profile.familyType,
            `Children: ${profile.wantsChildren}`,
            profile.relocation,
          ]}
        />
        <ProfileInfo
          title="Life goal"
          lines={[profile.lifeGoals ?? "Ask directly about long-term goals."]}
        />
      </section>

      {isAssistantOpen && (
        <AssistantPopover
          messages={aiMessages}
          onClose={onToggleAssistant}
          onReport={onReport}
          onSendMessage={onSendAiMessage}
        />
      )}

      {report && <ReportDialog report={report} />}
    </>
  );
}

function ConnectionActions({
  request,
  conversation,
  onSendInterest,
  onOpenConversation,
}) {
  if (conversation) {
    return (
      <button
        className="primary-button"
        onClick={() => onOpenConversation(conversation)}
      >
        Open private chat
      </button>
    );
  }

  if (request?.status === "PENDING") {
    return (
      <p className="connection-note">
        Interest request is pending. Private chat unlocks after acceptance.
      </p>
    );
  }

  if (request?.status === "DECLINED") {
    return <p className="connection-note">This request was declined.</p>;
  }

  return (
    <button className="primary-button" onClick={onSendInterest}>
      Send interest
    </button>
  );
}

function AssistantPopover({ messages, onClose, onReport, onSendMessage }) {
  const [message, setMessage] = useState(
    "Is this a good match for me? Tell me honestly.",
  );

  return (
    <div className="assistant-popover">
      <div className="assistant-chat-header">
        <div className="assistant-avatar">AI</div>
        <div>
          <p className="eyebrow">Real AI assistant</p>
          <h3>Let me help you.</h3>
        </div>
        <button className="assistant-close" onClick={onClose}>
          Close
        </button>
      </div>

      <div className="chat-window">
        {messages.map((chatMessage, index) => (
          <div
            className={`chat-bubble ${chatMessage.role}`}
            key={`${chatMessage.role}-${index}`}
          >
            <AiText text={chatMessage.content} />
          </div>
        ))}
      </div>

      <form
        className="assistant-form"
        onSubmit={(event) => {
          event.preventDefault();
          if (!message.trim()) {
            return;
          }
          onSendMessage(message.trim());
          setMessage("");
        }}
      >
        <textarea
          value={message}
          onChange={(event) => setMessage(event.target.value)}
          placeholder="Ask about this match, family, career, city, children plans..."
          rows="3"
        />
        <div className="assistant-actions">
          <button type="button" className="secondary-button" onClick={onReport}>
            Full report
          </button>
          <button className="primary-button small">Ask AI</button>
        </div>
      </form>
    </div>
  );
}

function RequestCenter({
  loggedInProfile,
  requests,
  conversations,
  onClose,
  onAccept,
  onDecline,
  onOpenConversation,
}) {
  const incoming = requests.filter(
    (request) => request.receiverProfile.id === loggedInProfile.id,
  );
  const outgoing = requests.filter(
    (request) => request.senderProfile.id === loggedInProfile.id,
  );

  return (
    <aside className="request-center">
      <button className="assistant-close" onClick={onClose}>
        Close
      </button>
      <p className="eyebrow">Requests and chats</p>
      <h3>People waiting for your answer.</h3>

      <div className="request-center-section">
        <h4>Incoming requests</h4>
        {incoming.length === 0 ? (
          <p>No incoming interest requests yet.</p>
        ) : (
          incoming.map((request) => (
            <InterestRequestItem
              key={request.id}
              request={request}
              profile={request.senderProfile}
              loggedInProfile={loggedInProfile}
              onAccept={onAccept}
              onDecline={onDecline}
              onOpenConversation={onOpenConversation}
            />
          ))
        )}
      </div>

      <div className="request-center-section">
        <h4>Your chats</h4>
        {conversations.length === 0 ? (
          <p>Accept a request to unlock private chat.</p>
        ) : (
          conversations.map((conversation) => {
            const otherProfile = otherConversationProfile(
              conversation,
              loggedInProfile.id,
            );
            return (
              <button
                className="conversation-row"
                key={conversation.id}
                onClick={() => onOpenConversation(conversation)}
              >
                <img src={otherProfile.photo} alt="" />
                <span>
                  <strong>{otherProfile.fullName}</strong>
                  <small>Open private chat</small>
                </span>
              </button>
            );
          })
        )}
      </div>

      <div className="request-center-section">
        <h4>Sent by you</h4>
        {outgoing.length === 0 ? (
          <p>You have not sent any interest request yet.</p>
        ) : (
          outgoing.map((request) => (
            <InterestRequestItem
              key={request.id}
              request={request}
              profile={request.receiverProfile}
              loggedInProfile={loggedInProfile}
              onAccept={onAccept}
              onDecline={onDecline}
              onOpenConversation={onOpenConversation}
            />
          ))
        )}
      </div>
    </aside>
  );
}

function InterestRequestItem({
  request,
  profile,
  loggedInProfile,
  onAccept,
  onDecline,
  onOpenConversation,
}) {
  const isReceiver = request.receiverProfile.id === loggedInProfile.id;

  return (
    <article className="request-item">
      <img src={profile.photo} alt="" />
      <div>
        <h4>{profile.fullName}</h4>
        <p>
          {profile.age} / {profile.profession} / {profile.city}
        </p>
        <span className={`request-status ${request.status.toLowerCase()}`}>
          {request.status}
        </span>
      </div>
      {request.status === "PENDING" && isReceiver && (
        <div className="request-actions">
          <button
            className="primary-button small"
            onClick={() => onAccept(request.id)}
          >
            Accept
          </button>
          <button
            className="secondary-button"
            onClick={() => onDecline(request.id)}
          >
            Decline
          </button>
        </div>
      )}
      {request.status === "ACCEPTED" && request.conversationId && (
        <button
          className="secondary-button"
          onClick={() =>
            onOpenConversation({
              id: request.conversationId,
              profileOne: request.senderProfile,
              profileTwo: request.receiverProfile,
              interestRequestId: request.id,
            })
          }
        >
          Chat
        </button>
      )}
    </article>
  );
}

function PersonChatPopover({
  conversation,
  loggedInProfile,
  messages,
  onClose,
  onSendMessage,
}) {
  const [draft, setDraft] = useState("");
  const otherProfile = otherConversationProfile(
    conversation,
    loggedInProfile.id,
  );

  return (
    <aside className="person-chat-popover">
      <div className="person-chat-header">
        <img src={otherProfile.photo} alt="" />
        <div>
          <p className="eyebrow">Private chat</p>
          <h3>{otherProfile.fullName}</h3>
        </div>
        <button className="assistant-close" onClick={onClose}>
          Close
        </button>
      </div>

      <div className="person-chat-window">
        {messages.length === 0 ? (
          <p className="empty-chat">
            Chat is unlocked. Send a respectful first message.
          </p>
        ) : (
          messages.map((message) => {
            const mine = message.senderProfileId === loggedInProfile.id;
            return (
              <div
                className={mine ? "person-message mine" : "person-message"}
                key={message.id}
              >
                <small>{mine ? "You" : message.senderName}</small>
                <p>{message.message}</p>
              </div>
            );
          })
        )}
      </div>

      <form
        className="person-chat-form"
        onSubmit={(event) => {
          event.preventDefault();
          onSendMessage(draft);
          setDraft("");
        }}
      >
        <textarea
          value={draft}
          onChange={(event) => setDraft(event.target.value)}
          placeholder={`Write to ${firstName(otherProfile.fullName)}...`}
          rows="3"
        />
        <button className="primary-button small">Send</button>
      </form>
    </aside>
  );
}

function ProfileInfo({ title, lines }) {
  return (
    <article className="info-block">
      <h3>{title}</h3>
      {lines.map((line) => (
        <p key={line}>{line}</p>
      ))}
    </article>
  );
}

function ProfileSignature({ profile }) {
  return (
    <article className="profile-signature">
      <div className="signature-avatar-wrap">
        <img src={profile.photo} alt={`${profile.fullName} profile`} />
      </div>
      <div>
        <p className="eyebrow">Logged in as</p>
        <h3>{profile.fullName}</h3>
        <p>{profile.profession}</p>
        <p>
          {profile.city}, {profile.state}
        </p>
      </div>
      <div className="signature-chips">
        <span>{profile.gender}</span>
        <span>{profile.age} years</span>
        <span>{profile.community}</span>
      </div>
    </article>
  );
}

function Metric({ number, label }) {
  return (
    <div className="metric">
      <strong>{number}</strong>
      <span>{label}</span>
    </div>
  );
}

function ReportDialog({ report }) {
  return (
    <section className="report-dialog">
      <div className="report-panel">
        <div className="report-header">
          <p className="eyebrow">Detailed AI compatibility report</p>
          <h2>{report.overallScore}/100 compatibility</h2>
          <p>{report.summary}</p>
          <strong>Confidence: {report.confidence}</strong>
        </div>
        {report.aiNarrative && (
          <article className="ai-narrative">
            <p className="eyebrow">AI advisor note</p>
            <AiText text={report.aiNarrative} />
          </article>
        )}
        <div className="score-list">
          {report.statistics.map((statistic) => (
            <article className="score-row" key={statistic.label}>
              <div>
                <h3>{statistic.label}</h3>
                <p>{statistic.reason}</p>
              </div>
              <meter min="0" max="100" value={statistic.score}></meter>
              <b>{statistic.score}%</b>
            </article>
          ))}
        </div>
        <ReportList title="What is good" items={report.strengths} />
        <ReportList title="What to discuss carefully" items={report.concerns} />
        <ReportList
          title="Questions before marriage"
          items={report.questionsToAsk}
        />
        <HighlightList
          title="Best things to feel positive about"
          items={report.strengths}
        />
        <div className="recommendation">
          <h3>Recommendation</h3>
          <p>{report.recommendation}</p>
          <small>{report.disclaimer}</small>
        </div>
      </div>
    </section>
  );
}

function AiText({ text }) {
  const blocks = text
    .split(/\n{2,}/)
    .map((block) => block.trim())
    .filter(Boolean);

  return (
    <div className="ai-text">
      {blocks.map((block) => {
        const lines = block
          .split("\n")
          .map((line) => line.trim())
          .filter(Boolean);
        const isBulletList = lines.every((line) => /^[-*]\s+/.test(line));

        if (isBulletList) {
          return (
            <ul key={block}>
              {lines.map((line) => (
                <li key={line}>
                  {renderInlineAiText(line.replace(/^[-*]\s+/, ""))}
                </li>
              ))}
            </ul>
          );
        }

        return (
          <p key={block}>{renderInlineAiText(block.replace(/^[-*]\s+/, ""))}</p>
        );
      })}
    </div>
  );
}

function renderInlineAiText(text) {
  const parts = text.split(/(\*\*[^*]+\*\*)/g);
  return parts.map((part) => {
    if (part.startsWith("**") && part.endsWith("**")) {
      return <strong key={part}>{part.slice(2, -2)}</strong>;
    }
    return part.replace(/\*/g, "");
  });
}

function HighlightList({ title, items }) {
  return (
    <div className="highlight-list">
      <h3>{title}</h3>
      <div>
        {items.map((item) => (
          <span key={item}>{item}</span>
        ))}
      </div>
    </div>
  );
}

function ReportList({ title, items }) {
  return (
    <div className="report-list">
      <h3>{title}</h3>
      <ul>
        {items.map((item) => (
          <li key={item}>{item}</li>
        ))}
      </ul>
    </div>
  );
}

function buildFallbackMatches(allProfiles, profileId) {
  const loggedInProfile = allProfiles.find(
    (candidate) => candidate.id === Number(profileId),
  );

  return allProfiles
    .filter((candidate) => candidate.id !== Number(profileId))
    .filter((candidate) => isOppositeGender(loggedInProfile, candidate))
    .map((candidate, index) => ({
      profile: candidate,
      overallScore: [88, 82, 78, 73, 69][index % 5],
      confidence: "Preview",
      topReasons: [
        `${candidate.city} / ${candidate.profession}`,
        `${candidate.familyType} and ${candidate.diet} lifestyle`,
        "Discuss location, money, parents and conflict style before engagement.",
      ],
      recommendation: "Open profile and ask AI for a detailed report.",
    }))
    .sort((left, right) => right.overallScore - left.overallScore);
}

function buildFallbackReport(user, match) {
  return {
    overallScore: 78,
    confidence: "Local preview",
    summary: `${user.fullName} and ${match.fullName} need a profile-specific backend report. This preview shows the exact report format.`,
    statistics: [
      {
        label: "Location",
        score: 74,
        reason:
          "City and relocation should be discussed before the family meeting.",
      },
      {
        label: "Lifestyle",
        score: 85,
        reason: "Daily habits and values appear workable from the profile.",
      },
      {
        label: "Family expectations",
        score: 70,
        reason: "Discuss parent-care, festivals and decision-making.",
      },
      {
        label: "Communication readiness",
        score: 82,
        reason: "Use the questions below before saying yes.",
      },
    ],
    strengths: [
      "Profiles include career, family and life-goal signals.",
      "The report is designed for discussion, not blind approval.",
    ],
    concerns: [
      "Live API is not connected yet.",
      "Profile text is incomplete for emotional conflict style.",
    ],
    questionsToAsk: [
      "Where will we live after marriage?",
      "How will money, savings and family support work?",
      "What happens when we disagree strongly?",
    ],
    recommendation:
      "Start with a serious conversation. Let AI prepare questions, then trust real-world meetings.",
    disclaimer:
      "This report estimates compatibility from profile data. It cannot predict marriage success.",
  };
}

function profile(
  id,
  fullName,
  gender,
  age,
  heightCm,
  city,
  state,
  religion,
  community,
  profession,
  education,
  annualIncome,
  diet,
  familyType,
  wantsChildren,
  relocation,
  about,
  lifeGoals,
) {
  return {
    id,
    fullName,
    gender,
    age,
    heightCm,
    city,
    state,
    religion,
    community,
    profession,
    education,
    annualIncome,
    diet,
    familyType,
    wantsChildren,
    relocation,
    about,
    lifeGoals,
    photo: photoForProfile({ id, gender }),
  };
}

function withPhotos(apiProfiles) {
  return apiProfiles.map((apiProfile) => withPhoto(apiProfile));
}

function withPhoto(profileToDecorate) {
  return {
    ...profileToDecorate,
    photo: photoForProfile(profileToDecorate),
  };
}

function photoForProfile(profileToDecorate, fallbackIndex = 0) {
  if (profilePhotoByName[profileToDecorate.fullName]) {
    return profilePhotoByName[profileToDecorate.fullName];
  }

  const photos = profileToDecorate.gender?.toLowerCase() === "male"
    ? maleProfilePhotos
    : femaleProfilePhotos;
  const index = Math.max(0, (profileToDecorate.id ?? fallbackIndex + 1) - 1);
  return photos[index % photos.length];
}

function isOppositeGender(one, two) {
  return one?.gender && two?.gender && one.gender.toLowerCase() !== two.gender.toLowerCase();
}

function upsertProfile(currentProfiles, profileToSave) {
  const exists = currentProfiles.some(
    (profile) => profile.id === profileToSave.id,
  );
  if (exists) {
    return currentProfiles.map((profile) =>
      profile.id === profileToSave.id ? profileToSave : profile,
    );
  }
  return [...currentProfiles, profileToSave];
}

function demoLogin(credentials) {
  const demoAccounts = {
    "aarav@example.com": 1,
    "ananya@example.com": 2,
    "kabir@example.com": 3,
    "meera@example.com": 4,
    "rohan@example.com": 5,
    "priya@example.com": 6,
  };
  const profileId = demoAccounts[credentials.email.toLowerCase()];
  if (!profileId || credentials.password !== "Password@123") {
    return null;
  }
  return fallbackProfiles.find(
    (profileToFind) => profileToFind.id === profileId,
  );
}

function withPhotosOnRequest(request) {
  return {
    ...request,
    senderProfile: withPhoto(request.senderProfile),
    receiverProfile: withPhoto(request.receiverProfile),
  };
}

function withPhotosOnConversation(conversation) {
  return {
    ...conversation,
    profileOne: withPhoto(conversation.profileOne),
    profileTwo: withPhoto(conversation.profileTwo),
  };
}

function requestBetween(requests, profileOneId, profileTwoId) {
  return requests.find(
    (request) =>
      (request.senderProfile.id === profileOneId &&
        request.receiverProfile.id === profileTwoId) ||
      (request.senderProfile.id === profileTwoId &&
        request.receiverProfile.id === profileOneId),
  );
}

function conversationBetween(conversations, profileOneId, profileTwoId) {
  return conversations.find(
    (conversation) =>
      (conversation.profileOne.id === profileOneId &&
        conversation.profileTwo.id === profileTwoId) ||
      (conversation.profileOne.id === profileTwoId &&
        conversation.profileTwo.id === profileOneId),
  );
}

function otherConversationProfile(conversation, profileId) {
  return conversation.profileOne.id === profileId
    ? conversation.profileTwo
    : conversation.profileOne;
}

function upsertInterestRequest(requests, requestToSave) {
  const exists = requests.some((request) => request.id === requestToSave.id);
  if (!exists) {
    return [requestToSave, ...requests];
  }
  return requests.map((request) =>
    request.id === requestToSave.id ? requestToSave : request,
  );
}

function upsertConversation(conversations, conversationToSave) {
  const exists = conversations.some(
    (conversation) => conversation.id === conversationToSave.id,
  );
  if (!exists) {
    return [conversationToSave, ...conversations];
  }
  return conversations.map((conversation) =>
    conversation.id === conversationToSave.id
      ? conversationToSave
      : conversation,
  );
}

function upsertChatMessage(messages, messageToSave) {
  const exists = messages.some((message) => message.id === messageToSave.id);
  if (exists) {
    return messages.map((message) =>
      message.id === messageToSave.id ? messageToSave : message,
    );
  }
  return [...messages, messageToSave];
}

function playNotificationTone(type) {
  const AudioContext = window.AudioContext ?? window.webkitAudioContext;
  if (!AudioContext) {
    return;
  }

  try {
    const context = new AudioContext();
    const notes = type === "request" ? [660, 880] : [760];
    notes.forEach((frequency, index) => {
      const startAt = context.currentTime + index * 0.12;
      const oscillator = context.createOscillator();
      const gain = context.createGain();

      oscillator.type = "sine";
      oscillator.frequency.setValueAtTime(frequency, startAt);
      gain.gain.setValueAtTime(0.0001, startAt);
      gain.gain.exponentialRampToValueAtTime(0.13, startAt + 0.02);
      gain.gain.exponentialRampToValueAtTime(0.0001, startAt + 0.22);

      oscillator.connect(gain);
      gain.connect(context.destination);
      oscillator.start(startAt);
      oscillator.stop(startAt + 0.24);
    });
    window.setTimeout(() => context.close(), 700);
  } catch {
    // Browsers may block notification sound until the user interacts with the page.
  }
}

function replaceLastAssistantMessage(messages, content) {
  const nextMessages = [...messages];
  for (let index = nextMessages.length - 1; index >= 0; index -= 1) {
    if (nextMessages[index].role === "assistant") {
      nextMessages[index] = { role: "assistant", content };
      return nextMessages;
    }
  }
  return [...nextMessages, { role: "assistant", content }];
}

function firstName(fullName) {
  return fullName.split(" ")[0];
}

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>,
);
