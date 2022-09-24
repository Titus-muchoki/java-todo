package dao;

import models.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {

private Sql2oCategoryDao categoryDao;

private Sql2oTaskDao taskDao;
private Connection conn;

@Before
public void setUp() throws Exception{
    String ConnectionString = "jdbc:h2:mem:testing;INIT = RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o( ConnectionString, "" ,"");
    categoryDao = new Sql2oCategoryDao(sql2o);
    taskDao = new Sql2oTaskDao(sql2o);
    conn = sql2o.open();
}
@After
public  void tearDown() throws Exception{
    conn.close();
}
    @Test
    public void addingCategorySetID() throws Exception {
        Category category = setUpNewCategory();
        int originalCategoryId = category.getId();
        categoryDao.add(category);
        assertNotEquals(originalCategoryId, category.getId())
    }

    @Test
    public void existingCategoryCanBeFoundById() throws  Exception {
    Category category = setUpNewCategory();
    categoryDao.add(category);
    Category foundCategory = categoryDao.findById(category.getId());
    assertEquals(category, foundCategory);
    }

    @Test
    public void addedCategoryAreReturnedFromGetAll() throws Exception {
    Category category = new Category("the pimp squad");
    categoryDao.add(category);
    assertEquals(1, category.getAll().size());

    }

    @Test
    public void noCategoryReturnsEmptyList() throws  Exception{
    assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void updateChangesCategoryContent() throws  Exception{
    String initialDescription = "the pimp squad";
    Category  category = new Category(initialDescription);
        categoryDao.add(category);

        categoryDao.update(category.getId(), "the great women");
        Category updateCategory = categoryDao.findById(category.getId());
        assertNotEquals(initialDescription, updateCategory.getDescription());
    }

    @Test
    public void deleteByIdDeleteCorrectCategory() throws Exception {
    Category category = new Category("the pimp squad");
    categoryDao.add(category);
    categoryDao.deleteById(category.getId());
    assertEquals(0, category.getAll().size());
    }

    @Test
    public void clearALLClearsALL() throws Exception {
    Category category = new Category("the pimp squad");
    Category otherCategory = new Category("the great women");
    categoryDao.add(category);
    categoryDao.add(otherCategory);
    int daoSize = categoryDao.getAll().size();
    categoryDao.clearALLCategory();
        assertTrue(daoSize > 0 && daoSize > categoryDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.

    }
}
