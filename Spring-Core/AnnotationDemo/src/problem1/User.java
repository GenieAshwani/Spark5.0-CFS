package problem1;

public class User {

    String name;
    int age;

    public User(String name,int age)
    {
        if(age<18)
        {
            throw new RuntimeException("Age must be 18+");
        }
        /*
            validation:
            age validation

            problem:
            constructor k andar hardcoded hai
            is class k bhar use nhi ho sakta
            tightly coupled
            same rule dushri class me likhna padega
         */
        this.name=name;
        this.age=age;
    }
    /*

     */
}
