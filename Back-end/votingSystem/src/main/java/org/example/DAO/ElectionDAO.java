package org.example.DAO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;


public class ElectionDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/elections";
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
    public void addElection() throws ClassNotFoundException, SQLException {
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
                    for(int i=0;i<len;i++){
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
            }finally {
                if(connection!=null){
                    connection.close();
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(preparedStatement1!=null){
                    preparedStatement1.close();
                }
            }
        }else{
            System.out.println("options not given cannot add election");
        }

    }

    //Delete Election
    public void deleteElection(String deleteTable) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        String deleteQ = "DROP TABLE "+ deleteTable;
        System.out.println(deleteQ);
        try{
            connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
            preparedStatement = connection.prepareStatement(deleteQ);
             boolean delete = preparedStatement.execute();

             if(!delete){
                 System.out.println("End election successfully");
             }else {
                 System.out.println("Error while end election!");
             }

        }catch (SQLException e){
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }
    }

    //voting
    public void giveVote(String electionName,String voteFor) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        String vote = "select count from " + electionName + " where options = \'" + voteFor + "\'";
        ResultSet getCount;
        int newCount = 0;
        try {

            connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
            getCount = connection.createStatement().executeQuery(vote);
            if(getCount.next()){
                newCount = getCount.getInt("count");
            }

            newCount += 1;
            String newVote = "update " + electionName +" set count = " + newCount +  " where options = \'" + voteFor + "\'";

            preparedStatement = connection.prepareStatement(newVote);
            boolean upDate = preparedStatement.execute();

            if(!upDate){
                System.out.println("Add vote for election successfully");
            }else {
                System.out.println("Error while adding vote election!");
            }

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            if(connection!= null){
                connection.close();
            }
        }
    }

    //Show all elections
    public String showElections() throws ClassNotFoundException {
        String allElections = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try{
            String sql = "show tables";
            connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
            preparedStatement = connection.prepareStatement(sql);

            ResultSet saveData;
            saveData = connection.createStatement().executeQuery(sql);

            if(saveData.next()){
                allElections = saveData.getString(1);
            }

        } catch (SQLException e) {
            allElections = " ";
            throw new RuntimeException(e);
        }

        return allElections;
    }
}
