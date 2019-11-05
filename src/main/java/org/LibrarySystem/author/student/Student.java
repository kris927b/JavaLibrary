package main.java.org.LibrarySystem.author.student;

import main.java.org.LibrarySystem.author.Author;

public class Student extends Author {
    private double GPA;

    public Student(String name, double GPA) {
        super(name);
        this.GPA = GPA;
    }

    public double getGPA() {
        return this.GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "{" +
            " GPA='" + getGPA() + "'" +
            "}";
    }
}