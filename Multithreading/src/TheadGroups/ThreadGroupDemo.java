package TheadGroups;


class MyThread extends Thread
{
    public MyThread(ThreadGroup group, String name)
    {
        super(group,name);
    }

    @Override
    public void run() {
        System.out.println("Thread name: "+Thread.currentThread().getName() +
             "Group: "+Thread.currentThread().getThreadGroup().getName());
    }
}

public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup apiGroup=new ThreadGroup("API-group");
        MyThread t1=new MyThread(apiGroup,"API-Thread-1");
        MyThread t2=new MyThread(apiGroup,"API-Thread-2");
        t1.start();
        t2.start();
    }
}
