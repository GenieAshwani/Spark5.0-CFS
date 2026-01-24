package cfs;

public class StringP01 {

    public static void main(String[] args) {
        String s="Hello1"; //scp
        String s1=new String("Hello"); //heap

        System.out.println(s==s1);
        System.out.println(s.equals(s1));

        System.out.println(String.valueOf(s.hashCode()));
        System.out.println(String.valueOf(s1.hashCode()));

        String s2="hello";
        String s3=s2.concat(" cfs");
        System.out.println(s2);
        System.out.println(s3);
    }
}
