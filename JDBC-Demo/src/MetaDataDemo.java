import java.sql.*;

public class MetaDataDemo {


    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "root"
            );

            //===================================================
            //part-1 DatabaseMetaData (info about the software)
            //===================================================
            DatabaseMetaData metaDb = con.getMetaData();
            System.out.println("=== Database info ====");
            System.out.println("Driver name:  "+metaDb.getDriverName());
            System.out.println("Driver version:  "+metaDb.getDriverVersion());
            System.out.println("Logged in user:  "+metaDb.getUserName());
            System.out.println("Database URL:  "+metaDb.getURL());

            //===================================================
            //part-2 ResultMetaData (info about the software)
            //===================================================
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from employee");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("==== Table (ResultSet) Info ====");

            //1. How many columns are in this table ?
            int totalColumn = rsmd.getColumnCount();
            System.out.println("Total column: "+totalColumn);

            //2. What are the names and types of columns ?
            System.out.println("--------------------------------------------");
            System.out.println("Col Index  | Column Name  | Column Type");
            System.out.println("--------------------------------------------");

            //loop from 1 to total column
            for (int i=1;i<=totalColumn;i++)
            {
                System.out.println(
                        "  "+i+"    |  "+rsmd.getColumnName(i)+" |  "+
                                rsmd.getColumnTypeName(i)
                );
            }
            System.out.println("----------------------------------------------");
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
