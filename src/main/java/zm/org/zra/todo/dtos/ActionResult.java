package zm.org.zra.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionResult {
    private  boolean succeeded;
    private  String message;
}
