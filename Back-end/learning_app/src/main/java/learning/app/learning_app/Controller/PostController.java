package learning.app.learning_app.Controller;

import java.security.Principal;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import learning.app.learning_app.Entity.Post;
import learning.app.learning_app.Service.PostService;
import learning.app.learning_app.dto.PostCreateDTO;
import learning.app.learning_app.dto.PostResponseDTO;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostCreateDTO dto, Principal principal) {
        PostResponseDTO post = postService.createPost(principal.getName(), dto);
        return ResponseEntity.ok(post);
    }


    // Get all posts
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(Principal principal) {
        List<PostResponseDTO> posts = postService.getAllPosts(principal.getName());
        return ResponseEntity.ok(posts);
    }

    // Get a single post
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable String id, Principal principal) {
        PostResponseDTO post = postService.getPostById(id, principal.getName());
        return ResponseEntity.ok(post);
    }

    // Update a post
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable String id, @RequestBody PostCreateDTO dto, Principal principal) {
        PostResponseDTO updatedPost = postService.updatePost(id, dto, principal.getName());
        return ResponseEntity.ok(updatedPost);
    }    

    // Delete a post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id, Principal principal) {
        postService.deletePost(id, principal.getName());
        return ResponseEntity.ok("Post deleted successfully!");
    }



    // Like a post
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likePost(@PathVariable String id, Principal principal) {
        postService.likePost(id, principal.getName());
        return ResponseEntity.ok("Liked successfully!");
    }

    // Unlike a post
    @DeleteMapping("/{id}/like")
    public ResponseEntity<String> unlikePost(@PathVariable String id, Principal principal) {
        postService.likePost(id, principal.getName()); // Same service method for toggling
        return ResponseEntity.ok("Unliked successfully!");
    }
}
