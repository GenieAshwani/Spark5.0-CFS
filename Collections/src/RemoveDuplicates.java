import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {

        ArrayList<Integer> list=new ArrayList<>(Arrays.asList(1,4,3,2,4,3,1));
        System.out.println(list);

        ArrayList<Integer> unique=new ArrayList<>();

        for(Integer data:list)
        {
            if(!unique.contains(data))
            {
                unique.add(data);
            }
        }
        System.out.println(unique);
    }
}
