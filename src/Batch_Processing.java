import java.sql.*;
import java.util.Scanner;

public class Batch_Processing {
    private static String url="jdbc:mysql://localhost:3306/JDBC_PRACTICE" ;

    private static String username="root";

    private static String password= "Bhaveshsql@1";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
        String query = "INSERT INTO students(id, name, age) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner sc=new Scanner(System.in);
            while(true){
                System.out.println("Enter the ID: ");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the name: ");
                String name =sc.nextLine();
                System.out.println("Enter the age: ");
                int age=sc.nextInt();
                sc.nextLine();
                System.out.print("do you want to enter next students data (Y/N)");
                String choice=sc.next();

                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,name);
                preparedStatement.setInt(3,age);
                preparedStatement.addBatch();
                if(choice.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] arr =preparedStatement.executeBatch();
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
