public class Car {


    Engine engine=new Engine();
    void run()
    {
        System.out.println("going to run car.....");
        int res = engine.start();
        if(res>0) System.out.println("Engine start....");
        else System.out.println("Engine not started");
    }
}
