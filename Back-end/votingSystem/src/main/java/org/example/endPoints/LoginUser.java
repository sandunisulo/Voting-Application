package org.example.endPoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.DAO.VoterDAO;

@WebServlet("/user/login")
public class LoginUser extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();

        StringBuilder inPut = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            inPut.append(line);
        }

        reader.close();
        Gson gson = new Gson();
        JsonObject jsonData = gson.fromJson(inPut.toString(),JsonObject.class);


        String userName = jsonData.get("userName").getAsString();
        String password = jsonData.get("password").getAsString();

        VoterDAO voter = new VoterDAO();
        try {
            voter.userLogin(userName,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
