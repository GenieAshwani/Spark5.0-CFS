package ExecutorsServiceDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationDemo {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);

        executorService.submit(()->readUserData());
        executorService.submit(()->readOrderdata());
        executorService.submit(()->callPaymentService());
        executorService.submit(()->callNotificationService());
        executorService.submit(()->readFromCache());
        executorService.submit(()->readFromFile());

        executorService.shutdown();

    }

    public static void readUserData()
    {
        System.out.println("Reading user data from db:");
        sleep(2000);
        System.out.println("user db read completed");
    }

    public static void readOrderdata()
    {
        System.out.println("Reading order data from db:");
        sleep(1500);
        System.out.println("order db read completed");
    }

    public static void callPaymentService()
    {
        System.out.println("calling Payment service");
        sleep(3000);
        System.out.println("Payment service response received");
    }
    public static void callNotificationService()
    {
        System.out.println("calling notification service");
        sleep(3000);
        System.out.println("notification service response received");
    }

    public static void readFromCache()
    {
        System.out.println("Reading data from cache");
        sleep(3000);
        System.out.println("cache read completed");
    }

    public static void readFromFile()
    {
        System.out.println("Reading data from file");
        sleep(3000);
        System.out.println("file read completed");
    }


    private static void sleep(int ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
