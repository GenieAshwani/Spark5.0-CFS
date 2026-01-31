package Generics;

import java.util.List;

public class UnboundDemo {
    public static void main(String[] args) {

        print(List.of(1,"java",true));
    }

    static void print(List<?> list)
    {
        System.out.println(list);
    }

    String s="abcd";

    char[] ch=s.toCharArray();
}
