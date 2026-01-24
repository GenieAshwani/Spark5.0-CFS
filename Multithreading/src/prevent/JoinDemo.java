package prevent;

class WashingMachine extends Thread
{
    @Override
    public void run() {
        try {
            System.out.println("Washing started....");
            Thread.sleep(3000); //just sleep for 3 sec
            System.out.println("Washing finished....");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        WashingMachine wash=new WashingMachine();
        wash.start();

        //wash.join();
        //wash.join(2000); //college friend
        wash.join(2000,100);
        System.out.println("Now start drying clothes....");
    }
}
