package learning.app.learning_app.Repository;

import learning.app.learning_app.Entity.LearningPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearningPlanRepository extends MongoRepository<LearningPlan, String> {
}
