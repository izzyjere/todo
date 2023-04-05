package zm.org.zra.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zm.org.zra.todo.models.Todo;

import java.util.List;

public interface ITodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAllByTodoUserId(Long userid);
}
