import java.sql.*;
import java.util.Collection;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) {
        try {

            Connection connection = DriverManager.getConnection(DBConfigStd.url,
                    DBConfigStd.username, DBConfigStd.password);

            Scanner sc=new Scanner(System.in);

            while (true)
            {
                System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ====");
                System.out.println("1. Insert Student");
                System.out.println("2. Update Marks");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");

                int choice=sc.nextInt();
                switch (choice)
                {
                    case 1:
                        System.out.println("Enter the roll no:");
                        int rollNo=sc.nextInt();
                        System.out.println("Enter name:");
                        String name=sc.next();
                        System.out.println("Enter the marks");
                        double marks= sc.nextDouble();

                        //Query: insert into student values (roll, name, marks)
                        PreparedStatement ps = connection.prepareStatement("insert into student values(?,?,?)");
                        ps.setInt(1,rollNo);
                        ps.setString(2,name);
                        ps.setDouble(3,marks);

                        int rows=ps.executeUpdate();
                        System.out.println(rows+ " record inserted.");
                        break;
                    case 2: //update
                        System.out.println("Enter roll no to update");
                        int roll=sc.nextInt();
                        System.out.println("Enter New Marks ");
                        double newMarks=sc.nextDouble();

                        PreparedStatement ps1 = connection.prepareStatement("update student set marks=? where rollno=?");
                        ps1.setDouble(1,newMarks);
                        ps1.setInt(2,roll);

                        int updated=ps1.executeUpdate();
                        if(updated>0)
                        {
                            System.out.println("Record updated.");
                        }
                        else {
                            System.out.println("Roll number not found.");
                        }
                        break;
                    case 3: //Delete
                        System.out.println("Enter the roll num to delete:");
                        int rn=sc.nextInt();
                        PreparedStatement ps2 = connection.prepareStatement
                                ("delete from student where rollno=?");
                        ps2.setInt(1,rn);
                        int u=ps2.executeUpdate();
                        if(u>0)
                        {
                            System.out.println("Record deleted.");
                        }
                        else {
                            System.out.println("Roll number not found.");
                        }
                        break;

                    case 4: //Search
                        System.out.println("Enter the roll to search:");
                        int rSearch=sc.nextInt();
                        PreparedStatement ps3 = connection.prepareStatement
                                ("select * from student where rollno=?");
                        ps3.setInt(1,rSearch);

                        ResultSet rs = ps3.executeQuery();
                        if(rs.next())
                        {
                            System.out.println("Data found: "+
                                    rs.getInt(1)
                                    +" | "+rs.getString(2)+" | "+rs.getDouble(3));
                        }
                        else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting.....");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");

                }

            }
        }
        catch (SQLException e)
        {
            System.out.println("Database Error: "+e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
