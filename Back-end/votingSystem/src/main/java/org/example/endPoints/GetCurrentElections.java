package org.example.endPoints;

import org.example.DAO.ElectionDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/showAllElections")
public class GetCurrentElections extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{

        ElectionDAO electionDAO = new ElectionDAO();
        String s = null;
        try {
            s = electionDAO.showElections();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
    }
}
