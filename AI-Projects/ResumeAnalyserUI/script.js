const API_BASE = "http://localhost:8080/api/resume";

/* ── DOM refs ── */
const fileInput       = document.querySelector("#resumeFile");
const uploadButton    = document.querySelector("#uploadButton");
const uploadBox       = document.querySelector("#uploadBox");
const fileName        = document.querySelector("#fileName");
const jobDescription  = document.querySelector("#jobDescription");
const jdGroup         = document.querySelector("#jdGroup");
const analyzeButton   = document.querySelector("#analyzeButton");
const clearButton     = document.querySelector("#clearButton");
const modeButtons     = document.querySelectorAll(".mode-button");
const statusPill      = document.querySelector("#statusPill");
const statusText      = document.querySelector("#statusText");
const emptyState      = document.querySelector("#emptyState");
const resultContent   = document.querySelector("#resultContent");
const resultTitle     = document.querySelector("#resultTitle");
const resultBody      = document.querySelector("#resultBody");
const copyButton      = document.querySelector("#copyButton");
const scoreValue      = document.querySelector("#scoreValue");      // hidden sr-only
const scoreCaption    = document.querySelector("#scoreCaption");
const gaugeFill       = document.querySelector("#gaugeFill");
const gaugeNum        = document.querySelector("#gaugeNum");

/* Inject SVG gradient def for gauge */
(function injectGaugeDefs() {
  const svg = document.querySelector(".gauge-svg");
  if (!svg) return;
  const defs = document.createElementNS("http://www.w3.org/2000/svg", "defs");
  defs.innerHTML = `
    <linearGradient id="gaugeGrad" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%"   stop-color="#f5d78e"/>
      <stop offset="100%" stop-color="#c9832a"/>
    </linearGradient>
  `;
  svg.prepend(defs);
})();

const CIRCUMFERENCE = 2 * Math.PI * 56; // r=56 → ≈ 351.86

let selectedMode = "resume";
let selectedFile;
let latestText = "";

/* ── Utilities ── */
function setStatus(text, isError = false) {
  statusText.textContent = text;
  statusPill.classList.toggle("error", isError);
}

function showResults() {
  emptyState.classList.add("is-hidden");
  resultContent.classList.remove("is-hidden");
}

function showEmpty() {
  emptyState.classList.remove("is-hidden");
  resultContent.classList.add("is-hidden");
}

function setMode(mode) {
  selectedMode = mode;
  modeButtons.forEach((btn) => {
    btn.classList.toggle("active", btn.dataset.mode === mode);
  });

  const isAts = mode === "ats";
  jdGroup.classList.toggle("hidden", !isAts);

  const btnLabel = analyzeButton.querySelector(".btn-label");
  if (btnLabel) btnLabel.textContent = isAts ? "Run ATS Check" : "Analyze Resume";

  if (resultTitle) {
    resultTitle.textContent = isAts ? "ATS Match Report" : "Resume Review";
  }
}

function setFile(file) {
  if (!file) return;
  selectedFile = file;
  fileName.textContent = `${file.name}  ·  ${formatSize(file.size)}`;
  fileName.style.color = "#d4983a";
}

function formatSize(bytes) {
  if (bytes < 1024 * 1024) return `${Math.round(bytes / 1024)} KB`;
  return `${(bytes / (1024 * 1024)).toFixed(1)} MB`;
}

