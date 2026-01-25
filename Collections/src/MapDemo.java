import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();

        map.put(101,"Amit");
        map.put(102,"Rahul");
        map.put(103,"Ashwani"); //complexity O(1)

        //why insertion complexity O(1)

        String value = map.get(101);
        System.out.println(value);
        map.put(null,"null");
        map.put(null,"Raja");

        System.out.println(map);
        if(map.containsKey(101))
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("No");
        }

        String orDefault = map.getOrDefault(101, "NO");
        System.out.println(orDefault);

        for(Map.Entry<Integer,String> entry: map.entrySet())
        {
            System.out.println(entry.getKey() + " --> "+entry.getValue());
        }
    }
}
