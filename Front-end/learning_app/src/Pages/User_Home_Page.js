// UserHomePage.jsx
import React from "react";
import "../Styles/UserHomePage.scss";
import { FiBookOpen, FiClock, FiBarChart, FiSettings } from "react-icons/fi";

const User_Home_Page = () => {
  // Temporary data - replace with real data
  const courses = [
    {
      id: 1,
      title: "Web Development",
      progress: 65,
      duration: "8h left",
      image: "/images/course1.jpg",
    },
    {
      id: 2,
      title: "Data Science",
      progress: 40,
      duration: "12h left",
      image: "/images/course2.jpg",
    },
    {
      id: 3,
      title: "Mobile Development",
      progress: 85,
      duration: "4h left",
      image: "/images/course3.jpg",
    },
  ];

  return (
    <div className="user-home-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="sidebar-header">
          <h2>Learnify</h2>
        </div>

        <nav className="sidebar-nav">
          <a href="#dashboard" className="active">
            <FiBookOpen className="icon" />
            My Courses
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

      {/* Main Content */}
      <main className="main-content">
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

        {/* Dashboard Content */}
        <div className="dashboard-content">
          <section className="welcome-section">
            <h1>Welcome back, John!</h1>
            <p>Continue your learning journey</p>
          </section>

          {/* Course Grid */}
          <section className="course-grid">
            {courses.map((course) => (
              <div key={course.id} className="course-card">
                <div
                  className="course-image"
                  style={{ backgroundImage: `url(${course.image})` }}
                >
                  <div className="progress-bar">
                    <div
                      className="progress"
                      style={{ width: `${course.progress}%` }}
                    ></div>
                  </div>
                </div>
                <div className="course-details">
                  <h3>{course.title}</h3>
                  <div className="course-meta">
                    <span>{course.duration}</span>
                    <span>{course.progress}% Complete</span>
                  </div>
                </div>
              </div>
            ))}
          </section>

          {/* Progress Section */}
          <section className="progress-section">
            <div className="progress-card">
              <h3>Overall Progress</h3>
              <div className="circular-progress">
                <div className="progress-circle">72%</div>
              </div>
              <p>Completed 8 out of 12 courses</p>
            </div>

            <div className="calendar-card">
              <h3>Today's Schedule</h3>
              <div className="calendar-placeholder">
                {/* Add calendar component here */}
                <p>No upcoming events</p>
              </div>
            </div>
          </section>
        </div>
      </main>
    </div>
  );
};

export default User_Home_Page;
