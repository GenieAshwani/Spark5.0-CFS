import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<Integer,String> map=new TreeMap<>();
        map.put(10,"C");
        map.put(5,"B");
        map.put(1,"K");
        map.put(4,"L");
       // map.put(null,"PP");
        System.out.println(map);
    }
}
