package zm.org.zra.todo.services.interfaces;

import org.springframework.stereotype.Service;
import zm.org.zra.todo.models.TodoUser;
@Service
public interface IUserService extends  ICrudService<TodoUser>{
    TodoUser getByUsername(String username);
}
