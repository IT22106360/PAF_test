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

    // Create a post
    public PostResponseDTO createPost(String email, PostCreateDTO dto) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setMediaUrl(dto.getMediaUrl());
        post.setUserId(user.getId());
        post.setCreatedAt(LocalDateTime.now());
        
        Post savedPost = postsRepo.save(post);
        return mapToDTO(savedPost);
    }

    public void likePost(String postId, String email) {
        Users user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    
        Post post = postsRepo.findById(new ObjectId(postId))
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
    
        // Toggle like/unlike
        if (post.getLikedBy().contains(user.getId())) {
            post.getLikedBy().remove(user.getId()); // Unlike the post
        } else {
            post.getLikedBy().add(user.getId()); // Like the post
        }
        postsRepo.save(post);
    }

    // Get all posts
    public List<PostResponseDTO> getAllPosts(String email) {
        Users currentUser = usersRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return postsRepo.findAll()
                        .stream()
                        .map(post -> mapToDTO(post, currentUser.getId()))
                        .toList();
    }

    // Get post by ID
    public PostResponseDTO getPostById(String id, String email) {
        Post post = postsRepo.findById(new ObjectId(id)).orElseThrow();
        Users currentUser = usersRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return mapToDTO(post, currentUser.getId());
    }

    // Update a post
    public PostResponseDTO updatePost(String postId, PostCreateDTO dto, String email) {
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

    // Delete a post
    public void deletePost(String postId, String email) {
        Post post = postsRepo.findById(new ObjectId(postId)).orElseThrow();
        Users user = usersRepo.findByEmail(email).orElseThrow();

        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this post.");
        }

        postsRepo.delete(post);
    }

    private PostResponseDTO mapToDTO(Post post, ObjectId currentUserId) {
        PostResponseDTO dto = new PostResponseDTO();
    dto.setId(post.getId().toHexString());
    dto.setContent(post.getContent());
    dto.setMediaUrl(post.getMediaUrl());
    dto.setCreatedAt(post.getCreatedAt().toString());
    dto.setLikesCount(post.getLikedBy().size());
    dto.setLikedByUser(post.getLikedBy().contains(currentUserId));

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
