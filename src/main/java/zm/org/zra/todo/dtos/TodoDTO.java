package zm.org.zra.todo.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDTO {
    private long id;
    private String details;
    private String status;
    private Date createdOn;
    private Date completedOn;
    private String owner;
}
