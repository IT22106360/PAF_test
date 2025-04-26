package learning.app.learning_app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningItem {
    private String id;
    private String resourceTitle;
    private String resourceUrl;
    private boolean completed;

}
