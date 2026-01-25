import java.util.ArrayList;
import java.util.Iterator;

public class FailFastDemo {
    public static void main(String[] args) {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(10);
        al.add(20);
        al.add(30);
        al.add(40);

        Iterator<Integer> iterator = al.iterator();
        while (iterator.hasNext())
        {
            Integer data = iterator.next();
            System.out.println(data);
            al.add(90);
        }
    }
}
