package org.hprtech.dto;

public class Student {
    public Student(int id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    private int id;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Student() {
    }

    private String name;
    private boolean isActive;


}
