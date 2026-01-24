package Inheritance;

public class Test {
    static void main(String[] args) {
        Raju r=new Raju();

        r.print();
        r.daimond();

        Hariya h=new Hariya();
        //h.daimond();

        Priya p=new Priya();
        p.daimond(); //from parent class
        //single level inheritance
        p.plot();  // from grand-parent class
        // multi level inheritance


       /* Thomas t=new Thomas();
        t.house();*/



   }
}
