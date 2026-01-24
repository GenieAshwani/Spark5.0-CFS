package privateDemo;


class Student {
    private int pin=132;

    private void showPin()
    {
        System.out.println("Pin is : "+pin);
    }

    void verify()
    {
        showPin();
    }
    private class College{

    }
}

public class Test{
    static void main(String[] args) {
        Student s=new Student();

        //System.out.println(s.pin);  //error: pin is private
        //s.showPin();
        s.verify(); //
    }
}


