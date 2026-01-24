package SuperThis;

public class MyChild extends MyParent{

    void display()
    {
        System.out.println("Child class method");
    }

    @Override
    void m1() {
        System.out.println("child m1()");
    }
}
