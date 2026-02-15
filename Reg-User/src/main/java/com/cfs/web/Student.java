package com.cfs.web;

public class Student {
    public final String username;
    public final String rollno;
    public final String course;
    public final int marks;


    public Student(String username, String rollno, String course, int marks) {
        this.username = username;
        this.rollno = rollno;
        this.course = course;
        this.marks = marks;
    }


    public String getUsername() {
        return username;
    }

    public String getRollno() {
        return rollno;
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
                "username='" + username + '\'' +
                ", rollno='" + rollno + '\'' +
                ", course='" + course + '\'' +
                ", marks=" + marks +
                '}';
    }
}
