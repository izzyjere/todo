package zm.org.zra.todo.services.interfaces;

import zm.org.zra.todo.dtos.ActionResult;

import java.util.List;

public interface ICrudService<TModel> {
    TModel getById(long id);
    List<TModel> getAll();
    TModel save(TModel entity);
    ActionResult delete(long id);
}
