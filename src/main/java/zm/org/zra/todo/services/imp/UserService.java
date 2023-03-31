package zm.org.zra.todo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.repositories.IUserRepository;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Override
    public TodoUser getById(long id) {
        var record = repository.findById(id);
         if(record.isPresent()){
             return  record.get();
         }
        return null;
    }

    @Override
    public List<TodoUser> getAll() {
       return  repository.findAll();
    }

    @Override
    public TodoUser save(TodoUser entity) {
        return repository.save(entity);
    }

    @Override
    public String delete(long id) {
        var user = getById(id);
        if(user != null){
            repository.delete(user);
            return  "User : " + user.getUsername() + "Deleted";
        }
        return "User deletion failed. User not found.";
    }

    @Override
    public TodoUser getByUsername(String username) {
        var record = repository.findByUsername(username);
        if(record.isPresent()){
            return  record.get();
        }
        return null;
    }
}
