import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        try {
            System.out.println("🚗 Welcome to rehman dakait garage 🚗 ");

            //Customer details
            System.out.println("Enter customer name: ");
            String name=sc.nextLine();

            System.out.println("Enter car Number: ");
            String carNo=sc.nextLine();

            System.out.println("Enter car type (Hatchback/Sedan/SUV): ");
            String carType=sc.nextLine();

            Customer customer=new Customer(name,carNo,carType);

            //Available Service
            Service[] services={
                    new Service(1,"Car wash",300),
                    new Service(2,"Oil Change",400),
                    new Service(3,"Brake Check",150),
                    new Service(4,"Coolant Change",500),
                    new Service(5,"Full Service",10000)
            };

            boolean[] selected=new boolean[services.length];

            //Service selection
            while (true)
            {
                System.out.println("\n 🚗 Available Services:  ");
                for (Service s : services)
                {
                    System.out.println(s.getId()+". "+s.getName()+" - ₹"+s.getPrice());
                }

                System.out.println("0. Generate Bill");

                System.out.println("Select Services ID: ");
                int choice =sc.nextInt();


                if(choice<0 || choice> services.length)
                {
                    throw new Exception("Invalid service selection");
                }


                if (choice==0)
                {
                    int total=BillCalcultor.calculateTotal(services,selected);
                    //final billing
                    System.out.println("\n--------------Final Bill---------------");
                    System.out.println("Customer: "+customer.getName());
                    System.out.println("Car No: "+customer.getCarNumber());
                    System.out.println("Car type: "+customer.getCarType());
                    System.out.println("\nServices Availed:");

                    for(int i=0;i< selected.length;i++)
                    {
                        if(selected[i])
                        {
                            System.out.println("- "+services[i].getName()+": ₹"+services[i].getPrice());
                        }
                    }

                    System.out.println("---------------------------------------");
                    System.out.println("Total Amount: ₹"+total);
                    System.out.println("Thank you! Visit Again 🚗");

                    break;
                }

                System.out.println("Service Added: "+ services[choice-1].getName());
                selected[choice-1]=true;

            }

        }
        catch (Exception e)
        {
            System.out.printf("Error: "+e.getMessage());
        }
    }
}
