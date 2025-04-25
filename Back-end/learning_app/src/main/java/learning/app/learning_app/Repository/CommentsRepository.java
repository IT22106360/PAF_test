package learning.app.learning_app.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import learning.app.learning_app.Entity.Comment;

public interface CommentsRepository extends MongoRepository<Comment, ObjectId> {
    List<Comment> findByPostId(ObjectId postId);
}

