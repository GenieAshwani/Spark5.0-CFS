package org.cfs;

public class Student {

    Course course;

    Student()
    {
        System.out.println("Student default constructor....");
    }

    public Student(Course course) {
        System.out.println("Using para constructor...");
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        System.out.println("Using setter...");
        this.course = course;
    }

    void learning()
    {
        course.start();;
        System.out.println("learning started....");
    }
}
