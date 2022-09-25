
package dao;

import models.Category;
import models.Task;
import java.util.List;

public interface CategoryDao {

    void clearAllCategory();

    List<Task> getAllListByCategory();

    //LIST
    List<Category> getAll();

    //CREATE
    void add (Category category);

    //READ
    Category findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllCategories();
}
