package models;

import java.util.Collection;

public class Category {
    public Category(String name) {
        this.name = name;
    }

    private String name;
    private int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String findById() {
        return null;
    }

    public Collection<Object> getAll() {
        return getAll();
    }

    public String getDescription() {
        return null;
    }
}