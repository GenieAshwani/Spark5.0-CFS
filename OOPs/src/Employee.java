public class Employee {
    int id;
    String name;
    String dept;

    //default const
    //with no parameter

    Employee()
    {
        System.out.println("Constructor.....");
    }

    Employee(int id)
    {
        this.id=id;
    }
    Employee(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

    Employee(int id,String name,String dept)
    {
        this.id=id;
        this.name=name;
        this.dept=dept;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
