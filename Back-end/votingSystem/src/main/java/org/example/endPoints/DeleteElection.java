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

@WebServlet("/admin/delete/*")
public class DeleteElection extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{
        String pathInform = request.getPathInfo();

        String deleteTable = pathInform.substring(1);
        ElectionDAO electionDAO = new ElectionDAO();

        try {
            electionDAO.deleteElection(deleteTable);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
