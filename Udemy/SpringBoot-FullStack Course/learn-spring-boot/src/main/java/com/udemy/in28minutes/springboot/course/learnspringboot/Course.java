package com.udemy.in28minutes.springboot.course.learnspringboot;

public class Course {

    private long ID;
    private String name;
    private String author;

    public Course(long ID, String name, String author) {
        this.ID = ID;
        this.name = name;
        this.author = author;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
