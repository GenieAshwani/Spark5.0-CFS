const API_URL = "http://localhost:8080/api/chat";

const form = document.querySelector("#chatForm");
const input = document.querySelector("#questionInput");
const messages = document.querySelector("#messages");
const sendButton = document.querySelector("#sendButton");
const statusPill = document.querySelector("#statusPill");
const promptChips = document.querySelectorAll(".prompt-chip");

function setStatus(text, isError = false) {
  statusPill.querySelector(".status-text").textContent = text;
  statusPill.classList.toggle("error", isError);
}

function scrollToLatest() {
  messages.scrollTop = messages.scrollHeight;
}

function resizeInput() {
  input.style.height = "auto";
  input.style.height = `${Math.min(input.scrollHeight, 150)}px`;
}

function addMessage(role, text, extraClass = "") {
  const article = document.createElement("article");
  article.className = `message ${role}-message ${extraClass}`.trim();

  const avatar = document.createElement("div");
  avatar.className = "avatar";
  avatar.textContent = role === "user" ? "You" : "AI";

  const bubble = document.createElement("div");
  bubble.className = "bubble";

  const label = document.createElement("span");
  label.className = "message-label";
  label.textContent = role === "user" ? "You" : "AskAi Assistant";

  const paragraph = document.createElement("p");
  paragraph.textContent = text;

  bubble.appendChild(label);
  bubble.appendChild(paragraph);
  article.append(avatar, bubble);
  messages.appendChild(article);
  scrollToLatest();

  return article;
}

async function askAi(question) {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ question })
  });

  if (!response.ok) {
    throw new Error(`Request failed with status ${response.status}`);
  }

  return response.json();
}

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const question = input.value.trim();
  if (!question) {
    return;
  }

  addMessage("user", question);
  input.value = "";
  resizeInput();
  input.focus();

  sendButton.disabled = true;
  setStatus("Thinking");
  const typingMessage = addMessage("assistant", "Thinking", "typing");

  try {
    const data = await askAi(question);
    typingMessage.remove();
    addMessage("assistant", data.answer || "No answer was returned.");
    setStatus("Ready");
  } catch (error) {
    typingMessage.remove();
    addMessage("assistant", "I could not reach the AskAi API. Start the Spring Boot app on port 8080 and try again.");
    setStatus("API offline", true);
  } finally {
    sendButton.disabled = false;
  }
});

input.addEventListener("input", resizeInput);

input.addEventListener("keydown", (event) => {
  if (event.key === "Enter" && !event.shiftKey) {
    event.preventDefault();
    form.requestSubmit();
  }
});

promptChips.forEach((chip) => {
  chip.addEventListener("click", () => {
    input.value = chip.textContent.trim();
    resizeInput();
    input.focus();
  });
});
