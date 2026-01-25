import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class Employee {
    int id;
    String name;
    int sal;

    public Employee(int id, String name, int sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }


}

class MyComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        return o2.id-o1.id;
    }
}

public class ComparatorDemo {

    public static void main(String[] args) {
        Map<Employee,String> map=new TreeMap<>();
        map.put(new Employee(1,"Raj",10001),"RAJ");
        map.put(new Employee(4,"Amit",34344),"Amit");
        map.put(new Employee(2,"Ajay",232323),"Ajay");
        map.put(new Employee(6,"Hari",390090),"Hari");

        System.out.println(map);
    }
}
