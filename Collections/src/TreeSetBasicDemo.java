import java.util.TreeSet;


class Student implements Comparable<Student>{
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        System.out.println("aa gaya...");
        System.out.println(this.id - o.id);
        return this.id - o.id;  //ascending order

        //0 == equals
        //1 == this.id > o.id  this.id wala object bad me aa jayega
        //-1 == this.id < o.id this.id wala object pahle aa jayega
    }
}

public class TreeSetBasicDemo {
    public static void main(String[] args) {
        TreeSet<Integer> set=new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(4440);
        set.add(0);
        set.add(40);
        set.add(440);

        System.out.println(set);

        TreeSet<Student> studentsSet=new TreeSet<>();
        Student s1=new Student(3,"rani");
        Student s2=new Student(2,"raja");
        Student s3=new Student(1,"baccha");


        studentsSet.add(s1);
        studentsSet.add(s2);
        studentsSet.add(s3);
    }
}
