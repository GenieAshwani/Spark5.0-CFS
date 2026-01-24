package Overriding;

import Inheritance.Hariya;

public class OverdingDemo extends Hariya {

    @Override
    public void gold() {
        System.out.println("200kg gold");
    }

    @Override
    public void plot() {
        System.out.println("1000gaj plot");
    }
}
