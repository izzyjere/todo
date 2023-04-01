package zm.org.zra.todo.dtos;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private Set<TodoDTO> todos = new HashSet<>();
}