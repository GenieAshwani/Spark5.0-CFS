package Overloading;

public class OverloadingDemo {

    public int add(int a,int b)
    {
        return a+b;
    }
    public int add(int a, int b, int c)
    {
        return a+b+c;
    }

    public void print()
    {
        System.out.println("hello ji");
    }

    public void print(String name)
    {
        System.out.println("hello ji "+name);
    }


}
