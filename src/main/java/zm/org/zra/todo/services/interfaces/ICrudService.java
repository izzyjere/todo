package zm.org.zra.todo.services.interfaces;

import java.util.List;

public interface ICrudService<TModel> {
    TModel getById(long id);
    List<TModel> getAll();
    TModel save(TModel entity);
    String delete(long id);
}
