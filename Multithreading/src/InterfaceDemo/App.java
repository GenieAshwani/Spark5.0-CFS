package InterfaceDemo;

public class App {
    public static void main(String[] args) {
        Payment p=new CardPay();
        p.pay();
        System.out.println(Payment.x);
    }
}
