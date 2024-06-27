package org.example.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

public class VoterDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/voters";
    private String jdbcUserName = "root";
    private String jdbcPassword = "1234";

    String userName;
    String password;
    String email;

    public VoterDAO(){

    }

    public VoterDAO(String userName,String password,String email){
        this.userName =userName;
        this.password = password;
        this.email= email;
    }

    public void saveUser(String userName,String password,String email) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection2 = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String checkSQL = "SELECT * FROM voter WHERE userName = ?";
        String insertSQL = "INSERT INTO voter(userName,password,email)VALUES(?,?,?)";

        try{
            connection2 = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);

            preparedStatement1 = connection2.prepareStatement(checkSQL);
            preparedStatement1.setString(1,userName);

            ResultSet result = preparedStatement1.executeQuery();
            if(!result.isBeforeFirst()){
                preparedStatement2 = connection2.prepareStatement(insertSQL);

                preparedStatement2.setString(1,userName);
                preparedStatement2.setString(2,password);
                preparedStatement2.setString(3,email);

                int rowInsert = preparedStatement2.executeUpdate();
                if(rowInsert>0){
                    System.out.println("User insert successfully");
                }
            }else{
                System.out.println("User already taken");
            }

        }catch(SQLException e){
            System.out.println(e);
        }finally {
            {
                try {
                    {
                        if(preparedStatement1!=null){
                            preparedStatement1.close();
                        }
                        if(preparedStatement2!=null){
                            preparedStatement2.close();
                        }
                        if(connection2!=null){
                            connection2.close();
                        }

                    }
                }catch (SQLException e){
                    System.out.println(e);
                }
            }
        }
    }
}

