package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zm.org.zra.todo.dtos.LoginDTO;
import zm.org.zra.todo.services.imp.AuthenticationService;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.io.IOException;

@Controller
public class BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationService authService;
    @RequestMapping ("/index")
    public String index(){
        return  "index";
    }
    @RequestMapping ("/home")
    public String home(){
       return  "index";
    }
    @RequestMapping ("/todos")
    public String todos(){
       return  "index";
    }
    @RequestMapping ("/register")
    public String register(){
       return  "register";
    }

    @GetMapping("/")
    public  String root(){
        return "login";
    }
    @RequestMapping("/signin")
    public String login(LoginDTO formData) throws IOException {
        var result = authService.login(formData);
        if (result.isSucceeded()){
            return  "redirect:/index";
        }
        else {
            return  "redirect:/?error="+result.getMessage();
        }
    }

}
