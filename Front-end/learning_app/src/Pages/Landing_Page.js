// LandingPage.jsx
import React from "react";
import "../Styles/LandingPage.scss";
import Landing_img from "../Assets/img/Landing_img.jpg";
import {
  FaBookOpen,
  FaChalkboardTeacher,
  FaMobileAlt,
  FaRegClock,
} from "react-icons/fa";
import { Link } from "react-router-dom";

const Landing_Page = () => {
  return (
    <div className="landing-page">
      {/* Navigation */}
      <nav className="navbar">
        <div className="container">
          <div className="logo">Learnify</div>
          <div className="nav-links">
            <a href="#features">Features</a>
            <a href="#courses">Courses</a>
            <a href="/Login">Login</a>
            <Link to="/Reg">
              <button className="cta-button">Get Started</button>
            </Link>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="hero">
        <div className="container">
          <div className="hero-content">
            <h1>Master New Skills with Interactive Learning</h1>
            <p>Join 500,000+ learners worldwide advancing their careers</p>
            <div className="cta-container">
              <Link to="/Login">
                <button className="cta-button primary">
                  Start Learning Free
                </button>
              </Link>
              <button className="cta-button secondary">Explore Courses</button>
            </div>
          </div>
          <div className="hero-image">
            <img src={Landing_img} alt="Learning illustration" />
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="features" id="features">
        <div className="container">
          <h2>Why Choose Learnify?</h2>
          <div className="features-grid">
            <FeatureCard
              icon={<FaChalkboardTeacher />}
              title="Expert Instructors"
              text="Learn from industry professionals with real-world experience"
            />
            <FeatureCard
              icon={<FaMobileAlt />}
              title="Mobile Friendly"
              text="Learn anywhere, anytime with our mobile-optimized platform"
            />
            <FeatureCard
              icon={<FaBookOpen />}
              title="100+ Courses"
              text="From coding to business strategy - we've got you covered"
            />
            <FeatureCard
              icon={<FaRegClock />}
              title="Flexible Schedule"
              text="Learn at your own pace with lifetime access"
            />
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="footer">
        <div className="container">
          <div className="footer-content">
            <div className="footer-section">
              <h4>Learnify</h4>
              <p>Empowering learners since 2023</p>
            </div>
            <div className="footer-section">
              <h4>Company</h4>
              <a href="/about">About</a>
              <a href="/careers">Careers</a>
              <a href="/blog">Blog</a>
            </div>
            <div className="footer-section">
              <h4>Legal</h4>
              <a href="/privacy">Privacy Policy</a>
              <a href="/terms">Terms of Service</a>
              <a href="/cookies">Cookie Policy</a>
            </div>
          </div>
          <div className="copyright">© 2023 Learnify. All rights reserved.</div>
        </div>
      </footer>
    </div>
  );
};

const FeatureCard = ({ icon, title, text }) => (
  <div className="feature-card">
    <div className="feature-icon">{icon}</div>
    <h3>{title}</h3>
    <p>{text}</p>
  </div>
);

export default Landing_Page;
