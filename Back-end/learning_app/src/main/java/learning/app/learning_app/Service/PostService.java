package learning.app.learning_app.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.app.learning_app.Entity.Post;
import learning.app.learning_app.Entity.Users;
import learning.app.learning_app.Repository.PostsRepository;
import learning.app.learning_app.Repository.UsersRepository;
import learning.app.learning_app.dto.PostDTO;

@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepo;

    @Autowired
    private UsersRepository usersRepo;

    // ✅ Create a post
    public Post createPost(String email, PostDTO dto) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setMediaUrl(dto.getMediaUrl());
        post.setUserId(user.getId());
        post.setCreatedAt(LocalDateTime.now());
        return postsRepo.save(post);
    }

    // ✅ Like a post
    public void likePost(ObjectId postId, String email) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Post post = postsRepo.findById(postId).orElseThrow();
        post.getLikedBy().add(user.getId());
        postsRepo.save(post);
    }

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
        Post post = postsRepo.findById(new ObjectId(postId)).orElseThrow();
        Users user = usersRepo.findByEmail(email).orElseThrow();

        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to update this post.");
        }

        post.setContent(dto.getContent());
        post.setMediaUrl(dto.getMediaUrl());
        return postsRepo.save(post);
    }

    // ✅ Delete a post
    public void deletePost(String postId, String email) {
        Post post = postsRepo.findById(new ObjectId(postId)).orElseThrow();
        Users user = usersRepo.findByEmail(email).orElseThrow();

        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this post.");
        }

        postsRepo.delete(post);
    }
}
