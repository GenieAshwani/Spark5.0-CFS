import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "root"
            );
            Statement stmt = con.createStatement();
            String sql="INSERT INTO employee VALUES(4,'Arjun')";
            int res = stmt.executeUpdate(sql);

            System.out.println("Success! Row inserted: "+res);
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
