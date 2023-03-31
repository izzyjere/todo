package zm.org.zra.todo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zm.org.zra.todo.dtos.RegisterDTO;
import zm.org.zra.todo.models.TodoUser;
import zm.org.zra.todo.repositories.IUserRepository;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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

    @Override
    public String register(RegisterDTO request) {
        try {
            var existingUser = repository.findByUsername(request.getUsername());
            if(existingUser != null){
                return  "User with username " + request.getUsername() + " already exist.";
            }
            var user = new TodoUser();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(passwordEncoder.encode((request.getPassword())));
            user.setUsername(request.getUsername());
            save(user);
            return  "User: " + user.getUsername() + "Registered Successfully.";
        }catch (Exception e){
            return  e.getMessage();
        }
    }
}
