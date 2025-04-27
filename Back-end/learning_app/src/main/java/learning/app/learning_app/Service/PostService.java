package learning.app.learning_app.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.app.learning_app.Entity.Post;
import learning.app.learning_app.Entity.Users;
import learning.app.learning_app.Repository.PostsRepository;
import learning.app.learning_app.Repository.UsersRepository;
import learning.app.learning_app.dto.PostCreateDTO;
import learning.app.learning_app.dto.PostResponseDTO;
import learning.app.learning_app.dto.UserShortDTO;

@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepo;

    @Autowired
    private UsersRepository usersRepo;

<<<<<<< Updated upstream
    // ✅ Create a post
    public Post createPost(String email, PostDTO dto) {
=======
    // Create a post
    public PostResponseDTO createPost(String email, PostCreateDTO dto) {
>>>>>>> Stashed changes
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setMediaUrl(dto.getMediaUrl());
        post.setUserId(user.getId());
        post.setCreatedAt(LocalDateTime.now());
        
        Post savedPost = postsRepo.save(post);
        return mapToDTO(savedPost);
    }

<<<<<<< Updated upstream
    // ✅ Like a post
    public void likePost(ObjectId postId, String email) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Post post = postsRepo.findById(postId).orElseThrow();
=======
    public void likePost(String postId, String email) {
        Users user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    
        Post post = postsRepo.findById(new ObjectId(postId))
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
    
>>>>>>> Stashed changes
        post.getLikedBy().add(user.getId());
        postsRepo.save(post);
    }

<<<<<<< Updated upstream
    // ✅ Get all posts
    public List<Post> getAllPosts() {
        return postsRepo.findAll();
    }

    // ✅ Get post by ID
    public Post getPostById(String id) {
        return postsRepo.findById(new ObjectId(id)).orElseThrow();
    }

    // ✅ Update a post
    public Post updatePost(String postId, PostDTO dto, String email) {
=======
    // Get all posts
    public List<PostResponseDTO> getAllPosts() {
        return postsRepo.findAll()
                        .stream()
                        .map(this::mapToDTO)
                        .toList();
    }

    // Get post by ID
    public PostResponseDTO getPostById(String id) {
        Post post = postsRepo.findById(new ObjectId(id)).orElseThrow();
        return mapToDTO(post);
    }

    // Update a post
    public PostResponseDTO updatePost(String postId, PostCreateDTO dto, String email) {
>>>>>>> Stashed changes
        Post post = postsRepo.findById(new ObjectId(postId)).orElseThrow();
        Users user = usersRepo.findByEmail(email).orElseThrow();

        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to update this post.");
        }

        post.setContent(dto.getContent());
        post.setMediaUrl(dto.getMediaUrl());
        Post updated = postsRepo.save(post);
        return mapToDTO(updated);
    }

<<<<<<< Updated upstream
    // ✅ Delete a post
=======
    // Delete a post
>>>>>>> Stashed changes
    public void deletePost(String postId, String email) {
        Post post = postsRepo.findById(new ObjectId(postId)).orElseThrow();
        Users user = usersRepo.findByEmail(email).orElseThrow();

        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this post.");
        }

        postsRepo.delete(post);
    }

 private PostResponseDTO mapToDTO(Post post) {
    PostResponseDTO dto = new PostResponseDTO();
    dto.setId(post.getId().toHexString());
    dto.setContent(post.getContent());
    dto.setMediaUrl(post.getMediaUrl());
    dto.setCreatedAt(post.getCreatedAt().toString());
    dto.setLikesCount(post.getLikedBy().size());

    Users user = usersRepo.findById(post.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + post.getUserId()));

    UserShortDTO userDto = new UserShortDTO();
    userDto.setId(user.getId().toHexString());
    userDto.setName(user.getName()); // assuming your Users class has getName()
    userDto.setProfilePictureUrl(user.getProfilePictureUrl()); // assuming you store profile pictures

    dto.setUser(userDto);

    return dto;
}

}
