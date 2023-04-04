package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zm.org.zra.todo.dtos.AddEditTodoDTO;
import zm.org.zra.todo.models.Todo;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.services.interfaces.ITodoService;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.time.Instant;
import java.util.Date;

@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private ITodoService todoService;
    @Autowired
    private IUserService userService;
    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteTodo(@RequestParam("id") long id){
        var result = todoService.delete(id);
        if(result.isSucceeded()){
            return  ResponseEntity.ok(result);
        }
        else {
            return  ResponseEntity.badRequest().body(result);
        }
    }
    @PutMapping("/complete")
    private ResponseEntity<?> markComplete(@RequestParam("id")long id){
        var result =  todoService.complete(id);
        if(result.isSucceeded()){
            return  ResponseEntity.ok(result);
        }
        else {
            return  ResponseEntity.badRequest().body(result);
        }
    }
    @PostMapping
    public String saveTodo(@RequestBody AddEditTodoDTO entity){

        if(entity.getId()>0){
          var extistingTodo = todoService.getById(entity.getId());
          extistingTodo.setDetails(entity.getDetails());
           return "redirect:/index";
        }
        var todo = new Todo();
        todo.setDetails(entity.getDetails());
        todo.setStatus("Pending");
        todo.setCompletedOn(null);
        todo.setCreatedOn(Date.from(Instant.now()));
        todo.setTodoUser(getCurrentUser());

        return  "redirect:/index";
    }
    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private TodoUser getCurrentUser(){
        return userService.getByUsername(getCurrentUsername());
    }
}
