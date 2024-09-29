package org.example.models;

public class Course {
    private int id;
    private String name;
    private String description;
    private int capacity;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Course(int id, String name, String description, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }

    public Course(String name, String description, int capacity) {
        this(0, name, description, capacity);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + "\nDescription: " + description + "\nCapacity: " + capacity;
    }
}
