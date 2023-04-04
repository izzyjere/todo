package zm.org.zra.todo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.org.zra.todo.dtos.ActionResult;
import zm.org.zra.todo.models.Todo;
import zm.org.zra.todo.repositories.ITodoRepository;
import zm.org.zra.todo.services.interfaces.ITodoService;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class TodoService implements ITodoService {
    @Autowired
    private ITodoRepository repository;
    @Override
    public Todo getById(long id) {
        var record = repository.findById(id);
        if(record.isPresent()){
            return  record.get();
        }
        return null;
    }

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public Todo save(Todo entity) {
        return repository.save(entity);
    }

    @Override
    public ActionResult delete(long id) {
        var todo = getById(id);
        if(todo != null){
            repository.delete(todo);
            return new ActionResult(true,"Todo : " + todo.getDetails() + "Deleted") ;
        }
        return new ActionResult(false, "Todo deletion failed. Record was not found.");
    }

    @Override
    public List<Todo> getByUserId(long id) {
        return repository.findAllByTodoUserId(id);
    }

    @Override
    public ActionResult complete(long id) {
        try{
            var todo = getById(id);
            todo.setCompletedOn(Date.from(Instant.now()));
            todo.setStatus("Completed");
            save(todo);
            return  new ActionResult(true,"Marked as complete");
        }catch (Exception e){
            return new ActionResult(false,e.getMessage());
        }

    }
}
