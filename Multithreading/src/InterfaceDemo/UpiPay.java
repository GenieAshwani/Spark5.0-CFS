package InterfaceDemo;

public class UpiPay implements Payment{
    @Override
    public void pay() {
        System.out.println("payment using UPI....");
    }
}
