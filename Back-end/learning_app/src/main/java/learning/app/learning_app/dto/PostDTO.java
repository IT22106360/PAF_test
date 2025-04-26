
package learning.app.learning_app.dto;

import learning.app.learning_app.Entity.Post;
import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String content;
    private String mediaUrl;
}
