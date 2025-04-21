// LoginPage.jsx
import React, { useState, useEffect } from "react";
import "../Styles/LoginPage.scss";
import { FaEye, FaEyeSlash, FaGoogle, FaGithub } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import UserService from "../api/UserService";

const sliderImages = [
  "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/acc3de9d-17b0-449a-b11f-20383ba109b3.png",
  "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/b7c60d8e-8a66-463d-b6a6-759202f6c436.png",
  "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/79d48f38-331f-46fc-b2de-59bd08caaee5.png",
];

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [activeIndex, setActiveIndex] = useState(0);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => {
      setActiveIndex((prev) => (prev + 1) % sliderImages.length);
    }, 5000);
    return () => clearInterval(timer);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const response = await UserService.login(email, password);
      if (response.token) {
        localStorage.setItem("token", response.token);
        localStorage.setItem("role", response.role);
        navigate("/Home");
      }
    } catch (error) {
      if (error.response) {
        switch (error.response.status) {
          case 401:
            setError("Invalid email/password ");
            break;
          case 404:
            setError("User not found");
            break;
          case 500:
            setError(`Server error: ${error.response.data.message}`);
            break;
          default:
            setError("Login failed. Please try again.");
        }
      }
    }
  };

  return (
    <div className="login-container">
      <div className="slider-section">
        <div
          className="slider-track"
          style={{ transform: `translateX(-${activeIndex * 100}%)` }}
        >
          {sliderImages.map((img, index) => (
            <div
              key={index}
              className="slider-item"
              style={{ backgroundImage: `url(${img})` }}
            />
          ))}
        </div>
      </div>

      <div className="form-section">
        <form className="login-form" onSubmit={handleSubmit}>
          <h2>Sign In to Learnify</h2>
          {error && <div className="error-message">{error}</div>}
          <div className="social-login">
            <button type="button" className="google-btn">
              <FaGoogle className="icon" /> Continue with Google
            </button>
            <button type="button" className="github-btn">
              <FaGithub className="icon" /> Continue with GitHub
            </button>
          </div>

          <div className="divider">
            <span>or</span>
          </div>

          <div className="form-group">
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <label htmlFor="email">Email Address</label>
          </div>

          <div className="form-group">
            <input
              type={showPassword ? "text" : "password"}
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <label htmlFor="password">Password</label>
            <button
              type="button"
              className="password-toggle"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? <FaEyeSlash /> : <FaEye />}
            </button>
          </div>
          <button type="submit" className="login-button">
            Sign In
          </button>

          <div className="form-footer">
            <a href="/forgot-password">Forgot Password?</a>
            <p>
              Don't have an account? <a href="/Reg">Create Account</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
