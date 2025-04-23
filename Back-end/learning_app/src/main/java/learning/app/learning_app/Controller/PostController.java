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

import learning.app.learning_app.Entity.Post;
import learning.app.learning_app.Service.PostService;
import learning.app.learning_app.dto.PostDTO;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO dto, Principal principal) {
        return ResponseEntity.ok(postService.createPost(principal.getName(), dto));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable String id, Principal principal) {
        postService.likePost(new ObjectId(id), principal.getName());
        return ResponseEntity.ok("Liked");
    }
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id, Principal principal) {
        postService.deletePost(id, principal.getName());
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody PostDTO dto, Principal principal) {
        return postService.updatePost(id, dto, principal.getName());
    }

}
