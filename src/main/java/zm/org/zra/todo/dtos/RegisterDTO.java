package zm.org.zra.todo.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
