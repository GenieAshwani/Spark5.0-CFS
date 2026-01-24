public class Demo {


    static int s=initStatic();   //static variable

    int x=initInstance(); //instance variable

    static void main(String[] args) {
        System.out.println("Main Start...");
        staticMethod();

        Demo d=new Demo();
        d.normalMethod();

        System.out.println("Main end....");
    }

    static void staticMethod()
    {
        System.out.println("staticMethod()");
    }

    public void normalMethod()
    {
        System.out.println("normalMethod()");
    }

    int initInstance()
    {
        System.out.println("initInstance()");
        return 30;
    }

    static int initStatic()
    {
        System.out.println("initStatic()");
        return 10;
    }


}
