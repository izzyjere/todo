package zm.org.zra.todo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zm.org.zra.todo.dtos.ActionResult;
import zm.org.zra.todo.dtos.LoginDTO;
import zm.org.zra.todo.dtos.RegisterDTO;
import zm.org.zra.todo.security.TokenGenerator;
import zm.org.zra.todo.services.imp.AuthenticationService;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO formData, HttpServletResponse response, HttpServletRequest request) throws IOException {
        var result = authService.login(formData);
        if(result.isSucceeded()){
            String rememberMeToken = TokenGenerator.generateToken();
            Cookie rememberMeCookie = new Cookie("todo-spring-auth", rememberMeToken);
            rememberMeCookie.setMaxAge(86400);
            response.addCookie(rememberMeCookie);
        }
        return  ResponseEntity.ok(result);
    }
    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterDTO formData) throws IOException {
        try {
            var result = userService.register(formData);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
