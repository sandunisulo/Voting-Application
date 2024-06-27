package org.example.endPoints;


import org.example.DAO.VoterDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


@WebServlet("/admin/addUser")
public class AddUser extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        VoterDAO addUserDAO = new VoterDAO();
        try {
            addUserDAO.saveUser(userName,password,email);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
