package zm.org.zra.todo.dtos;

import lombok.Data;

@Data
public class AddEditTodoDTO {
    private  String details;
    private  String status;
    private  long id;
}
