package zm.org.zra.todo.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String details;
    private String status;
    private Date createdOn;
    private Date completedOn;
    @ManyToOne
    @JoinColumn(name = "todo_user_id")
    private TodoUser todoUser;
}
