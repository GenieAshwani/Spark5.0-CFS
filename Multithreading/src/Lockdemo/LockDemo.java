package Lockdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 class BackAccount {
    private int balance=1000;
    private final Lock lock = new ReentrantLock(true);

    void withdraw(int amount)
    {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" entered");
            balance=balance-amount;
            System.out.println("Balance after withdrawal: "+balance);
            while (true)
            {

            }
        }
       finally {
                lock.unlock();
        }
    }
}


public class LockDemo
{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        BackAccount account=new BackAccount();
        executor.submit(()->account.withdraw(500));
        executor.submit(()->account.withdraw(400));
        executor.submit(()->account.withdraw(100));

        System.out.println("i am doing something");

        executor.shutdown();
    }
}
