import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class InsetDemo {
    public static void main(String[] args) {
        try {

            Connection con= DriverManager.getConnection(DBConfig.url,DBConfig.username,DBConfig.password);
            Scanner sc= new Scanner(System.in);

            System.out.println("Enter id :");
            int id=sc.nextInt();

            System.out.println("Enter name : ");
            String name=sc.next();

            System.out.println("Enter salary: ");
            double sal=sc.nextDouble();

            String query="insert into employee values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setDouble(3,sal);

            int res = ps.executeUpdate();
            System.out.println(res+" row inserted successfully!");

            con.close();


            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
