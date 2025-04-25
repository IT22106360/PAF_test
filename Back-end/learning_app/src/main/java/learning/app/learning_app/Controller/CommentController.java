package learning.app.learning_app.Controller;

import java.security.Principal;

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

import learning.app.learning_app.Service.CommentService;
import learning.app.learning_app.dto.CommentDTO;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO dto, Principal principal) {
        return ResponseEntity.ok(commentService.addComment(principal.getName(), dto));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getComments(@PathVariable String postId) {
        return ResponseEntity.ok(commentService.getCommentsForPost(postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable String commentId, @RequestBody CommentDTO dto, Principal principal) {
        return ResponseEntity.ok(commentService.updateComment(principal.getName(), commentId, dto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String commentId, Principal principal) {
        commentService.deleteComment(principal.getName(), commentId);
        return ResponseEntity.ok("Comment deleted successfully.");
    }
}
