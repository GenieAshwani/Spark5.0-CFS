public class Student {
    Course course;
    Student()
    {

    }
   // DSA dsa=new DSA(); //tight coupling
    Student(Course course)
    {
        this.course=course;
    }
    //constructor dependency injection

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    //Setter dependency injection

    void learn()
    {
        System.out.println("learning started....");
        course.start();

    }
}
