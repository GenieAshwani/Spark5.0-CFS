package org.cfs;

public class DSA implements Course{

    DSA()
    {
        System.out.println("DSA default constructor....");
    }

    @Override
    public void start() {
        System.out.println("DSA journey started....");
    }
}
