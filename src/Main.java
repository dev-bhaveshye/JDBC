import java.sql.*;
public class Main {
    private static String url="jdbc:mysql://localhost:3306/JDBC_PRACTICE" ;

    private static String username="root";

    private static String password= "Bhaveshsql@1";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
//        Read Data
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String sql="select * from students";
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                String grade=resultSet.getString("grade");
                String email=resultSet.getString("email");
                System.out.println("id "+ " name " +"age  " +"grade " +"email  ");
                System.out.println(id+" "+name+" "+age+"   "+grade+" "+email);
            }

//          Insert Data
            String sql1=String.format("INSERT INTO students(id,name,age,grade,email) VALUES(%d,'%s','%d','%s','%s')",002,"Yash",21,"2nd","yashhatwar@gmail.com");
            int result=statement.executeUpdate(sql1);
            if(result>0){
                System.out.println("insert success");
            }else{
                System.out.println("insert failed");
            }

//          Updata Data
            String sql2=String.format("UPDATE students SET grade = '%s' WHERE id = %d ","3rd",002);
            int result2=statement.executeUpdate(sql2);
            if(result2>0){
                System.out.println("updated success");
            }else{
                System.out.println("updation failed");
            }

//            Delete Data

            String sql3=String.format("DELETE FROM students WHERE id = %d ",002);
            int result3=statement.executeUpdate(sql3);
            if(result3>0){
                System.out.println("deleted success");
            }else{
                System.out.println("deletion failed");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}