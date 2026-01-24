package cfs;

import genie.StringMethods;

public class MethodDemo {
    static void main(String[] args) {

        //army area
        //display();
        //cfs.MethodDemo.display();
        print();

 /*     cfs.MethodDemo m=new cfs.MethodDemo();
      m.display();*/

        Utility m=new Utility();

        int a=10;
        int b=20;
        System.out.println(a+b);

        int c=20;
        int d=30;
        System.out.println(c+d);

        m.sum(a,b);
        m.sum(c,d);

       String res= m.stringAppend("Ashwani","Upadhayay"); // calling
        System.out.println(res);

        StringMethods stringMethods=new StringMethods();
        int sub = stringMethods.sub(10, 5);
        System.out.println(sub);


    }

    public static void print()
    {
        System.out.println("Code for success");
    }

}
