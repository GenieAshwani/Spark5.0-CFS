package Communication;

import javax.swing.plaf.TableHeaderUI;


//hoter jhoda
//hoter sitara
class WeddingHall
{
    boolean dinnerReady = false;
}

class Guest extends Thread
{
    WeddingHall hall;
    Guest(WeddingHall h)
    {
        hall=h;
    }

    @Override
    public void run() {
        synchronized (hall)
        {
            System.out.println("Guest enter in the hall....");
            while (!hall.dinnerReady)  //false---
            {
                System.out.println("Dinner not ready. Guest Please wait....");
                try {
                    hall.wait(); //release lock
                    System.out.println("getting lock back....");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("Guest is having dinner....");
        }
    }
}

class Chef extends Thread
{
    WeddingHall hall;
    Chef(WeddingHall h)
    {
        hall=h;
    }

    @Override
    public void run() {
        synchronized (hall)
        {
            System.out.println("Chef started preparing dinner....");
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {

            }

            hall.dinnerReady=true;
            System.out.println("Chef: Dinner is ready");
            hall.notify();

        }
    }
}



public class CommDemo {
    public static void main(String[] args) {
        WeddingHall hall=new WeddingHall();
        Guest guest=new Guest(hall);
        Chef chef=new Chef(hall);

        guest.start();
        chef.start();
    }
}
