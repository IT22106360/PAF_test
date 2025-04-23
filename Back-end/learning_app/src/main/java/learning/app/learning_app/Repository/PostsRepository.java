package learning.app.learning_app.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import learning.app.learning_app.Entity.Post;

public interface PostsRepository extends MongoRepository<Post, ObjectId> {
}

