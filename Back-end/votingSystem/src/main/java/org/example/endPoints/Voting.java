package org.example.endPoints;
import org.example.DAO.ElectionDAO;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.Servlet;
@WebServlet("/user/voting")
public class Voting extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{
        String electionName = request.getParameter("electionName");
        String vote = request.getParameter("vote");


        ElectionDAO electionDAO = new ElectionDAO();
        try {
            electionDAO.giveVote(electionName,vote);
        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
