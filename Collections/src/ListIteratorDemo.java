import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String[] args) {

        List<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println(list);
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext())
        {
            Integer data = listIterator.next();
            if(data==20)
                listIterator.remove();
            if(data==30)
            {
                listIterator.add(80);
                System.out.println(listIterator.nextIndex());
            }

        }
        System.out.println(list);
    }
}
