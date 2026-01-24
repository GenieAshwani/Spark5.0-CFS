package ExecutorsServiceDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SalarayService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<String> salaryTask = ()->{
            System.out.println("calculating salary by "+Thread.currentThread().getName());
            Thread.sleep(1000);
            return "45000.0";
        };

        Callable<String> email = ()->{
            System.out.println("sending email by"+Thread.currentThread().getName());
            Thread.sleep(1000);
            return "done";
        };

        List<Callable<String>> tasks= Arrays.asList(salaryTask,email);
        List<Future<String>> restults=executor.invokeAll(tasks);

        System.out.println("Hr doing rangoli work.....");

        //Double sal=submit.get();

        System.out.println("------------------final results-----------------");
        for (Future<String> future:restults)
        {
            System.out.println(future.get());
        }

       // System.out.println("final salary = "+sal);
        executor.shutdown();
    }
}
