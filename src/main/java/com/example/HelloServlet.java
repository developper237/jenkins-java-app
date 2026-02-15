package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Jenkins CI/CD Demo</title></head>");
        out.println("<body>");
        out.println("<h1>Bienvenue dans l'application Java avec Jenkins CI/CD!</h1>");
        out.println("<p>Cette application a été déployée automatiquement via Jenkins.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}