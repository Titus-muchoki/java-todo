package dao;

//import models.Category;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
import models.Category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;
import java.util.Collection;

import static jdk.nashorn.internal.objects.NativeFunction.bind;
import static sun.net.www.protocol.http.AuthenticatorKeys.getKey;

public class Sql2oCategoryDao implements CategoryDao{
    private final Sql2o sql2o;
    public Sql2oCategoryDao(Sql2o sql2o) {
    this.sql2o = sql2o;
    }
    @Override
    public void add(Category category) {
    String sql = "INSERT INTO categories(name) VALUES (:NAME)";
    try(Connection conn = sql2o.open()){
        int id = (int) con.createQuery(sql, true);
        .bind(category);
        .excuteUpdate();
        .getKey();
        category.setId(id);
    }catch (Sql2oException ex){
        System.out.println(ex);
    }

    }
    @Override
    public  List<Category> getAll(){
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM categories")
                    .executeAndFetch(Category.class);
            }
        }
        @Override
        public Category findById(int iD){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM categories WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
        }

    }
    @Override
    public  void update(int id, String newName){
        String sql = ("UPDATE categories SET name = :name WHERE id = :id")
                try(Connection con = sql2o.open()){
                    con.createQuery(sql)
                            .addParameter("name", newName)
                            .addParameter("id", id)
                            .executeAndFetchFirst(Category.class);
                }catch (Sql2oException ex){
                    System.out.println(ex);
                }

    }
    @Override
    public  void deleteById(int id){
        String sql = ("DELETE from categories  WHERE id = :id")
                try(Connection con = sql2o.open()){
                    con.createQuery(sql)
                            .addParameter("id", id)
                            .executeUpdate()
                }catch (Sql2oException ex){
                    System.out.println(ex);
                }
    }
    @Override
    public void clearAllCategory(){
        String sql = "DELETE from categories";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate()
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

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
