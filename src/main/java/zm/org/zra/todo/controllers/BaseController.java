package zm.org.zra.todo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zm.org.zra.todo.dtos.RegisterDTO;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.io.IOException;

@Controller
public class BaseController {
    @Autowired
    private IUserService userService;
    @RequestMapping ("/index.jsp")
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
    public  String login(){
        return "login";
    }

}
