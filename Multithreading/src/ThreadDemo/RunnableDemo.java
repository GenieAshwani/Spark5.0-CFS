package ThreadDemo;

public class RunnableDemo {
    public static void main(String[] args) {
        MyRunnable r=new MyRunnable();
        Thread t1=new Thread(r);

        t1.start();
    }
}
