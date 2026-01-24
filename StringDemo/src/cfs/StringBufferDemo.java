package cfs;

public class StringBufferDemo {

    static void main(String[] args) {
        StringBuffer sb=new StringBuffer("Ashwani");
        sb.append(" cfs");

        System.out.println(sb);

        StringBuilder builder=new StringBuilder("cfs");
        builder.append(" code for success");
        System.out.printf(builder.toString());
    }
}
