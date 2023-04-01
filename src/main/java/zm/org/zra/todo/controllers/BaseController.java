package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zm.org.zra.todo.dtos.TodoDTO;
import zm.org.zra.todo.dtos.TodoModDTO;
import zm.org.zra.todo.models.Todo;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.services.interfaces.ITodoService;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITodoService todoService;
    @RequestMapping ("/index")
    public String index(ModelMap modelMap){

        modelMap.addAttribute("todoList",getTodos());
        modelMap.addAttribute("username",getCurrentUser());
        modelMap.addAttribute("userFullName",CurrentUser().toString());
        return  "index";
    }
    @RequestMapping ("/home")
    public String home(ModelMap modelMap){

        modelMap.addAttribute("todoList",getTodos());
        modelMap.addAttribute("username",getCurrentUser());
        modelMap.addAttribute("userFullName",CurrentUser().toString());
       return  "index";
    }
    @RequestMapping ("/todos")
    public String todos(ModelMap modelMap){

        modelMap.addAttribute("todoList",getTodos());
        modelMap.addAttribute("username",getCurrentUser());
        modelMap.addAttribute("userFullName",CurrentUser().toString());
       return  "index";
    }
    @RequestMapping ("/register")
    public String register(){
       return  "register";
    }
    @RequestMapping("/todo/new")
    public  String addTodo(TodoDTO todo){

        var newTodo = new Todo();
        newTodo.setDetails(todo.getDetails());
        newTodo.setStatus("Pending");
        newTodo.setCompletedOn(null);
        newTodo.setCreatedOn(Date.from(Instant.now()));
        newTodo.setTodoUser(CurrentUser());
        todoService.save(newTodo);
        return  "redirect:/index";
    }
    @GetMapping("/")
    public  String root(){
        return "redirect:/index";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }
    @RequestMapping("/delete-todo")
    private String deleteTodo(TodoModDTO modDTO){
        todoService.delete(Long.parseLong(modDTO.getTodoId()));
        return "redirect:/index";
    }
    @RequestMapping("/complete-todo")
    private String markComplete(TodoModDTO modDTO){
        todoService.complete(Long.parseLong(modDTO.getTodoId()));
        return "redirect:/index";
    }
   private String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
   }
   private TodoUser CurrentUser(){
        return userService.getByUsername(getCurrentUser());
   }
   private List<TodoDTO> getTodos(){
        var outPut = new ArrayList<TodoDTO>();
        int c= 1;
       for (var todo: CurrentUser().getTodos()
            ) {
           outPut.add(new TodoDTO(Long.toString(todo.getId()),Integer.toString(c),todo.getDetails(),todo.getStatus(),todo.getCreatedOn(),todo.getCompletedOn(),todo.getTodoUser().toString()));
           c++;
       }
       return  outPut;
   }
}
