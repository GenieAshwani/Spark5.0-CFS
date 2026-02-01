import java.sql.*;

public class Test {
    public static void main(String[] args) {
       //load driver
       //get connection
       //create statement
       //execute query
       //close conn


        try {
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //get connection
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "root"
            );

            //create statement
            Statement statement=connection.createStatement();

           ResultSet resultSet=statement.executeQuery("select * from employee");
            //boolean execute = statement.execute("select * from employee");
            while (resultSet.next())
            {
                System.out.println(resultSet.getInt("empid")
                        +"  "+resultSet.getString("empname"));
            }

            connection.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
