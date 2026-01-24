package Lockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReportService {
    public final Lock lock=new ReentrantLock();

    public void generateReport() throws InterruptedException {
        if(lock.tryLock(3, TimeUnit.SECONDS))
        {
            try {
                System.out.println(Thread.currentThread().getName()+" generating report");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }
        else {
            System.out.println(Thread.currentThread().getName()+" could not get lock");
        }
    }
}
