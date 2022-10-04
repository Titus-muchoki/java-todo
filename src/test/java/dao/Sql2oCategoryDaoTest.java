//package dao;
//
//import models.Category;
//import models.Task;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.*;
//
//public class Sql2oCategoryDaoTest {
//
//private Sql2oCategoryDao categoryDao;
//
//private Sql2oTaskDao taskDao;
//private Connection conn;
//
//@Before
//public void setUp() throws Exception{
//    String ConnectionString = "jdbc:h2:mem:testing;INIT = RUNSCRIPT from 'classpath:db/create.sql'";
//    Sql2o sql2o = new Sql2o( ConnectionString, "" ,"");
//    categoryDao = new Sql2oCategoryDao(sql2o);
//    taskDao = new Sql2oTaskDao(sql2o);
//    conn = sql2o.open();
//}
//@After
//public  void tearDown() throws Exception{
//    conn.close();
//}
//    @Test
//    public void addingCategorySetID() throws Exception {
//        Category category = setUpNewCategory();
//        int originalCategoryId = category.getId();
//        categoryDao.add(category);
//        assertNotEquals(originalCategoryId, category.getId())
//    }
//
//    @Test
//    public void existingCategoryCanBeFoundById() throws  Exception {
//    Category category = setUpNewCategory();
//    categoryDao.add(category);
//    Category foundCategory = categoryDao.findById(category.getId());
//    assertEquals(category, foundCategory);
//    }
//
//    @Test
//    public void addedCategoryAreReturnedFromGetAll() throws Exception {
//    Category category = setUpNewCategory();
//    categoryDao.add(category);
//    assertEquals(1, categoryDao.getAll().size());
//
//    }
//
//    @Test
//    public void noCategoryReturnsEmptyList() throws  Exception{
//    assertEquals(0, categoryDao.getAll().size());
//    }
//
//    @Test
//    public void updateChangesCategoryContent() throws  Exception{
//    String initialDescription = "the pimp squad";
//    Category  category = new Category(initialDescription);
//        categoryDao.add(category);
//
//        categoryDao.update(category.getId(), "the great women");
//        Category updateCategory = categoryDao.findById(category.getId());
//        assertNotEquals(initialDescription, updateCategory.getName());
//    }
//
//    @Test
//    public void deleteByIdDeleteCorrectCategory() throws Exception {
//    Category category = setUpNewCategory();
//    categoryDao.add(category);
//    categoryDao.deleteById(category.getId());
//    assertEquals(0, category.getAll().size());
//    }
//
//    @Test
//    public void clearALLClearsALL() throws Exception {
//    Category category = setUpNewCategory();
//    Category otherCategory = new Category("sponsors");
//    categoryDao.add(category);
//    categoryDao.add(otherCategory);
//    int daoSize = categoryDao.getAll().size();
//    categoryDao.clearALLCategory();
//        assertTrue(daoSize > 0 && daoSize > categoryDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
//
//    }
//
//    @Test
//    public void getALlTaskByCategoryReturnsAllTaskCorrectly() throws Exception {
//    Category category = setUpNewCategory();
//    categoryDao.add(category);
//    int categoryId = category.getId();
//        Task newTask = new Task("mow the lawn", categoryId);
//        Task otherTask = new Task("tito", categoryId);
//        Task thirdTask = new Task("kajela", categoryId);
//        taskDao.add(newTask);
//        taskDao.add(otherTask);
//        assertEquals(2, categoryDao.getAllTasksByCategory(categoryId).size());
//        assertTrue(categoryDao.getAllTasksByCategory(categoryId).contains(newTask));
//        assertTrue(categoryDao.getAllTasksByCategory(categoryId).contains(otherTask));
//        assertFalse(categoryDao.getAllTasksByCategory(categoryId).contains(thirdTask)); //
//
//    }
//    public  Category setUpNewCategory(){
//    return new Category("kajela to the world");
//    }
//}
package dao;

import models.Category;
import models.Task;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oCategoryDaoTest {
    private Sql2oCategoryDao categoryDao;
    private Sql2oTaskDao taskDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/todolist_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        categoryDao = new Sql2oCategoryDao(sql2o);
        taskDao = new Sql2oTaskDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        categoryDao.clearAllCategories();
        taskDao.clearAllTasks();
    }

    @AfterClass
    public static void shutDown () throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingCategorySetsId() throws Exception {
        Category category = setupNewCategory();
        int originalCategoryId = category.getId();
        categoryDao.add(category);
        assertNotEquals(originalCategoryId, category.getId());
    }

    @Test
    public void existingCategoriesCanBeFoundById() throws Exception {
        Category category = setupNewCategory();
        categoryDao.add(category);
        Category foundCategory = categoryDao.findById(category.getId());
        assertEquals(category, foundCategory);
    }

    @Test
    public void addedCategoriesAreReturnedFromGetAll() throws Exception {
        Category category = setupNewCategory();
        categoryDao.add(category);
        assertEquals(1, categoryDao.getAll().size());
    }

    @Test
    public void noCategoriesReturnsEmptyList() throws Exception {
        assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void updateChangesCategoryContent() throws Exception {
        String initialDescription = "Yardwork";
        Category category = new Category (initialDescription);
        categoryDao.add(category);
        categoryDao.update(category.getId(),"Cleaning");
        Category updatedCategory = categoryDao.findById(category.getId());
        assertNotEquals(initialDescription, updatedCategory.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectCategory() throws Exception {
        Category category = setupNewCategory();
        categoryDao.add(category);
        categoryDao.deleteById(category.getId());
        assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void clearAllClearsAllCategories() throws Exception {
        Category category = setupNewCategory();
        Category otherCategory = new Category("Cleaning");
        categoryDao.add(category);
        categoryDao.add(otherCategory);
        int daoSize = categoryDao.getAll().size();
        categoryDao.clearAllCategories();
        assertTrue(daoSize > 0 && daoSize > categoryDao.getAll().size());
    }

    @Test
    public void getAllTasksByCategoryReturnsTasksCorrectly() throws Exception {
        Category category = setupNewCategory();
        categoryDao.add(category);
        int categoryId = category.getId();
        Task newTask = new Task("mow the lawn", categoryId);
        Task otherTask = new Task("pull weeds", categoryId);
        Task thirdTask = new Task("trim hedge", categoryId);
        taskDao.add(newTask);
        taskDao.add(otherTask); //we are not adding task 3 so we can test things precisely.
        assertEquals(2, categoryDao.getAllTasksByCategory(categoryId));
        assertTrue(categoryDao.getAllTasksByCategory(categoryId).contains(newTask));
        assertTrue(categoryDao.getAllTasksByCategory(categoryId).contains(otherTask));
        assertFalse(categoryDao.getAllTasksByCategory(categoryId).contains(thirdTask)); //things are accurate!
    }

    // helper method
    public Category setupNewCategory(){
        return new Category("Yardwork");
    }
}
