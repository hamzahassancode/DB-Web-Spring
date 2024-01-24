package com.example.demo3;

import model.SecureConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ="/intropage")
public class IntroServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        SecureConnection secureConnection=new SecureConnection();
        secureConnection.getSecureConnection();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/intropage.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option"); // Get the selected option
        String whoAreU="";
        HttpSession session = req.getSession();
        if ("student".equals(option)) {
            whoAreU="student";
            session.setAttribute("whoAreU", whoAreU);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);

        } else if ("instructor".equals(option)) {
            whoAreU="instructor";
            session.setAttribute("whoAreU", whoAreU);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);

        } else if ("admin".equals(option)) {
            whoAreU="student";
            session.setAttribute("whoAreU", whoAreU);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
        } else {
            // Handle invalid option
            resp.sendRedirect("intropage"); // Redirect back to intro page
        }
    }
}
