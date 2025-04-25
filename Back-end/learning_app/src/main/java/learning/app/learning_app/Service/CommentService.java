package learning.app.learning_app.Service;

import java.time.LocalDateTime;
import java.util.List;

import learning.app.learning_app.Entity.Comment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.app.learning_app.Entity.Users;
import learning.app.learning_app.Repository.CommentsRepository;
import learning.app.learning_app.Repository.UsersRepository;
import learning.app.learning_app.dto.CommentDTO;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository commentRepo;

    @Autowired
    private UsersRepository usersRepo;

    public Comment addComment(String email, CommentDTO dto) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Comment comment = new Comment();
        comment.setPostId(new ObjectId(dto.getPostId()));
        comment.setUserId(user.getId());
        comment.setText(dto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsForPost(String postId) {
        return commentRepo.findByPostId(new ObjectId(postId));
    }

    public Comment updateComment(String email, String commentId, CommentDTO dto) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Comment comment = commentRepo.findById(new ObjectId(commentId))
            .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Check if the logged-in user is the one who posted the comment
        if (!comment.getUserId().equals(user.getId())) {
            throw new RuntimeException("You can only update your own comment");
        }

        // Update the comment
        comment.setText(dto.getText());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    public void deleteComment(String email, String commentId) {
        Users user = usersRepo.findByEmail(email).orElseThrow();
        Comment comment = commentRepo.findById(new ObjectId(commentId))
            .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Check if the logged-in user is the one who posted the comment
        if (!comment.getUserId().equals(user.getId())) {
            throw new RuntimeException("You can only delete your own comment");
        }

        commentRepo.delete(comment);
    }
}
