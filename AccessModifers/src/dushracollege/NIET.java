package dushracollege;

//import defaultDemo.CollegeOffice;

import publicdemo.CollegeWebSite;

public class NIET {
    static void main(String[] args) {
        //CollegeOffice o=new CollegeOffice(); // cannot access because class is default
        //System.out.println(o.internalNotice); // cannot access

        CollegeWebSite webSite=new CollegeWebSite();
        System.out.println(webSite.notice);
    }
}
