package dao;

import models.Category;
import org.sql2o.Sql2o;

import java.util.Collection;

public class Sql2oCategoryDao implements CategoryDao{
    private final Sql2o sql2o;
    public Sql2oCategoryDao(Sql2o sql2o) {
    this.sql2o = sql2o;
    }

    public void add(Category category) {

    }

    public Collection<Object> getAll() {
        return null;
    }

    public void update(int id, String the_great_women) {

    }

    public Category findById(int id) {
        return null;
    }

    public void deleteById(int id) {

    }

    @Override
    public void clearAllCategories() {

    }

    public void clearALLCategory() {

    }

    public Collection<Object> getAllTasksByCategory(int categoryId) {
        return null;
    }
}
