package Returndemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableDemoReturn {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);

        Runnable emailTask=()->{
            System.out.println("sending email "+Thread.currentThread().getName());
        };

        executorService.submit(emailTask);

        executorService.shutdown();


    }
}
