import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        Queue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(10);
        pq.add(20);
        pq.add(220);
        pq.add(2);

        //smaller value ----> highest priority

        System.out.println(pq.poll());
        System.out.println(pq.poll());
    }
}
