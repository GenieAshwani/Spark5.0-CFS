package problem1;

public class Test {
    public static void main(String[] args) throws IllegalAccessException {
        User2 u1=new User2("Abhay",20);
        Validator.validate(u1);


        User2 u2=new User2("Abhay",10);
        Validator.validate(u2);
    }
}
