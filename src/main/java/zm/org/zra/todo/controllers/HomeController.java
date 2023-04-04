package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zm.org.zra.todo.dtos.TodoDTO;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;
    @RequestMapping ("/index")
    public String index(ModelMap modelMap){

        modelMap.addAttribute("todoList",getTodos());
        modelMap.addAttribute("username", getCurrentUsername());
        modelMap.addAttribute("userFullName", getCurrentUser().toString());
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
    public String login() {
        return "login";
    }

   private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
   }
   private TodoUser getCurrentUser(){
        return userService.getByUsername(getCurrentUsername());
   }
   private List<TodoDTO> getTodos(){
        var outPut = new ArrayList<TodoDTO>();
        int count= 1;
       for (var todo: getCurrentUser().getTodos()
            ) {
           outPut.add(new TodoDTO(Long.toString(todo.getId()),Integer.toString(count),todo.getDetails(),todo.getStatus(),todo.getCreatedOn(),todo.getCompletedOn(),todo.getTodoUser().toString()));
           count++;
       }
       return  outPut;
   }
}
