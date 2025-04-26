package learning.app.learning_app.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "learning_plans")
public class LearningPlan {

    @Id
    private String id;

    private String title;
    private String description;

    private List<LearningItem> items;


}

