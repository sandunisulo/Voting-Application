package org.example.endPoints;
import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.Servlet;
@WebServlet("/user/voting")
public class Voting extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse responseToFront) throws ServletException,IOException{
        String ovte = request.getParameter("vote");
    }
}
