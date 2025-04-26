import React, { useState } from "react";
import "../Styles/UpdateUserPage.scss";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import UserService from "../api/UserService";
import { useNavigate } from "react-router-dom";

const UpdateUserPage = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      const updatedUser = {
        email: formData.email,
        password: formData.password,
      };
      await UserService.updateUser(updatedUser);
      alert("User updated successfully");
      navigate("/Dashboard"); // or wherever you'd like to redirect
    } catch (error) {
      alert("Failed to update user");
    }
  };

  return (
    <div className="update-user-container">
      <form className="update-user-form" onSubmit={handleSubmit}>
        <h2>Update Your Details</h2>

        <div className="form-group">
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <label htmlFor="email">New Email</label>
        </div>

        <div className="form-group">
          <input
            type={showPassword ? "text" : "password"}
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <label htmlFor="password">New Password</label>
          <button
            type="button"
            className="password-toggle"
            onClick={() => setShowPassword(!showPassword)}
          >
            {showPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>

        <div className="form-group">
          <input
            type={showConfirmPassword ? "text" : "password"}
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="confirmPassword">Confirm Password</label>
          <button
            type="button"
            className="password-toggle"
            onClick={() => setShowConfirmPassword(!showConfirmPassword)}
          >
            {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>

        <button type="submit" className="update-button">
          Update Info
        </button>
      </form>
    </div>
  );
};

export default UpdateUserPage;
