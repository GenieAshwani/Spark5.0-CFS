package ThreadDemo;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Task running in thread "+Thread.currentThread().getName());
    }
}
