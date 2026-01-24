import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<Integer> set= new HashSet<>();

        set.add(10);
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(30);
        set.add(40);
        set.add(null);
        set.add(null);
        System.out.println(set);

        ArrayList<String> names=new ArrayList<>();
        names.add("ram");
        names.add(null);
        names.add(null);
        names.add(null);

        ArrayList<Integer> num=new ArrayList<>();
        num.add(10);
        num.add(null);
        num.add(null);
        System.out.println(num);

        System.out.println(names);


    }
}
