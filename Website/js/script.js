"use strict";

/* ======================================
   RoadPulse Lite
   script.js
====================================== */

document.addEventListener("DOMContentLoaded", function () {

    initializeWebsite();

});

function initializeWebsite() {

    smoothScrolling();

    activeNavigation();

    contactForm();

    createBackToTopButton();

    revealSections();

}

/* ======================================
   Smooth Scrolling
====================================== */

function smoothScrolling() {

    const links = document.querySelectorAll('a[href^="#"]');

    links.forEach(function (link) {

        link.addEventListener("click", function (event) {

            const targetId = this.getAttribute("href");

            if (targetId === "#") {

                return;

            }

            const targetSection = document.querySelector(targetId);

            if (targetSection) {

                event.preventDefault();

                targetSection.scrollIntoView({

                    behavior: "smooth"

                });

            }

        });

    });

}

/* ======================================
   Active Navigation
====================================== */

function activeNavigation() {

    const currentPage = window.location.pathname.split("/").pop();

    const navLinks = document.querySelectorAll("nav a");

    navLinks.forEach(function (link) {

        const page = link.getAttribute("href");

        if (page === currentPage || (currentPage === "" && page === "index.html")) {

            link.style.color = "#38bdf8";
            link.style.fontWeight = "700";

        }

    });

}

/* ======================================
   Contact Form Validation
====================================== */

function contactForm() {

    const form = document.querySelector("form");

    if (!form) {

        return;

    }

    form.addEventListener("submit", function (event) {

        event.preventDefault();

        const name = document.getElementById("name").value.trim();

        const email = document.getElementById("email").value.trim();

        const subject = document.getElementById("subject").value.trim();

        const message = document.getElementById("message").value.trim();

        if (name === "" ||
            email === "" ||
            subject === "" ||
            message === "") {

            alert("Please fill in all fields.");

            return;

        }

        if (!email.includes("@") || !email.includes(".")) {

            alert("Please enter a valid email address.");

            return;

        }

        alert("Thank you! Your message has been submitted.");

        form.reset();

    });

}

/* ======================================
   Back To Top Button
====================================== */

function createBackToTopButton() {

    const button = document.createElement("button");

    button.innerHTML = "↑";

    button.id = "topButton";

    button.style.position = "fixed";
    button.style.right = "20px";
    button.style.bottom = "20px";
    button.style.padding = "12px 18px";
    button.style.fontSize = "20px";
    button.style.cursor = "pointer";
    button.style.border = "none";
    button.style.borderRadius = "8px";
    button.style.backgroundColor = "#0284c7";
    button.style.color = "#ffffff";
    button.style.display = "none";
    button.style.zIndex = "1000";

    document.body.appendChild(button);

    window.addEventListener("scroll", function () {

        if (window.scrollY > 300) {

            button.style.display = "block";

        } else {

            button.style.display = "none";

        }

    });

    button.addEventListener("click", function () {

        window.scrollTo({

            top: 0,

            behavior: "smooth"

        });

    });

}

/* ======================================
   Reveal Sections On Scroll
====================================== */

function revealSections() {

    const sections = document.querySelectorAll("section");

    sections.forEach(function (section) {

        section.style.opacity = "0";
        section.style.transform = "translateY(40px)";
        section.style.transition = "all 0.8s ease";

    });

    reveal();

    window.addEventListener("scroll", reveal);

    function reveal() {

        sections.forEach(function (section) {

            const sectionTop = section.getBoundingClientRect().top;

            const windowHeight = window.innerHeight;

            if (sectionTop < windowHeight - 100) {

                section.style.opacity = "1";
                section.style.transform = "translateY(0)";

            }

        });

    }

}