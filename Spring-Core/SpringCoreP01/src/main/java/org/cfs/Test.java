package org.cfs;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");


        //getting Student object
        Student std=context.getBean(Student.class);
        Student std1=context.getBean(Student.class);
        std.learning();
        System.out.println(std);
        System.out.println(std1);

    }
}
