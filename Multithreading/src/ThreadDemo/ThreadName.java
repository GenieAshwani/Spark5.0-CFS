package ThreadDemo;


class MyThread implements Runnable
{
    @Override
    public void run() {
        System.out.println("Thread is running....");
    }
}

public class ThreadName {
    public static void main(String[] args) {
        Thread t1=new Thread(new MyThread());

        //getting name of thread
        String name=t1.getName(); //default name
        System.out.println("default name of thread : "+name);
        System.out.println("line executed by thread "+Thread.currentThread().getName());

        //setting name of thread
        t1.setName("MyThread-0");
        String updateName=t1.getName();
        System.out.println("updated name of thread : "+updateName);

    }
}
