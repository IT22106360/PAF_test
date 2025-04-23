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
}
