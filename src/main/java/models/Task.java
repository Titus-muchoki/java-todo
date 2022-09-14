package models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Task {

    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

//    public static void clearAllTasks() {
//
//    }
//
//    public static Map<Object, Object> getAll() {
//            return null;
//    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Task task = (Task) o;
//        return completed == task.completed && id == task.id && Objects.equals(description, task.description) && Objects.equals(createdAt, task.createdAt);
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getCompleted() == task.getCompleted() &&
                getId() == task.getId() &&
                Objects.equals(getDescription(), task.getDescription());
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(description, completed, createdAt, id);
//    }
    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getCompleted(), getId());
    }
//    private void setDescription(String description) {
//        this.description = description;
//    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}

//    public void update(String floss_the_cat) {
//
//    }
//
//    public void deleteTask() {
//
//    }


