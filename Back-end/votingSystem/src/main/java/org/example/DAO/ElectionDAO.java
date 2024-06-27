package org.example.DAO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ElectionDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/voters";
    private String jdbcUserName = "root";
    private String jdbcPassword = "1234";
    String electionName;
    String options;
    int count;

    public ElectionDAO(){

    }

    public ElectionDAO(String electionName,String options,int count){
        this.electionName = electionName;
        this.options = options;
        this.count = count;
    }

    //add election function
    public void addElection() throws ClassNotFoundException {
        if(options != null && !options.isEmpty()){
            List<String> optionsRows = Arrays.asList(options.split(","));

            int len = optionsRows.size();
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            PreparedStatement preparedStatement1 = null;

            String createTable = "CREATE TABLE "+electionName+"(options VARCHAR(20) PRIMARY KEY,count INT)";


            try{
                connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);

                preparedStatement = connection.prepareStatement(createTable);

                boolean tableAdd = preparedStatement.execute();
                if(!tableAdd){
                    for(int i=0;i<=len;i++){
                        String addData = "INSERT INTO "+electionName+"(options,count) VALUES(\""+ optionsRows.get(i) +"\","+count+")";
                        preparedStatement1 = connection.prepareStatement(addData);
                        int addRow = preparedStatement1.executeUpdate();
                        if(addRow>0){
                            System.out.println("Adding "+i+" is success");
                        }else{
                            System.out.println("Adding "+i+" is not success");
                        }
                    }
                }

            }catch(SQLException e){
                System.out.println(e);
            }
        }else{
            System.out.println("options not given cannot add election");
        }

    }

    //Delete Election
    public void deleteElection(){

    }

}
