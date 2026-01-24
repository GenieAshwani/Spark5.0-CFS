package SuperThis;
 class Parent {
    String name="Ashwani";
    int data;

    Parent()
    {
        System.out.println("Parent constructor");
       // throw new RuntimeException("not making object....");
    }
    void details()
    {
       System.out.println(name.toUpperCase());
    }

     void details(String name)
     {
         System.out.println(name.toUpperCase());
     }

     void details(String name, String add)
     {
         System.out.println(name.toUpperCase()+"  address "+add);
     }

     void printName()
     {
         System.out.println("hello ashwani");
     }
}

public class child extends Parent{
    String name;

    child()
    {
        super();
        System.out.println("child constructor");
    }

    child(String name)
    {
        this(); //always pointing to default cons
        System.out.println("child constructor");
    }

    public void details()
    {
        super.name="Parent";
        this.name="Child";
        System.out.println(super.name +" and  "+name);
        super.details();
    }
    public void hello()
    {
        System.out.println("Hello ji");
    }

    static void main(String[] args) {
        child c=new child();
        c.details();
        System.out.println(c.data);

        System.out.println("-----------------------------");
        Parent p=new Parent();
        p.details();


        child c1=new child();
        c1.details("Ashwani");


    }
}
