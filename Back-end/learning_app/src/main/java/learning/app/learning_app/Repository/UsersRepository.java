package learning.app.learning_app.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import learning.app.learning_app.Entity.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users,ObjectId> {
    Optional<Users> findByEmail(String email);
}
