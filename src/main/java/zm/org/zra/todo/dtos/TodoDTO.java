package zm.org.zra.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TodoDTO {
    private  String id;
    private String count;
    private String details;
    private String status;
    private Date createdOn;
    private Date completedOn;
    private String owner;
}
