package Returndemo;

import java.util.concurrent.*;

public class CallableRutrnDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Callable<Integer> email =()->{
            System.out.println("sending email.....");
            Thread.sleep(10000);
            return 200;
        };

        Future<Integer> future=executorService.submit(email);

        System.out.println("doing the work........");
        Thread.sleep(1000);
        Integer res=future.get();
        System.out.println("result = "+res);
        executorService.shutdown();

    }
}

