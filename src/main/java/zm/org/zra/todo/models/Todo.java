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
    @Column(nullable = false)
    private String details;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Date createdOn;
    @Column(nullable = false)
    private Date completedOn;
    @ManyToOne
    @JoinColumn(name = "todo_user_id")
    private TodoUser todoUser;
}
