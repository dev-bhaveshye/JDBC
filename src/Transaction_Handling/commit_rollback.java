package Transaction_Handling;
import java.sql.*;
import java.util.Scanner;

public class commit_rollback {
    private static String url="jdbc:mysql://localhost:3306/transactions" ;

    private static String username="root";

    private static String password= "Bhaveshsql@1";

    public static void main(String[] args) throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection =DriverManager.getConnection(url,username,password);


    }
}