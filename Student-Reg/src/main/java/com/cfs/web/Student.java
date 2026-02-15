package com.cfs.web;

public class Student {
    private final String name;
    private final String roll;
    private final String course;
    private final int marks;

    public Student(String name, String roll, String course, int marks) {
        this.name = name;
        this.roll = roll;
        this.course = course;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getCourse() {
        return course;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", roll='" + roll + '\'' +
                ", course='" + course + '\'' +
                ", marks=" + marks +
                '}';
    }
}
