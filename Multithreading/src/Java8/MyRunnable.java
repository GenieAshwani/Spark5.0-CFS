package Java8;


class RunnableDemo implements Runnable
{

    @Override
    public void run() {
        for(int i=0;i<5;i++)
        {
            System.out.println("Task");
        }
    }
}

public class MyRunnable {
    public static void main(String[] args) {
        Runnable r=new RunnableDemo();
        Thread t=new Thread(r);
        t.start();
        for(int i=0;i<5;i++)
        {
            System.out.println("Main");
        }

    }
}
