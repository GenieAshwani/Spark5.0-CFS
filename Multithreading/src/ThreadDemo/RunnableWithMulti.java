package ThreadDemo;


class MyTask implements Runnable
{

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Thread.currentThread().getName()+" executing "+i);
        }
    }
}


public class RunnableWithMulti {
    public static void main(String[] args) {
        MyTask task=new MyTask();
        Thread t0=new Thread(task);
        Thread t1=new Thread(task);
        t1.start();
        t0.start();
    }
}
