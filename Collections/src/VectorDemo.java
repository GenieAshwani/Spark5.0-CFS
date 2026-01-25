import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<Integer> vector=new Vector<>(10);
        //ArrayList    10--->20
        System.out.println(vector.capacity());
        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
      //  System.out.println(vector);
        Enumeration<Integer> enumeration = vector.elements();
        while (enumeration.hasMoreElements())
        {
            Integer data = enumeration.nextElement();
            System.out.println(data);
        }

        System.out.println("-----------------------------------------");
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext())
        {
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
}
