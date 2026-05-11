const SOCKET_URL = "ws://localhost:8080/chat";

const messages = document.querySelector("#messages");
const form = document.querySelector("#chatForm");
const input = document.querySelector("#messageInput");
const sendButton = document.querySelector("#sendButton");
const newChatButton = document.querySelector(".new-chat-button");
const connectButton = document.querySelector("#connectButton");
const disconnectButton = document.querySelector("#disconnectButton");
const statusPill = document.querySelector("#statusPill");
const statusText = document.querySelector("#statusText");
const promptChips = document.querySelectorAll(".prompt-chip");

let socket;
let pendingNotice;
let queuedMessage;

function setStatus(text, state = "idle") {
  statusText.textContent = text;
  statusPill.classList.toggle("connected", state === "connected");
  statusPill.classList.toggle("error", state === "error");
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
  avatar.textContent = role === "user" ? "You" : "CB";

  const bubble = document.createElement("div");
  bubble.className = "bubble";

  const label = document.createElement("span");
  label.className = "message-label";
  label.textContent = role === "user" ? "You" : "ChatBot";

  const paragraph = document.createElement("p");
  paragraph.textContent = text;

  bubble.append(label, paragraph);
  article.append(avatar, bubble);
  messages.appendChild(article);
  scrollToLatest();

  return article;
}

function resetChat() {
  removePendingNotice();
  messages.innerHTML = "";
  addMessage("bot", "Start the Spring Boot ChatBot app, connect to the socket, then send a message.");
  input.value = "";
  resizeInput();
  input.focus();
}

function removePendingNotice() {
  if (pendingNotice) {
    pendingNotice.remove();
    pendingNotice = null;
  }
}

function connectSocket() {
  if (socket && socket.readyState === WebSocket.OPEN) {
    sendQueuedMessage();
    return;
  }

  if (socket && socket.readyState === WebSocket.CONNECTING) {
    return;
  }

  setStatus("Connecting");
  socket = new WebSocket(SOCKET_URL);

  socket.addEventListener("open", () => {
    setStatus("Connected", "connected");
    if (queuedMessage) {
      sendQueuedMessage();
    } else {
      addMessage("bot", "Socket connected. You can send messages now.");
    }
  });

  socket.addEventListener("message", (event) => {
    removePendingNotice();
    addMessage("bot", event.data || "Empty response received.");
  });

  socket.addEventListener("close", () => {
    removePendingNotice();
    setStatus("Disconnected");
  });

  socket.addEventListener("error", () => {
    queuedMessage = "";
    removePendingNotice();
    setStatus("Connection error", "error");
    addMessage("bot", "Could not connect to the WebSocket. Start the Spring Boot ChatBot app and try again.");
  });
}

function disconnectSocket() {
  if (socket) {
    socket.close();
  }
}

function sendMessage(text) {
  if (!socket || socket.readyState !== WebSocket.OPEN) {
    queuedMessage = text;
    addMessage("user", text);
    removePendingNotice();
    pendingNotice = addMessage("bot", "Connecting to ChatBot...", "typing");
    connectSocket();
    return;
  }

  sendToSocket(text);
}

function sendQueuedMessage() {
  if (!queuedMessage || !socket || socket.readyState !== WebSocket.OPEN) {
    return;
  }

  const text = queuedMessage;
  queuedMessage = "";
  sendToSocket(text);
}

function sendToSocket(text) {
  socket.send(text);
  removePendingNotice();
  pendingNotice = addMessage("bot", "Message sent. Waiting for the backend to send a response.", "typing");
}

connectButton.addEventListener("click", connectSocket);
disconnectButton.addEventListener("click", disconnectSocket);
newChatButton.addEventListener("click", resetChat);

form.addEventListener("submit", (event) => {
  event.preventDefault();

  const text = input.value.trim();
  if (!text) {
    return;
  }

  input.value = "";
  resizeInput();
  input.focus();
  sendMessage(text);
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
