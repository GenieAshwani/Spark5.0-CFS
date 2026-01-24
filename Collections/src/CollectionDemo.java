import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionDemo {
    public static void main(String[] args) {
        //duplicate
        //order
        List<Integer> list=new ArrayList<>(); //default array

        List<Integer> list3=new ArrayList<>(list); //creating arraylist from other collection

        List<Integer> list1=new ArrayList<>(20);  //initial capcity

        int arr[]={1,2,3,4,5};
        System.out.println(arr[3]);  //O(1) --> in

        ArrayList arrayList=new ArrayList();
        arrayList.add(10);
        arrayList.add(20);

        Object clone = arrayList.clone();

        System.out.println(clone);

        //for adding data
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(10);
        list.add(20);



        System.out.println(list);
        System.out.println(list.get(3));  //indexing


    }
}
