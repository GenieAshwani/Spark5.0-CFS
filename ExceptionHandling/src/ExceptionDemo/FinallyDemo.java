package ExceptionDemo;

public class FinallyDemo {
    static void main(String[] args) {
        int res=div(10,0);
        System.out.println(res);
    }

    public static int div(int a,int b)
    {
        int res=0;
        try {
            System.out.println("Inside try....");
            res= a/b;
            System.out.println("exception raised.....");
        }
        catch (ArithmeticException e)
        {
            System.out.println("Exception catched.....");
            System.out.println("catch inside exception");
        }
        finally {
            //cleanup activity
            System.out.println("finally executed");
        }
        return res;
    }
}
