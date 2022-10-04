
package models;

import java.util.Objects;

import static java.util.Currency.instances;

public class Category {
    private String name;
    private int id;
//    private static int instances;
//
//    public int getInstances() {
//        return instances;
//    }
//
//    public void setInstance(int instances) {
//        Category.instances = instances;
//    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static Category find(int id) {
        try {
            return instances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}