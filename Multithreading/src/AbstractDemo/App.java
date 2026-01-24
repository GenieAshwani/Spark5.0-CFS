package AbstractDemo;

public class App {
    public static void main(String[] args) {
      //Payment p=new Payment();



        Payment p=new CardPayment();
        p.invoice();
        p.pay();
    }
}
