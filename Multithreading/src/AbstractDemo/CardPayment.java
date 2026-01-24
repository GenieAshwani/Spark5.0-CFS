package AbstractDemo;

public class CardPayment extends Payment{

    @Override
    void pay() {
        System.out.println("Payment using card");
    }
}
