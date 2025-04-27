package learning.app.learning_app.dto;

import lombok.Data;

@Data
public class PostResponseDTO {
    private String id;
    private String content;
    private String mediaUrl;
    private String createdAt;
    private String visibility;
    private UserShortDTO user;
    private int likesCount;
}