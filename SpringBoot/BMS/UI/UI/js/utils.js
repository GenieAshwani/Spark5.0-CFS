// ===== TOAST NOTIFICATIONS =====
function showToast(message, type = 'info') {
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.textContent = message;
    container.appendChild(toast);
    setTimeout(() => toast.remove(), 3000);
}

// ===== SESSION (simple localStorage) =====
function setLoggedInUser(user) {
    localStorage.setItem('bms_user', JSON.stringify(user));
    updateNavUser();
}

function getLoggedInUser() {
    const u = localStorage.getItem('bms_user');
    return u ? JSON.parse(u) : null;
}

function logout() {
    localStorage.removeItem('bms_user');
    updateNavUser();
    showToast('Logged out successfully', 'success');
    // Redirect to home if on a protected page
    if (window.location.pathname.includes('bookings')) {
        window.location.href = '../index.html';
    }
}

function updateNavUser() {
    const navUser = document.getElementById('nav-user');
    if (!navUser) return;
    const user = getLoggedInUser();
    if (user) {
        navUser.innerHTML = `
            <span style="color: var(--text-muted); font-size: 0.9rem;">Hi, <strong style="color: var(--white)">${user.name}</strong></span>
            <button class="btn btn-sm btn-outline" onclick="logout()">Logout</button>
        `;
    } else {
        navUser.innerHTML = `
            <a href="${getPagePath('pages/login.html')}" class="btn btn-sm btn-outline">Login</a>
            <a href="${getPagePath('pages/register.html')}" class="btn btn-sm btn-primary">Sign Up</a>
        `;
    }
}

// Helper to resolve paths whether we're in root or pages/
function getPagePath(path) {
    if (window.location.pathname.includes('/pages/')) {
        return path.replace('pages/', '').replace('../', '');
        // we're inside pages/, so relative paths differ
    }
    return path;
}

function getBasePath() {
    return window.location.pathname.includes('/pages/') ? '../' : './';
}

// ===== LOADING SPINNER =====
function showLoading(containerId) {
    const el = document.getElementById(containerId);
    if (el) el.innerHTML = '<div class="loading-center"><div class="spinner"></div></div>';
}

function showEmpty(containerId, message = 'No data found') {
    const el = document.getElementById(containerId);
    if (el) el.innerHTML = `<div class="empty-state"><div class="icon">🎬</div><p>${message}</p></div>`;
}

// ===== MODAL HELPERS =====
function openModal(modalId) {
    document.getElementById(modalId)?.classList.add('active');
}

function closeModal(modalId) {
    document.getElementById(modalId)?.classList.remove('active');
}

// Close modal on overlay click
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('modal-overlay')) {
        e.target.classList.remove('active');
    }
});

// ===== ON LOAD =====
document.addEventListener('DOMContentLoaded', () => {
    updateNavUser();
});
