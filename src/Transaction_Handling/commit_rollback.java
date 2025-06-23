package Transaction_Handling;
import java.sql.*;
import java.util.Scanner;

public class commit_rollback {
    private static String url="jdbc:mysql://localhost:3306/transaction" ;

    private static String username="root";

    private static String password= "Bhaveshsql@1";

    public static void main(String[] args) throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{

                Connection connection =DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
                String debit = "UPDATE accounts SET balance = balance - ? WHERE account_number =?" ;
                String credit = "UPDATE accounts SET balance = balance + ? WHERE account_number =?" ;
                PreparedStatement debit_prepared_statement = connection.prepareStatement(debit);
                PreparedStatement credit_prepared_statement = connection.prepareStatement(credit);
                System.out.println("enter Account Number to Debit ");
                Scanner sc = new Scanner(System.in);
                int debit_account_number = sc.nextInt();
                System.out.println("enter Account Number to Credit ");
                int credit_account_number = sc.nextInt();
                System.out.println("enter amount to debit");
                Double amount = sc.nextDouble();
                debit_prepared_statement.setDouble(1,amount);
                debit_prepared_statement.setInt(2,debit_account_number);
                credit_prepared_statement.setDouble(1,amount);
                credit_prepared_statement.setInt(2,credit_account_number);
            if (isSufficient(connection, debit_account_number, amount)) {
                debit_prepared_statement.executeUpdate();
                credit_prepared_statement.executeUpdate();
                connection.commit();
                System.out.println("Transaction Successful");
            } else {
                connection.rollback();
                System.out.println("Transaction Failed!!!");
            }





        }catch( SQLException e){
            System.out.println(e.getMessage());

        }


    }
    static Boolean isSufficient(Connection connection , int account_number , Double amount) throws SQLException {
        try{
            String query = "select * from accounts where account_number = ?";
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            prepared_statement.setInt(1,account_number);
            ResultSet resultSet = prepared_statement.executeQuery();
            if(resultSet.next()){
                Double current_balance = resultSet.getDouble("balance");
                if(current_balance < amount){
                    return false;
                }else{
                    return true;
                }
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;

    }
}