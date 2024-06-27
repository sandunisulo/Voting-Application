package org.example.endPoints;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.Servlet;

@WebServlet("/admin/delete")
public class DeleteElection extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{

    }
}
