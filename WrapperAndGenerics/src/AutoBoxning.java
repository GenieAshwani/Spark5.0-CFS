public class AutoBoxning {

    public static void main(String[] args) {
        int a=100;
        Integer b=a; //autoboxing Integer.valueOf(a)

        Integer c=10;
        int d=c; //autoboxing

        System.out.println(d);
        System.out.println(b);

        Integer i=new Integer(10);
        Integer i2=new Integer("12");
        Character cc=new Character('1');

        Float f1=new Float(10.5f);
        Float f2=new Float("12.5f");
        Float f3=new Float(19.2);
        //String float, double

        //Boolean is having 2 consturtor
        Boolean b1=new Boolean(true);
        Boolean b2=new Boolean("TRue");
        Boolean b3=new Boolean("TRUe");
        Boolean b4=new Boolean("TRUr");
        Boolean b5=new Boolean("ravi");
        Boolean b6=new Boolean("yes");
        Boolean b7=new Boolean("no");

        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);
        System.out.println(b6);
        System.out.println(b7);


    }
}
