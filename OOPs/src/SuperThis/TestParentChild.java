package SuperThis;

public class TestParentChild {
    static void main(String[] args) {

        MyChild c= new MyChild();
        c.m1();
        c.display();

        Object

        MyParent p=new MyParent();  //LHS = RHS
        p.m1();
        //p.display();

        MyParent c1=new MyChild(); //LHS != RHS
        c1.m1();

    }

}
