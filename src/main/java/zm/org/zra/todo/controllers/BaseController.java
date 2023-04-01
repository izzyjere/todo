package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zm.org.zra.todo.services.interfaces.IUserService;

@Controller
public class BaseController {
    @Autowired
    private IUserService userService;
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
        return "redirect:/index";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }


}
