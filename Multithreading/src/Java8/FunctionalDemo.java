package Java8;

//lamda is function without name
@FunctionalInterface
interface Calculator{
    public int operation(int a,int b); //one abstract method//one abstract method

}

interface Print{
    void display(String name);
}

public class FunctionalDemo {
    public static void main(String[] args) {
        System.out.println("hello");

        Calculator c1=(a,b)->
        {
            return a+b;
        };
        int res = c1.operation(10, 10);
        System.out.println(res);

        Print p=(name)->{
            System.out.println(name);
        };
        p.display("ashwani");
    }
}
