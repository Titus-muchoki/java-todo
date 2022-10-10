//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import dao.Sql2oTaskDao;
//import models.Category;
//import models.Task;
//import org.sql2o.Sql2o;
//import spark.ModelAndView;
//import spark.template.handlebars.HandlebarsTemplateEngine;
//import static spark.Spark.*;
//
//public class App {
//    public static void main(String[] args) { //type “psvm + tab” to autocreate this
//        staticFileLocation("/public");
//        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        Sql2oTaskDao taskDao = new Sql2oTaskDao(sql2o);
//
//
////get: show all tasks in all categories and show all categories
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Category> allCategories = categoryDao.getAll();
//            model.put("categories", allCategories);
//            List<Task> tasks = taskDao.getAll();
//            model.put("tasks", tasks);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
//        //get: delete all tasks
//        get("/tasks/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            taskDao.clearAllTasks();
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
//
//        //get: delete an individual task
//        get("/tasks/:id/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTaskToDelete = Integer.parseInt(req.params("id"));
//            taskDao.deleteById(idOfTaskToDelete);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
//
//        //get: show all tasks
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Task> tasks = taskDao.getAll();
//            model.put("tasks", tasks);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show new task form
//        get("/tasks/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "task-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //task: process new task form
//        post("/tasks", (req, res) -> { //URL to make new task on POST route
//            Map<String, Object> model = new HashMap<>();
//            String description = req.queryParams("description");
//            int categoryId = 0;
//            Task newTask = new Task(description, categoryId);
//            taskDao.add(newTask);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
//
//        //get: show an individual task
//        get("/tasks/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTaskToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            Task foundTask = taskDao.findById(idOfTaskToFind); //use it to find task
//            model.put("task", foundTask); //add it to model for template to display
//            return new ModelAndView(model, "task-detail.hbs"); //individual task page.
//        }, new HandlebarsTemplateEngine());
//
//        //get: show a form to update a task
//        get("/tasks/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
//            Task editTask = taskDao.findById(idOfTaskToEdit);
//            model.put("editTask", editTask);
//            return new ModelAndView(model, "task-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //task: process a form to update a task
//        post("/tasks/:id", (req, res) -> { //URL to update task on POST route
//            Map<String, Object> model = new HashMap<>();
//            String newContent = req.queryParams("description");
//            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
//            taskDao.update(idOfTaskToEdit, newContent);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
//
//    }
//}
import dao.Sql2oCategoryDao;
import models.Category;
import models.Task;
import dao.Sql2oTaskDao;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/todolist"; //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        Sql2oTaskDao taskDao = new Sql2oTaskDao(sql2o);
        Sql2oCategoryDao categoryDao = new Sql2oCategoryDao(sql2o);



//get: show all tasks in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Category> allCategories = categoryDao.getAll();
            model.put("categories", allCategories);
            List<Task> tasks = taskDao.getAll();
            model.put("tasks", tasks);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all tasks in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Task> tasks = taskDao.getAll();
            model.put("tasks", tasks);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new category
        //  /categories/new

        //post: process a form to create a new category
        //  /categories

        //get: delete all categories and all tasks
        //  /categories/delete

        //get: delete all tasks
        get("/tasks/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            taskDao.clearAllTasks();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get a specific category (and the tasks it contains)
        //  /categories/:category_id

        //get: show a form to update a category
        //  /categories/:id/edit

        //post: process a form to update a category
        //  /categories/:id

        //get: delete a category and tasks it contains
        //  /categories/:id/delete

        //get: delete an individual task
        get("/categories/:category_id/tasks/:task_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToDelete = Integer.parseInt(req.params("task_id"));
            taskDao.deleteById(idOfTaskToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/tasks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/tasks", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String description = req.queryParams("description");
            Task newTask = new Task(description, 1 ); //ignore the hardcoded categoryId for now
            taskDao.add(newTask);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task that is nested in a category
        get("/categories/:category_id/tasks/:task_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToFind = Integer.parseInt(req.params("task_id"));
            Task foundTask = taskDao.findById(idOfTaskToFind);
            model.put("task", foundTask);
            return new ModelAndView(model, "task-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/tasks/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
            Task editTask = taskDao.findById(idOfTaskToEdit);
            model.put("editTask", editTask);
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/tasks/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("description");
            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
            taskDao.update(idOfTaskToEdit, newContent, 1); //ignore the hardcoded categoryId for now
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //show new category form
        get("/categories/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Category> categories = categoryDao.getAll(); //refresh list of links for navbar
            model.put("categories", categories);
            return new ModelAndView(model, "category-form.hbs"); //new
        }, new HandlebarsTemplateEngine());


        //post: process new category form
        post("/categories", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Category newCategory = new Category(name);
            categoryDao.add(newCategory);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: show an individual category and tasks it contains
        get("/categories/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfCategoryToFind = Integer.parseInt(req.params("id")); //new
            Category foundCategory = categoryDao.findById(idOfCategoryToFind);
            model.put("category", foundCategory);
            List<Task> allTasksByCategory = categoryDao.getAllTasksByCategory(idOfCategoryToFind);
            model.put("tasks", allTasksByCategory);
            model.put("categories", categoryDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "category-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());



            }
}