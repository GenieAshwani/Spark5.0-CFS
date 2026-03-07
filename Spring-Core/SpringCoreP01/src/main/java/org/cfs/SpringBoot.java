package org.cfs;

public class SpringBoot implements Course{

    SpringBoot()
    {
        System.out.println("SpringBoot default constructor....");
    }

    @Override
    public void start() {
        System.out.println("SpringBoot journey started....");
    }
}
