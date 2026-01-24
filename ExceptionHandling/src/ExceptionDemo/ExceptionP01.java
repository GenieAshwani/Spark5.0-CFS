package ExceptionDemo;

public class ExceptionP01 {
    static void main(String[] args) {
        try {
            System.out.println("A");
            System.out.println(10/0);
            System.out.println("B");
        }
        catch (ArithmeticException e)
        {
            System.out.println("C");
            System.out.println(e.getMessage());
            System.out.println("D");
        }
        System.out.println("E");

    }
}
