public class ConsTest {
    static void main(String[] args) {
        Employee e1=new Employee();
        System.out.println(e1.id);
        e1.setId(1);
        System.out.println(e1.id);

        Employee e2=new Employee(2);
        System.out.println(e2.id);

        Employee e3=new Employee(3,"Arjun");
        System.out.println(e3.id);
        System.out.println(e3.name);


    }
}
