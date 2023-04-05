package zm.org.zra.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zm.org.zra.todo.models.TodoUser;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<TodoUser,Long> {
   Optional<TodoUser> findByUsername(String username);
}
