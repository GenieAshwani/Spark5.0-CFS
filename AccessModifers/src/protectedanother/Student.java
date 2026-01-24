package protectedanother;

import protecteddemo.College;

public class Student extends College {

    void access()
    {
        System.out.println(annoucement);
        rules();
    }


    static void main(String[] args) {

        new Student().access();
    }
}
