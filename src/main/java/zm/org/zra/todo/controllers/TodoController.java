package zm.org.zra.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zm.org.zra.todo.dtos.ActionResult;
import zm.org.zra.todo.dtos.AddEditTodoDTO;
import zm.org.zra.todo.models.Todo;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.services.interfaces.ITodoService;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.time.Instant;
import java.util.Date;

@RestController
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
    public ResponseEntity<?> saveTodo(@RequestBody AddEditTodoDTO entity){
        try {
            if(entity.getId()>0){
                var extistingTodo = todoService.getById(entity.getId());
                extistingTodo.setDetails(entity.getDetails());
                var saved = todoService.save(extistingTodo);
                if(saved!=null){
                    return  ResponseEntity.accepted().body(new ActionResult(true,"Todo saved."));
                }
                return ResponseEntity.internalServerError().body(new ActionResult(false,"Failed to save todo."));
            }
            var todo = new Todo();
            todo.setDetails(entity.getDetails());
            todo.setStatus("Pending");
            todo.setCompletedOn(null);
            todo.setCreatedOn(Date.from(Instant.now()));
            todo.setTodoUser(getCurrentUser());

            var saved = todoService.save(todo);
            if(saved!=null){
                return  ResponseEntity.accepted().body(new ActionResult(true,"Todo saved."));
            }
            return ResponseEntity.internalServerError().body(new ActionResult(false,"Failed to save todo."));

        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private TodoUser getCurrentUser(){
        return userService.getByUsername(getCurrentUsername());
    }
}
