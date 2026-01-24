public class InstanceDemo {

    static void main(String[] args) {
        Employee e1=new Employee();
        e1.displayEmp();

        Employee e2=new Employee();
        e2.displayEmp();
        e2.id=2;
        e2.name="Hariya";
        System.out.println("----------------------------------");
        e2.displayEmp();

        e1.displayEmp();
    }
}
