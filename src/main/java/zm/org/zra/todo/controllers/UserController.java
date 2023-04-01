package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zm.org.zra.todo.dtos.RegisterDTO;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterDTO formData) throws IOException {
        try {
            var result = userService.register(formData);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
