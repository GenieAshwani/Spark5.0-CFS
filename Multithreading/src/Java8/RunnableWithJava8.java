package Java8;

public class RunnableWithJava8 {
    public static void main(String[] args) {
            Thread t=new Thread(()->{
                for(int i=0;i<5;i++)
                {
                    System.out.println("Task");
                }
            });
            t.start();
        for(int i=0;i<5;i++)
        {
            System.out.println("Main");
        }
    }
}
