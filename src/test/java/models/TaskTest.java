package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static models.Task.*;
import static org.junit.Assert.*;

public class TaskTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        clearAllTasks(); // clear out all the tasks before each test
    }

    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Task task = setupNewTask();
        assertEquals(true, task instanceof Task);
    }

    @Test
    public void TaskInstantiatesWithDescription_true() throws Exception {
        Task task = setupNewTask();
        assertEquals("Mow the lawn", task.getDescription());
    }

    @Test
    public void AllTasksAreCorrectlyReturned_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, getAll().size());
    }

    @Test
    public void AllTasksContainsAllTasks_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertTrue(getAll().containsValue(task));
        assertTrue(getAll().containsValue(otherTask));
    }

    @Test
    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
        Task task = setupNewTask();
        assertEquals(false, task.getCompleted()); //should never start as completed
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Task task = setupNewTask();
        assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void tasksInstantiateWithId() throws Exception {
        Task task = setupNewTask();
        assertEquals(1, task.getId());
    }

    @Test
    public void findReturnsCorrectTask() throws Exception {
        Task task = setupNewTask();
        assertEquals(1, findById(task.getId()).getId());
    }

    private Task findById(int id) {
            return null;
    }


    @Test
    public void findReturnsCorrectTaskWhenMoreThanOneTaskExists() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, findById(otherTask.getId()).getId());
    }

    @Test
    public void updateChangesTaskContent() throws Exception {
        Task task = setupNewTask();
        String formerContent = task.getDescription();
        LocalDateTime formerDate = task.getCreatedAt();
        int formerId = task.getId();

        task.update("Floss the cat");

        assertEquals(formerId, task.getId());
        assertEquals(formerDate, task.getCreatedAt());
        assertNotEquals(formerContent, task.getDescription());
    }

    @Test
    public void deleteDeletesASpecificTask() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        task.deleteTask();
        assertEquals(1, getAll().size()); //one is left
        assertEquals(getAll().get(0).getClass(), 2); //the one that was deleted has the id of 2
    }

    @Test
    public void deleteAllTasksDeletesAllTasks() throws Exception {
        Task task = setupNewTask();
        Task otherTask = setupNewTask();
        clearAllTasks();
        assertEquals(0, getAll().size());
    }


    //helper methods
    public Task setupNewTask(){
        return new Task("Mow the lawn");
    }
}