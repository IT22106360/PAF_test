package learning.app.learning_app.dto;

import lombok.Data;

@Data
public class UserShortDTO {
    private String id;
    private String name;
    private String profilePictureUrl; // Assuming your Users have a profile picture field
}

