import java.sql.*;

public class prepared_statement {

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
            String query = "SELECT * FROM students";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                String grade=resultSet.getString("grade");
                String email=resultSet.getString("email");
                System.out.println("id "+ " name " +"age  " +"grade " +"email  ");
                System.out.println(id+" "+name+" "+age+" "+grade+ " "+email);
            }
            String query1="UPDATE students SET grade = ? WHERE name = ? ";
            PreparedStatement preparedStatement1=connection.prepareStatement(query1);
            preparedStatement1.setString(1,"2nd");
            preparedStatement1.setString(2,"Bhavesh");

            int result2= preparedStatement1.executeUpdate();
            if(result2>0){
                System.out.println("updated success");
            }else{
                System.out.println("updation failed");
            }


            String query3=String.format("DELETE FROM students WHERE name = ?");
            PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
            preparedStatement2.setString(1,"Bhavesh");
            int result3= preparedStatement2.executeUpdate();
            if(result3>0){
                System.out.println("deleted success");
            }else{
                System.out.println("deletion failed");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


//        Insert Data




    }
}
