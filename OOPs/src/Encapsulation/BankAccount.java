package Encapsulation;

public class BankAccount {

    private int accountNumber;   // controlling access
    private double bal;

    BankAccount(int accountNumber,int bal)  {
       /* if(accountNumber<=0) {
            throw new IllegalAccessException("Invalid account number");
        }
        if(bal<0)
        {
            throw new IllegalAccessException("Negative balance");
        }*/

        //validation
        this.accountNumber=accountNumber;
        this.bal=bal;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }
}
