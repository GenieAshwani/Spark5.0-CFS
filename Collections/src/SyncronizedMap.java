import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SyncronizedMap {
    public static void main(String[] args) {

        HashMap<String,Integer> map=new HashMap<>();
        map.put(null,10);
        map.put("raja",null);
        System.out.println(map);


       // Collections.synchronizedMap(map);

        ConcurrentMap<String,Integer> concurrentMap=new ConcurrentHashMap<>();
      //  concurrentMap.put(null,10);
        System.out.println(concurrentMap);

        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(10);
        copyOnWriteArrayList.add(20);
        copyOnWriteArrayList.add(30);
        copyOnWriteArrayList.add(40);
        copyOnWriteArrayList.add(50);

        System.out.println(copyOnWriteArrayList);
    }
}
