package withSycn;

class BankAccount
{
    int bal=10000;

    public void withdraw(int amount)
    {
        if(bal>=amount)
        {

            System.out.println(Thread.currentThread().getName()+" is going to withdraw ₹ "+amount+ " Total bal : "+bal);
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread InterruptedException ");
            }

            synchronized (this)
            {
                if(bal>=amount)
                {
                    bal=bal-amount;
                    System.out.println(Thread.currentThread().getName()+" completed withdraw: Remaining bal = ₹"+bal);
                }
                   //
            }

            //System.out.println(Thread.currentThread().getName()+" completed withdraw: Remaining bal = ₹"+bal);

        }
        else {
            System.out.println("Not enough balance for "+Thread.currentThread().getName());
        }
    }
}


class Person extends Thread
{
    BankAccount account;
    Person(String name,BankAccount account)
    {
        super(name);
        this.account=account;
    }

    @Override
    public void run() {
        account.withdraw(5000);
    }
}

public class WithSyncDemo {
    public static void main(String[] args) {
        BankAccount account=new BankAccount();

        Person pati = new Person("pati",account);   //t1
        Person patni = new Person("patni",account);  //t2
        Person child = new Person("child",account);  //t2

        pati.start();
        patni.start();
        child.start();
    }
}
