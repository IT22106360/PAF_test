import React, { useEffect, useState } from "react";
import axios from "axios";
import PostCard from "../components/PostCard";
import "../Styles/FeedPage.scss"; // Import SCSS file for styling
import { FiBookOpen, FiClock, FiBarChart, FiSettings } from "react-icons/fi";

const FeedPage = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/api/posts", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => setPosts(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="user-home-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="sidebar-header">
          <h2>Learnify</h2>
        </div>

        <nav className="sidebar-nav">
          <a href="/Home">
            <FiBookOpen className="icon" />
            My Courses
          </a>
          <a href="/Feed" className="active">
            <FiBookOpen className="icon" />
            Feed
          </a>
          <a href="#progress">
            <FiBarChart className="icon" />
            Progress
          </a>
          <a href="#schedule">
            <FiClock className="icon" />
            Schedule
          </a>
          <a href="#settings">
            <FiSettings className="icon" />
            Settings
          </a>
        </nav>
      </aside>

      {/* Main Feed Content */}
      <div className="main-content">
        {/* Top Bar */}
        <header className="top-bar">
          <div className="search-bar">
            <input type="text" placeholder="Search courses..." />
          </div>
          <div className="user-profile">
            <div className="notification">3</div>
            <img src="/images/user-avatar.jpg" alt="User Avatar" />
          </div>
        </header>

        <div className="feed-posts">
          {posts.length > 0 ? (
            posts.map((post) => <PostCard key={post.id} post={post} />)
          ) : (
            <p className="no-posts">No posts to show.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default FeedPage;
