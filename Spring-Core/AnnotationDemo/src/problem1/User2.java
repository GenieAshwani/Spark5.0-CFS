package problem1;

public class User2 {


    String name;


    @MinAge(18)
    private int age;

    public User2(String name, int age)
    {
        this.name=name;
        this.age=age;
    }

}
