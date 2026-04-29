const API_BASE_URL = "http://localhost:8080/api";

const courseList = document.querySelector("#courseList");
const statusMessage = document.querySelector("#statusMessage");
const studentForm = document.querySelector("#studentForm");
const summaryTitle = document.querySelector("#summaryTitle");
const summaryDescription = document.querySelector("#summaryDescription");
const summaryStudent = document.querySelector("#summaryStudent");
const summaryEmail = document.querySelector("#summaryEmail");
const summaryPrice = document.querySelector("#summaryPrice");

let courses = [];
let razorpayKeyId = "";
let selectedCourseId = "";

const courseDetails = {
    "java-spring": {
        label: "Backend",
        duration: "6 weeks",
        level: "Intermediate",
        accent: "linear-gradient(135deg, #0d7a63, #215c9e)"
    },
    "kafka-basics": {
        label: "Messaging",
        duration: "4 weeks",
        level: "Beginner",
        accent: "linear-gradient(135deg, #d99a21, #d85f45)"
    },
    "fullstack-web": {
        label: "Full stack",
        duration: "8 weeks",
        level: "Beginner",
        accent: "linear-gradient(135deg, #215c9e, #6f4bb2)"
    }
};

function setStatus(message, type = "") {
    statusMessage.textContent = message;
    statusMessage.className = `status-message ${type}`.trim();
}

function formatRupees(amountInPaise) {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        maximumFractionDigits: 0
    }).format(amountInPaise);
}

async function fetchJson(url, options) {
    const response = await fetch(url, {
        headers: { "Content-Type": "application/json" },
        ...options
    });

    if (!response.ok) {
        const error = await response.json().catch(() => ({ message: "Request failed" }));
        throw new Error(error.message || "Request failed");
    }

    return response.json();
}

function renderCourses() {
    courseList.innerHTML = courses.map(course => `
        <article class="course-card ${course.id === selectedCourseId ? "selected" : ""}" style="--course-bg: ${getCourseDetail(course).accent}">
            <div class="course-art">
                <span>${getCourseDetail(course).label}</span>
            </div>
            <div class="course-body">
                <h3>${course.title}</h3>
                <p>${course.description}</p>
                <div class="course-meta">
                    <span>${getCourseDetail(course).duration}</span>
                    <span>${getCourseDetail(course).level}</span>
                    <span>Email confirmation</span>
                </div>
            </div>
            <div class="course-footer">
                <div class="price">${formatRupees(course.amountInPaise)}</div>
                <button type="button" data-course-id="${course.id}">Pay now</button>
            </div>
        </article>
    `).join("");
}

function getCourseDetail(course) {
    return courseDetails[course.id] || {
        label: "Course",
        duration: "Self paced",
        level: "Open",
        accent: "linear-gradient(135deg, #0d7a63, #215c9e)"
    };
}

function getStudentDetails() {
    if (!studentForm.reportValidity()) {
        return null;
    }

    return {
        studentName: document.querySelector("#studentName").value.trim(),
        email: document.querySelector("#email").value.trim()
    };
}

function updateSummary() {
    const selectedCourse = courses.find(course => course.id === selectedCourseId);
    const studentName = document.querySelector("#studentName").value.trim();
    const email = document.querySelector("#email").value.trim();

    summaryStudent.textContent = studentName || "Not added";
    summaryEmail.textContent = email || "Not added";

    if (!selectedCourse) {
        summaryTitle.textContent = "No course selected";
        summaryDescription.textContent = "Select a course to see checkout details.";
        summaryPrice.textContent = "₹0";
        return;
    }

    summaryTitle.textContent = selectedCourse.title;
    summaryDescription.textContent = selectedCourse.description;
    summaryPrice.textContent = formatRupees(selectedCourse.amountInPaise);
}

async function createOrder(courseId, student) {
    return fetchJson(`${API_BASE_URL}/payments/orders`, {
        method: "POST",
        body: JSON.stringify({ ...student, courseId })
    });
}

async function verifyPayment(order, response) {
    return fetchJson(`${API_BASE_URL}/payments/verify`, {
        method: "POST",
        body: JSON.stringify({
            razorpayOrderId: response.razorpay_order_id,
            razorpayPaymentId: response.razorpay_payment_id,
            razorpaySignature: response.razorpay_signature,
            courseId: order.courseId,
            studentName: order.studentName,
            email: order.email
        })
    });
}

async function startPayment(courseId, button) {
    selectedCourseId = courseId;
    renderCourses();
    updateSummary();
    const activeButton = courseList.querySelector(`button[data-course-id="${courseId}"]`) || button;

    const student = getStudentDetails();
    if (!student) {
        return;
    }

    activeButton.disabled = true;
    setStatus("Creating Razorpay order...");

    try {
        const order = await createOrder(courseId, student);
        const options = {
            key: razorpayKeyId || order.keyId,
            amount: order.amountInPaise,
            currency: order.currency,
            name: "Course Enrollment",
            description: order.courseTitle,
            order_id: order.orderId,
            prefill: {
                name: order.studentName,
                email: order.email
            },
            theme: {
                color: "#0e7c66"
            },
            handler: async function (paymentResponse) {
                setStatus("Verifying payment and pushing Kafka notification...");
                await verifyPayment(order, paymentResponse);
                setStatus("Payment verified. Email notification event sent to Kafka.", "success");
            },
            modal: {
                ondismiss: function () {
                    setStatus("Payment popup closed.");
                }
            }
        };

        const razorpay = new Razorpay(options);
        razorpay.open();
    } catch (error) {
        setStatus(error.message, "error");
    } finally {
        activeButton.disabled = false;
    }
}

courseList.addEventListener("click", event => {
    const button = event.target.closest("button[data-course-id]");
    if (!button) {
        return;
    }

    startPayment(button.dataset.courseId, button);
});

studentForm.addEventListener("input", updateSummary);

async function init() {
    try {
        setStatus("Loading courses...");
        const [config, courseData] = await Promise.all([
            fetchJson(`${API_BASE_URL}/config`),
            fetchJson(`${API_BASE_URL}/courses`)
        ]);

        razorpayKeyId = config.razorpayKeyId;
        courses = courseData;
        selectedCourseId = courses[0]?.id || "";
        renderCourses();
        updateSummary();
        setStatus("Ready.");
    } catch (error) {
        setStatus(error.message, "error");
    }
}

init();
