public class StaticDemo {
    static void main(String[] args) {
        Student s1=new Student();
        s1.display();

        Student s2=new Student();
        Student.collegeName="Code for success";
        s2.display();
        System.out.println("------------------------");
        s1.display();

        StaticDemo.test();
    }

    public static void test()
    {

    }
}
