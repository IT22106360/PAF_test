package learning.app.learning_app.Controller;

import learning.app.learning_app.Entity.LearningPlan;
import learning.app.learning_app.Service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class LearningPlanController {

    @Autowired
    private LearningPlanService service;

    @PostMapping("/create")
    public LearningPlan createPlan(@RequestBody LearningPlan plan) {
        return service.createPlan(plan);
    }

    @PutMapping("update/{id}")
    public LearningPlan updatePlan(@PathVariable String id, @RequestBody LearningPlan plan) {
        return service.updatePlan(id, plan);
    }

    @DeleteMapping("delete/{id}")
    public void deletePlan(@PathVariable String id) {
        service.deletePlan(id);
    }

    @GetMapping("get/{id}")
    public LearningPlan getPlan(@PathVariable String id) {
        return service.getPlan(id);
    }

    @GetMapping
    public List<LearningPlan> getAllPlans() {
        return service.getAllPlans();
    }

    @PatchMapping("/{planId}/items/{itemId}")
    public LearningPlan markProgress(
            @PathVariable String planId,
            @PathVariable String itemId,
            @RequestParam boolean completed) {
        return service.markProgress(planId, itemId, completed);
    }
}

