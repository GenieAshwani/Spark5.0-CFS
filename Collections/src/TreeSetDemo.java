import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        SortedSet<Integer> sortedSet=new TreeSet<>();
        sortedSet.add(10);
        sortedSet.add(30);
        sortedSet.add(1);
        sortedSet.add(200);
        sortedSet.add(20);

        System.out.println("sorted set : "+sortedSet);


        //
        System.out.println("first element : "+sortedSet.first());

        //headset: all element less then given element
        System.out.println(sortedSet.headSet(30)); //<
        System.out.println(sortedSet.tailSet(30)); //>=

        NavigableSet<Integer> navigableSet=new TreeSet<>();
        navigableSet.add(10);
        navigableSet.add(20);
        navigableSet.add(30);
        navigableSet.add(40);
        navigableSet.add(50);
        System.out.println("navigable set : "+navigableSet);

        int key=20;


        //nevigation methods
        System.out.println("Lower then : "+key+" : "+navigableSet.lower(key));
        System.out.println("floor of : "+key+" : "+navigableSet.floor(key));
        System.out.println("Higher then : "+key+" : "+navigableSet.higher(key));
        System.out.println("Ceiling of : "+key+" : "+navigableSet.ceiling(key));

        //polling
        System.out.println("Pool first : "+navigableSet.pollFirst());
        System.out.println("Pool last : "+navigableSet.pollLast());
        System.out.println(navigableSet);
    }
}
