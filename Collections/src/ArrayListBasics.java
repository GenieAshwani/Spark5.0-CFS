import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListBasics {
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println(list); //print
        System.out.println(list.get(0));  //0th index  O(1)
        System.out.println(list.size()); //size

        ArrayList<String> names=new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        names.add("D");

        System.out.println(names);
        names.add(2,"E");  //O(n)
        System.out.println(names);

        names.remove(2);
        System.out.println(names);

        String[] str={"A","B","C","D"};
        List<String> list1=new ArrayList<>(Arrays.asList(str));
        System.out.println(list1);

        //travers
        for(String s:str)
        {
            System.out.println(s);
        }
        for(int i=0;i<list1.size();i++)
        {
            System.out.println(list1.get(i));
        }

        //sorting
        ArrayList<Integer> list3=new ArrayList<>();
        list3.add(10);
        list3.add(30);
        list3.add(20);
        list3.add(2);


        Collections.sort(list3);
        System.out.println(list3);

        Collections.synchronizedList(list);


    }
}
