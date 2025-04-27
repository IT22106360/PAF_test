package learning.app.learning_app.Entity;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document("posts")
public class Post {
    @Id
    private ObjectId id;

    private String content;
    private String mediaUrl;
    private LocalDateTime createdAt;

    private ObjectId userId;

    private Set<ObjectId> likedBy = new HashSet<>();

    private List<ObjectId> commentIds = new ArrayList<>();

}
