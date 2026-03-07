
public class Student {

   //static Student student=new Student();
   static Student student;
   static public Student getInstance()
   {
      if(student==null)
      {
          student=new Student();
      }
      return student;
   }


}