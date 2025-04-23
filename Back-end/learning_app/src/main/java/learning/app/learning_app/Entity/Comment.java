package learning.app.learning_app.Entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("comments")
@Data
public class Comment {
    @Id
    private ObjectId id;

    private ObjectId postId;
    private ObjectId userId;

    private String text;
    private LocalDateTime createdAt;
}
