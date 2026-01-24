import java.util.LinkedList;
import java.util.Queue;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> ll=new LinkedList<>();
        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        ll.add(50);

        //list.add(2,0)
        System.out.println(ll);

        ll.add(2,60);
        System.out.println(ll);
        ll.addLast(99);
        System.out.println(ll);

        Queue<Integer> queue=new LinkedList<>();

        //frequent fetching of data ---> AL
        //frequent insertion/deletion --> LL
    }
}
