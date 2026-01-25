package Generics;

import java.util.Map;


class Pair<K,V>
{
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    void display()
    {
        System.out.println("key => "+key +"  value=> "+value);
    }
}

public class Main {

    public static void main(String[] args) {
        Gift<Integer> intGift=new Gift<>();
        intGift.setValue(10);

        System.out.println(intGift.getValue());

        Gift<String> stringGift=new Gift<>();
        stringGift.setValue("hello");
        System.out.println(stringGift.getValue());

        Pair<Integer,String> p1=new Pair<>(1,"one");
        Pair<String,Double> p2=new Pair<>("p2",3039.34);

        p1.display();
        p2.display();

    }
}
