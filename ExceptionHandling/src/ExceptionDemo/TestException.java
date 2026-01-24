package ExceptionDemo;

public class TestException {
    static void main(String[] args) {
        System.out.println("before try");
        try {
            System.out.println("before risky code");
            System.out.println(10/0); //risky code
            System.out.println("after risky code");
        }
        catch (Exception e)
        {
            System.out.println("in catch block");
            e.printStackTrace();
        }

        System.out.println("after risky code");
        System.out.println("CFS");
        System.out.println("CFS");
        System.out.println("CFS");
        System.out.println("CFS");
    }
}
