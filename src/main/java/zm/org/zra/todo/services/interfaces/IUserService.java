package zm.org.zra.todo.services.interfaces;

import org.springframework.stereotype.Service;
import zm.org.zra.todo.dtos.ActionResult;
import zm.org.zra.todo.dtos.LoginDTO;
import zm.org.zra.todo.dtos.RegisterDTO;
import zm.org.zra.todo.models.TodoUser;
@Service
public interface IUserService extends  ICrudService<TodoUser>{
    TodoUser getByUsername(String username);
    ActionResult register(RegisterDTO request);

}
