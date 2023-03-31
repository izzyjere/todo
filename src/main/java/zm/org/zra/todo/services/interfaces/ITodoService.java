package zm.org.zra.todo.services.interfaces;

import org.springframework.stereotype.Service;
import zm.org.zra.todo.models.Todo;

import java.util.List;

@Service
public interface ITodoService extends ICrudService<Todo> {
    List<Todo> getByUserId(long id);
}
