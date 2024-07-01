package org.example.endPoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.DAO.VoterDAO;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/user/changePassword")
public class ChangePassword extends HttpServlet{
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
        String newPassword = jsonData.get("newPassword").getAsString();

        VoterDAO voterDAO = new VoterDAO();

        try {
            voterDAO.changePassword(userName,password,newPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
