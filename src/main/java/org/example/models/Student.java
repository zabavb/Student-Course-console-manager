package org.example.models;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private int course_id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Student(int id, String name, String surname, Date dateOfBirth, int course_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.course_id = course_id;
    }

    public Student(String name, String surname, Date dateOfBirth, int course_id) {
        this(0, name, surname, dateOfBirth, course_id);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + "\nSurname: " + surname +
                "\nDate of Birth: " + dateOfBirth + "\nCourse ID: " + course_id;
    }
}
