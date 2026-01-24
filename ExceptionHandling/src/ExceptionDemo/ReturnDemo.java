package ExceptionDemo;

public class ReturnDemo {

    static void main(String[] args) {

        final int a=10;
        //a=30;

        int res=m2();
        System.out.println(res);
    }

    public static int m2()
    {
        try {
            System.out.println(10/0);

            return 777;
        }
        catch (ArithmeticException e)
        {
            System.exit(0);
            return 111;

        }
        finally {
            return 7676;
        }

    }

}
