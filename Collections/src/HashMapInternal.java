import java.util.HashMap;
import java.util.Objects;

public class HashMapInternal {
    public static void main(String[] args) {

        HashMap<String,Integer> map=new HashMap<>();
        map.put("Raja",10);
        map.put("Rani",20);
        map.put("amit",40);

        Integer value = map.get("Raja");
        System.out.println(value);

        for(int i=1;i<50;i++)
        {
            System.out.println(i%16);
        }

    }
}
