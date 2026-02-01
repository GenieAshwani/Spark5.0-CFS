import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "root"
            );
            Statement stmt = con.createStatement();
            String sql="DELETE FROM employee WHERE empid=4";
            int res = stmt.executeUpdate(sql);

            if(res>0)
            {
                System.out.println("Delete successful!");
            }
            else {
                System.out.println("Update Failed");
            }

            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
