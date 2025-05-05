import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import {
  Image as ImageIcon,
  Video,
  X,
  Upload,
  AlertCircle,
} from "lucide-react";
import postService from "../api/postService"; // Import the service

const MAX_FILES = 5;
const MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
const ALLOWED_IMAGE_TYPES = ["image/jpeg", "image/png", "image/gif"];
const ALLOWED_VIDEO_TYPES = ["video/mp4", "video/webm"];

function CreatePost() {
  const navigate = useNavigate();
  const [description, setDescription] = useState("");
  const [mediaFiles, setMediaFiles] = useState([]);
  const [error, setError] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const fileInputRef = useRef(null);

  const handleFileChange = (e) => {
    const files = Array.from(e.target.files || []);
    setError("");

    if (mediaFiles.length + files.length > MAX_FILES) {
      setError(`You can only upload up to ${MAX_FILES} files`);
      return;
    }

    files.forEach((file) => {
      if (file.size > MAX_FILE_SIZE) {
        setError("Each file must be less than 5MB");
        return;
      }

      if (
        ![...ALLOWED_IMAGE_TYPES, ...ALLOWED_VIDEO_TYPES].includes(file.type)
      ) {
        setError(
          "Only images (JPEG, PNG, GIF) and videos (MP4, WEBM) are allowed"
        );
        return;
      }

      const reader = new FileReader();
      reader.onload = () => {
        const isImage = ALLOWED_IMAGE_TYPES.includes(file.type);
        setMediaFiles((prevFiles) => [
          ...prevFiles,
          {
            type: isImage ? "image" : "video",
            url: reader.result,
            name: file.name,
          },
        ]);
      };
      reader.readAsDataURL(file);
    });
  };

  const removeFile = (index) => {
    setMediaFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
  };

  // ... component code ...

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setIsSubmitting(true);

      //  *** CORRECTED postData ***
      const postData = {
        userId: "USER_ID_HERE", //  Replace with the actual user ID
        description: description, //  Use 'description'
        media: mediaFiles.map((file) => file.url), //  Send only URLs
        likesCount: 0, //  Use 'likesCount'
        commentsCount: 0, //  Use 'commentsCount'
        sharesCount: 0, //  Initialize sharesCount
        createdAt: new Date().toISOString(), //  Send current time in ISO format
        updatedAt: new Date().toISOString(),
      };

      const postId = await postService.createPost(postData);
      console.log("Post created with ID:", postId);
      navigate("/", { replace: true });
    } catch (error) {
      setError(error.message || "Failed to create post. Please try again.");
      console.error("Error creating post:", error);
    } finally {
      setIsSubmitting(false);
    }
  };
  // ... component code ...

  return (
    <div className="max-w-2xl mx-auto p-4">
      <div className="bg-white rounded-xl shadow-lg p-6">
        <h2 className="text-2xl font-bold mb-6">Create a Post</h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Share your skills and knowledge..."
              className="w-full h-32 p-4 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-transparent resize-none"
              required
            />
          </div>

          {mediaFiles.length > 0 && (
            <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
              {mediaFiles.map((media, index) => (
                <div key={index} className="relative group">
                  {media.type === "image" ? (
                    <img
                      src={media.url}
                      alt={`Preview ${index + 1}`}
                      className="w-full h-40 object-cover rounded-lg"
                    />
                  ) : (
                    <video
                      src={media.url}
                      className="w-full h-40 object-cover rounded-lg"
                      controls
                    />
                  )}
                  <button
                    type="button"
                    onClick={() => removeFile(index)}
                    className="absolute top-2 right-2 p-1 bg-red-500 text-white rounded-full opacity-0 group-hover:opacity-100 transition-opacity"
                  >
                    <X size={16} />
                  </button>
                </div>
              ))}
            </div>
          )}

          <div className="flex items-center gap-4">
            <button
              type="button"
              onClick={() => fileInputRef.current?.click()}
              className="flex items-center gap-2 px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
              disabled={mediaFiles.length >= MAX_FILES}
            >
              <Upload size={20} />
              Add Media
            </button>
            <span className="text-sm text-gray-500">
              {mediaFiles.length}/{MAX_FILES} files
            </span>
          </div>

          <input
            ref={fileInputRef}
            type="file"
            accept={[...ALLOWED_IMAGE_TYPES, ...ALLOWED_VIDEO_TYPES].join(",")}
            onChange={handleFileChange}
            className="hidden"
            multiple
          />

          {error && (
            <div className="flex items-center gap-2 text-red-500">
              <AlertCircle size={16} />
              <span>{error}</span>
            </div>
          )}

          <button
            type="submit"
            disabled={isSubmitting}
            className={`w-full ${
              isSubmitting
                ? "bg-indigo-400"
                : "bg-indigo-500 hover:bg-indigo-600"
            } text-white rounded-lg px-4 py-3 font-medium transition-colors flex items-center justify-center`}
          >
            {isSubmitting ? (
              <>
                <span className="animate-spin mr-2">⏳</span>
                Posting...
              </>
            ) : (
              "Post"
            )}
          </button>
        </form>
      </div>
    </div>
  );
}

export default CreatePost;
