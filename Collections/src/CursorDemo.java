import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CursorDemo {
    public static void main(String[] args) {
        ArrayList<String> names= new ArrayList<>(Arrays.asList("harpreet","Shivam","deepika","pratham")) ;
        System.out.println(names);

        Iterator<String> it = names.iterator();
        while (it.hasNext())
        {
            String data = it.next();
            if(data.equals("Shivam"))
            {
                //System.out.println("found");
                it.remove();
            }
            else {
                System.out.println(data);
            }
        }
        System.out.println(names);



    }
}
