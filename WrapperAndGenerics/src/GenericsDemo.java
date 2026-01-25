import java.util.ArrayList;

public class GenericsDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<Integer>();

        //now we can only store Integer
        //homo +hetro ==>> collection
        ArrayList list1=new ArrayList();

        list1.add(10000);
        list1.add("ravi");
        list1.add(true);
        System.out.println(list1);

        for(Object obj:list1)
        {
            if(obj instanceof Integer)
            {
                Integer i=(Integer) obj;
                System.out.println(i);
            }
            if(obj instanceof String)
            {
                String  i=(String) obj;
                System.out.println(i);
            }
            if(obj instanceof Boolean)
            {
                Boolean  i=(Boolean) obj;
                System.out.println(i);
            }

        }



    }
}
