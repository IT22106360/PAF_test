package learning.app.learning_app.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import learning.app.learning_app.Entity.Users;
import learning.app.learning_app.Service.UserManagementService;
import learning.app.learning_app.dto.UserDTO;

@RestController
public class UserController {
  @Autowired
    private UserManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO req){
        return usersManagementService.login(req);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UserDTO> refreshToken(@RequestBody UserDTO req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/adminuser/get-all-users")
    public ResponseEntity<UserDTO> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/adminuser/get-users/{userId}")
    public ResponseEntity<UserDTO> getUSerByID(@PathVariable ObjectId userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable ObjectId userId, @RequestBody Users reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<UserDTO> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDTO response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<UserDTO> deleteUSer(@PathVariable ObjectId userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }

}
