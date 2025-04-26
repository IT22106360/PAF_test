package learning.app.learning_app.Service;

import learning.app.learning_app.Entity.LearningItem;
import learning.app.learning_app.Entity.LearningPlan;
import learning.app.learning_app.Repository.LearningPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningPlanService {

    @Autowired
    private LearningPlanRepository repository;

    public LearningPlan createPlan(LearningPlan plan) {
        return repository.save(plan);
    }

    public LearningPlan updatePlan(String id, LearningPlan updatedPlan) {
        LearningPlan existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        existing.setTitle(updatedPlan.getTitle());
        existing.setDescription(updatedPlan.getDescription());
        existing.setItems(updatedPlan.getItems());

        return repository.save(existing);
    }

    public void deletePlan(String id) {
        repository.deleteById(id);
    }

    public LearningPlan getPlan(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    public List<LearningPlan> getAllPlans() {
        return repository.findAll();
    }

    public LearningPlan markProgress(String planId, String itemId, boolean completed) {
        LearningPlan plan = getPlan(planId);

        for (LearningItem item : plan.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setCompleted(completed);
                break;
            }
        }

        return repository.save(plan);
    }
}

