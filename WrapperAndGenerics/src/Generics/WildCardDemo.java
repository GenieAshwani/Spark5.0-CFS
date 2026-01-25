package Generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardDemo {
    public static void main(String[] args) {

        //List<Number> list=new ArrayList<Integer>();

        display(List.of(1,2,3,4));
        display(List.of(1.4,2.5,3.4,4.6));

        List<Number> nums=new ArrayList<>();
        print(nums);

    }

    static void display(List<? extends Number> list)
    {
        System.out.println(list);
    }

    static void print(List<? super Integer> list)
    {
        list.add(100);
        list.add(20);
        System.out.println(list);
    }
}
