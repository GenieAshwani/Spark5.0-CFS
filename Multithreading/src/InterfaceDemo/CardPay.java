package InterfaceDemo;

public class CardPay implements Payment{
    @Override
    public void pay() {
        System.out.println("Card payment...");
    }
}
