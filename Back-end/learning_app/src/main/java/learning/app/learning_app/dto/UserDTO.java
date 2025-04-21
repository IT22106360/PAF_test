package learning.app.learning_app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import learning.app.learning_app.Entity.Users;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
   private int statusCode;
   private String error;
   private String message;
   private String token;
   private String refreshToken;
   private String expirationTime;
   private String name;
   private String email;
   private String role;
   private String password;
   private Users users;
   private List<Users> usersList;

}
