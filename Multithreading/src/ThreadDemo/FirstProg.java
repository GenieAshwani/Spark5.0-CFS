package ThreadDemo;



class Mythread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Thread running - "+Thread.currentThread().getName());
        }
    }
}


public class FirstProg {
    public static void main(String[] args) {
        System.out.println("hello");
        Mythread t0= new Mythread();
        t0.start(); //create a new thread
        System.out.println(Thread.currentThread().getName());
        Mythread t1= new Mythread();
        t1.start();
        for (int i=0;i<5;i++)
        {
            System.out.println("main thread...");
        }
    }
}
