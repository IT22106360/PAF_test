// Replace with your actual API URL
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/posts"; // Make sure this matches your Spring Boot @RequestMapping

const PostService = {
  createPost: async (dto, token) => {
    try {
      const response = await axios.post(API_BASE_URL, dto, {
        headers: {
          Authorization: `Bearer ${token}`, // Include the token in the Authorization header
          "Content-Type": "application/json", // Explicitly set content type
        },
      });
      return response.data;
    } catch (error) {
      console.error("Error creating post:", error);
      throw error; // Re-throw to be caught by the component
    }
  },

  likePost: async (id, token) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/${id}/like`, null, {
        // Use null for an empty body
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data; // Returns "Liked"
    } catch (error) {
      console.error("Error liking post:", error);
      throw error;
    }
  },

  getAllPosts: async () => {
    try {
      const response = await axios.get(API_BASE_URL);
      return response.data;
    } catch (error) {
      console.error("Error getting all posts:", error);
      throw error;
    }
  },

  getPostById: async (id) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/${id}`);
      return response.data;
    } catch (error) {
      console.error("Error getting post by ID:", error);
      throw error;
    }
  },

  deletePost: async (id, token) => {
    try {
      const response = await axios.delete(`${API_BASE_URL}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      // No data is returned from the delete endpoint in the Java controller
      return response;
    } catch (error) {
      console.error("Error deleting post:", error);
      throw error;
    }
  },

  updatePost: async (id, dto, token) => {
    try {
      const response = await axios.put(`${API_BASE_URL}/${id}`, dto, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
      return response.data;
    } catch (error) {
      console.error("Error updating post:", error);
      throw error;
    }
  },
};

export default PostService;
