package org.example.endPoints;

import org.example.DAO.ElectionDAO;

import javax.servlet.ServletException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/admin/addElection")
public class AddElection extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException, IOException {
        String electionName = request.getParameter("electionName");
        String options = request.getParameter("options");
        int count;

        ElectionDAO electionDAO = new ElectionDAO(electionName,options,0);
        try {
            electionDAO.addElection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
