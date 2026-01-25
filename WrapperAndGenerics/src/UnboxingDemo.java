public class UnboxingDemo {
    public static void main(String[] args) {
        Integer i1=new Integer(100);

       int a=i1.intValue();
        System.out.println(a);

        Character c1=new Character('a');
        char c=c1.charValue();
        System.out.println(c);


        int i3=Integer.parseInt("10");
        System.out.println(i3);

        Integer i4=Integer.valueOf("100");
        System.out.println(i4);
        System.out.println(i4.toString());

        boolean b=Boolean.parseBoolean("ravi");
        System.out.println(b);


        int d=10;
        String s1=String.valueOf(d);
        System.out.println(s1);

        Integer jj=d;
        String s2=jj.toString();


    }
}
