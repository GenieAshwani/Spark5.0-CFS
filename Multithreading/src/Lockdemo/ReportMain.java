package Lockdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportMain {
    public static void main(String[] args) {
        ReportService service= new ReportService();
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for (int i=0;i<3;i++)
        {
            executorService.submit(()-> {
                try {
                    service.generateReport();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
    }
}
