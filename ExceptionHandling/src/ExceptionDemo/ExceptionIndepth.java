package ExceptionDemo;

public class ExceptionIndepth {
    static void main(String[] args) {

        showRoom();
    }

    public static void showRoom()
    {
        System.out.println("let see car");
        testDrive();
    }

    public static void testDrive()
    {
        System.out.println("test drive");

        try {
            paperWord();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
        priceDiscount();

    }

    public static void paperWord()
    {
        System.out.println("submit all papers");

        done();
    }

    public static void done()
    {

            System.out.println(10/0); //exception

        System.out.println("payment done....");
    }

    public static void priceDiscount()
    {
        System.out.println("bhai thoda theek thaak laga le");
    }
}

