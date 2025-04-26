import React, { useEffect, useState } from "react";
import "../Styles/ProfilePage.scss";
import { useNavigate } from "react-router-dom";
import UserService from "../api/UserService";

const ProfilePage = () => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState({
    name: "",
    email: "",
  });

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const res = await UserService.getCurrentUser(); // Assumes user info is fetched via token or session
        setUserData({
          name: res.data.name,
          email: res.data.email,
        });
      } catch (error) {
        alert("Failed to load user data");
      }
    };

    fetchUserData();
  }, []);

  const handleUpdate = () => {
    navigate("/UpdateUser"); // Make sure route is defined
  };

  const handleLogout = () => {
    UserService.logout(); // Add logout logic in UserService
    navigate("/Login");
  };

  return (
    <div className="profile-container">
      <div className="profile-card">
        <h2>Your Profile</h2>
        <div className="profile-info">
          <p><strong>Name:</strong> {userData.name}</p>
          <p><strong>Email:</strong> {userData.email}</p>
        </div>
        <div className="profile-actions">
          <button onClick={handleUpdate} className="update-btn">Update Info</button>
          <button onClick={handleLogout} className="logout-btn">Log Out</button>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
