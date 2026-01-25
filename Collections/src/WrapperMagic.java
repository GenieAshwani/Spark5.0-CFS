import java.util.ArrayList;
import java.util.List;

public class WrapperMagic {
    public static void main(String[] args) {

        List<Number> list=new ArrayList<>();
        list.add(20);
        list.add(30.4);


        Integer a=10;

        a=20; //immutable


        System.out.println(list);
    }
}