function extractJson(text) {
  const cleaned = text
    .replace(/^```json\s*/i, "")
    .replace(/^```\s*/i, "")
    .replace(/```$/i, "")
    .trim();

  try {
    return JSON.parse(cleaned);
  } catch {
    const match = cleaned.match(/\{[\s\S]*\}/);
    if (!match) return null;
    try {
      return JSON.parse(match[0]);
    } catch {
      return null;
    }
  }
}

function titleize(key) {
  return key.replace(/([A-Z])/g, " $1").replace(/^./, (c) => c.toUpperCase());
}

function escapeHtml(value) {
  return String(value)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}

function renderValue(value) {
  if (Array.isArray(value)) {
    const items = value.map((item) => {
      if (item && typeof item === "object") {
        return `<li>${escapeHtml(Object.values(item).join(" "))}</li>`;
      }
      return `<li>${escapeHtml(String(item))}</li>`;
    }).join("");
    return `<ul>${items}</ul>`;
  }

  if (value && typeof value === "object") {
    const items = Object.entries(value).map(([k, v]) =>
      `<li><strong>${escapeHtml(titleize(k))}:</strong> ${escapeHtml(String(v))}</li>`
    ).join("");
    return `<ul>${items}</ul>`;
  }

  return `<p>${escapeHtml(String(value))}</p>`;
}

/* ── Gauge animation ── */
function animateGauge(pct) {
  if (!gaugeFill || !gaugeNum) return;
  const filled = Math.min(100, Math.max(0, pct)) / 100 * CIRCUMFERENCE;
  gaugeFill.style.strokeDasharray = `${filled} ${CIRCUMFERENCE - filled}`;
}

function resetGauge() {
  if (!gaugeFill) return;
  gaugeFill.style.strokeDasharray = `0 ${CIRCUMFERENCE}`;
  if (gaugeNum) gaugeNum.textContent = "--";
}

/* ── Score update ── */
function updateScore(score, mode) {
  if (score === null || score === undefined || score === "") {
    scoreValue.textContent = "--";
    scoreCaption.textContent = mode === "ats" ? "ATS score unavailable" : "Quality score unavailable";
    resetGauge();
    return;
  }

  const numericScore = Number(score);

  if (Number.isNaN(numericScore)) {
    scoreValue.textContent = String(score);
    if (gaugeNum) gaugeNum.textContent = String(score);
    scoreCaption.textContent = mode === "ats" ? "ATS match score" : "Resume quality score";
    return;
  }

  const display = mode === "ats" ? Math.round(numericScore) : numericScore;
  scoreValue.textContent = display;

  // Animate gauge
  if (gaugeNum) gaugeNum.textContent = display;
  animateGauge(numericScore);

  scoreCaption.textContent = mode === "ats" ? "ATS match score" : "Resume quality score";
}

/* ── Loading state ── */
function setLoadingResult(mode) {
  latestText = "";
  showResults();
  resultTitle.textContent = mode === "ats" ? "ATS Match Report" : "Resume Review";
  resultBody.innerHTML = `
    <div class="json-grid">
      <article class="result-card loading-card">
        <div class="loading-line"></div>
        <div class="loading-line"></div>
        <div class="loading-line"></div>
      </article>
      <article class="result-card loading-card">
        <div class="loading-line"></div>
        <div class="loading-line"></div>
        <div class="loading-line"></div>
      </article>
    </div>`;
}

/* ── Render result ── */
function renderResult(text, mode) {
  const normalized = text == null ? "" : (typeof text === "string" ? text : JSON.stringify(text, null, 2));
  latestText = normalized;
  showResults();
  resultTitle.textContent = mode === "ats" ? "ATS Match Report" : "Resume Review";

  const parsed = extractJson(normalized);
  if (!parsed) {
    resultBody.innerHTML = `<pre class="raw-result">${escapeHtml(normalized || "No analysis was returned.")}</pre>`;
    updateScore(null, mode);
    return;
  }

  const cards = Object.entries(parsed).map(([key, value], i) => `
    <article class="result-card" style="animation-delay:${i * 60}ms">
      <h3>${escapeHtml(titleize(key))}</h3>
      ${renderValue(value)}
    </article>`).join("");

  resultBody.innerHTML = `<div class="json-grid">${cards}</div>`;

  const score =
    parsed.atsScore ?? parsed.ats_score ??
    parsed.atsMatchScore ?? parsed.ats_match_score ??
    parsed.matchScore ?? parsed.match_score ??
    parsed.resumeQuality ?? parsed.resume_quality ??
    parsed.overallResumeQualityRating ?? parsed.overall_resume_quality_rating ??
    parsed.overall_resume_quality ?? parsed.score ?? parsed.rating;

  updateScore(score, mode);
}

/* ── Analyze ── */
async function analyze() {
  if (!selectedFile) {
    setStatus("Choose a resume first", true);
    return;
  }

  if (selectedMode === "ats" && !jobDescription.value.trim()) {
    setStatus("Paste a job description", true);
    jobDescription.focus();
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile);

  let endpoint = `${API_BASE}/analyze`;
  if (selectedMode === "ats") {
    endpoint = `${API_BASE}/ats-check`;
    formData.append("jd", jobDescription.value.trim());
  }

  analyzeButton.disabled = true;
  setStatus("Analyzing…");
  setLoadingResult(selectedMode);
  resetGauge();

  try {
    const response = await fetch(endpoint, { method: "POST", body: formData });
    if (!response.ok) throw new Error(`Request failed — status ${response.status}`);

    const data = await response.json();
    const text = selectedMode === "ats" ? data.atsReport : data.analysis;
    renderResult(text ?? data, selectedMode);
    setStatus("Complete");
  } catch (error) {
    setStatus("API error", true);
    renderResult(
      `Could not reach the ResumeIQ API. Start the Spring Boot app on port 8080 and try again.\n\n${error.message}`,
      selectedMode
    );
  } finally {
    analyzeButton.disabled = false;
  }
}

/* ── Clear ── */
function clearAll() {
  fileInput.value = "";
  selectedFile = null;
  latestText = "";
  fileName.textContent = "or click to browse — PDF, DOCX, TXT, RTF";
  fileName.style.color = "";
  jobDescription.value = "";
  showEmpty();
  resultBody.innerHTML = "";
  scoreCaption.textContent = "Upload a resume to begin";
  resetGauge();
  setStatus("Ready");
}

/* ── Event listeners ── */
uploadButton.addEventListener("click", (e) => { e.stopPropagation(); fileInput.click(); });
uploadBox.addEventListener("click", () => fileInput.click());
fileInput.addEventListener("change", () => setFile(fileInput.files[0]));

uploadBox.addEventListener("dragover", (e) => {
  e.preventDefault();
  uploadBox.classList.add("dragging");
});
uploadBox.addEventListener("dragleave", () => uploadBox.classList.remove("dragging"));
uploadBox.addEventListener("drop", (e) => {
  e.preventDefault();
  uploadBox.classList.remove("dragging");
  setFile(e.dataTransfer.files[0]);
});

modeButtons.forEach((btn) => btn.addEventListener("click", () => setMode(btn.dataset.mode)));

analyzeButton.addEventListener("click", analyze);
clearButton.addEventListener("click", clearAll);

copyButton.addEventListener("click", async () => {
  if (!latestText) return;
  await navigator.clipboard.writeText(latestText);
  copyButton.textContent = "Copied!";
  setTimeout(() => {
    copyButton.innerHTML = `<svg viewBox="0 0 16 16" fill="none" width="14" height="14">
      <rect x="5.5" y="5.5" width="9" height="9" rx="2" stroke="currentColor" stroke-width="1.25"/>
      <path d="M3 10.5V3h7.5" stroke="currentColor" stroke-width="1.25" stroke-linecap="round" stroke-linejoin="round"/>
    </svg> Copy`;
  }, 1400);
});

/* ── Init ── */
setMode("resume");