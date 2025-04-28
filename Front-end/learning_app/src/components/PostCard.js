import React, { useState, useEffect, useRef } from "react";
import CommentSection from "./CommentSection";
import {
  FiThumbsUp,
  FiMessageCircle,
  FiRepeat,
  FiSend,
  FiMoreHorizontal,
  FiGlobe,
} from "react-icons/fi";

const PostCard = ({ post }) => {
  const [liked, setLiked] = useState(false);
  const [likesCount, setLikesCount] = useState(post.likesCount);
  const [expanded, setExpanded] = useState(false); // state to track "See More"
  const contentRef = useRef(null);

  useEffect(() => {
    // Check if the post is liked by the user
    const userHasLiked = post.likedByUser; // assuming you have a way to determine if the current user liked the post
    setLiked(userHasLiked);
    setLikesCount(post.likesCount);
  }, [post]);

  const toggleLike = async () => {
    // Optimistic UI update: Update state immediately
    const originalLiked = liked;
    const originalLikesCount = likesCount;

    const newLiked = !liked;
    const newLikesCount = likesCount + (liked ? -1 : 1);

    setLiked(newLiked);
    setLikesCount(newLikesCount);

    try {
      const token = localStorage.getItem("token");

      const response = await fetch(
        `http://localhost:8080/api/posts/${post.id}/like`,
        {
          method: liked ? "DELETE" : "POST", // toggle like/unlike based on current state
          headers: {
            Authorization: `Bearer ${token}`, // Include the token in the request
          },
        }
      );

      if (!response.ok) {
        // If API call fails, revert the optimistic update
        console.error("API Error:", response.status, await response.text());
        setLiked(originalLiked);
        setLikesCount(originalLikesCount);
        // Optionally: Show an error message to the user
      }
    } catch (error) {
      console.error("Error liking/unliking post:", error);
    }
  };

  //func to cal posted date from
  const formatTimeAgo = (isoDateString) => {
    const date = new Date(isoDateString);
    const now = new Date();
    const diffInSeconds = Math.floor((now - date) / 1000);

    if (diffInSeconds < 60) return `${diffInSeconds}s`;
    const diffInMinutes = Math.floor(diffInSeconds / 60);
    if (diffInMinutes < 60) return `${diffInMinutes}m`;
    const diffInHours = Math.floor(diffInMinutes / 60);
    if (diffInHours < 24) return `${diffInHours}h`;
    const diffInDays = Math.floor(diffInHours / 24);
    return `${diffInDays}d`;
  };

  return (
    <div className="postcard">
      <div className="postcard__header">
        <div className="postcard__user">
          <img
            src={post.user.profilePictureUrl}
            alt={`${post.user.username}'s avatar`}
            className="avatar"
            onError={(e) =>
              (e.target.src = "https://placehold.co/40x40/CCCCCC/333?text=?")
            }
          />
          <div>
            <p className="username">{post.user.name || "Anonymous"}</p>
            <p className="usertitle">
              {"Users_title | fixed size for this for one single line"}
            </p>
            <p className="timestamp">
              {formatTimeAgo(post.createdAt)}
              <FiGlobe size={12} className="globe" />
            </p>
          </div>
        </div>
        <button className="more-btn">
          <FiMoreHorizontal size={20} />
        </button>
      </div>

      <div
        className={`postcard__content ${expanded ? "expanded" : ""}`}
        ref={contentRef}
      >
        {post.content}
      </div>

      {!expanded && (
        <button onClick={() => setExpanded(true)} className="see-more-btn">
          See more
        </button>
      )}

      {post.mediaUrl && (
        <div className="postcard__image">
          <img
            src={post.mediaUrl}
            alt="Post media"
            onError={(e) => (e.target.style.display = "none")}
          />
        </div>
      )}

      {/* Use likesCount state for conditional rendering and display */}
      {(likesCount > 0 || post.comments?.length > 0) && (
        <div className="postcard__summary">
          {likesCount > 0 && (
            <div className="likes">
              <span className="like-badge">
                <FiThumbsUp size={10} />
              </span>
              {/* Display the likesCount state variable */}
              <span>{likesCount}</span>
            </div>
          )}
          {post.comments?.length > 0 && (
            <span>
              {/* Comments count can still come from the post prop unless you manage it in state too */}
              {post.comments.length} comment
              {post.comments.length !== 1 ? "s" : ""}
            </span>
          )}
        </div>
      )}

      <div className="postcard__actions">
        <button
          className={`action-btn ${liked ? "liked" : ""}`}
          onClick={toggleLike}
          style={{
            color: liked ? "#007bff" : "",
          }}
        >
          <FiThumbsUp size={20} color={liked ? "#007bff" : ""} />
          <span>{liked ? "Liked" : "Like"}</span>
        </button>
        <button className="action-btn">
          <FiMessageCircle size={20} />
          <span>Comment</span>
        </button>
        <button className="action-btn">
          <FiRepeat size={20} />
          <span>Repost</span>
        </button>
        <button className="action-btn">
          <FiSend size={20} />
          <span>Send</span>
        </button>
      </div>

      <div className="postcard__comments">
        <CommentSection postId={post.id} />
      </div>
    </div>
  );
};

export default PostCard;
