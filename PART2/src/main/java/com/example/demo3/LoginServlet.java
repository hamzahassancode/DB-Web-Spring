package com.example.demo3;


import model.InstructorRepository;
import model.SecureConnection;
import model.StudentRepository;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns ="/login")
public class LoginServlet extends HttpServlet {

    private String username;
    private String password;
    @Override
    public void init(ServletConfig config) throws ServletException {
        SecureConnection secureConnection=new SecureConnection();
        secureConnection.getSecureConnection();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        this.username=username;
        this.password=password;
        HttpSession sessionToKnow = req.getSession();
        String whoAreU = (String) sessionToKnow.getAttribute("whoAreU");
        try {
            if (whoAreU.equals("student")){
                System.out.println(whoAreU);
                if (StudentRepository.checkLogin(username,password)) {
                    req.setAttribute("username",username);
                    User loggedInUser = new User(username, password);
                    HttpSession session = req.getSession();
                    session.setAttribute("loggedInUser", loggedInUser);
                    req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
                    System.out.println("yes");

                }  else {
                    req.setAttribute("errorMessage","Wrong username or password!" );
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
                }

             } else if (whoAreU.equals("instructor")) {
                if (InstructorRepository.checkLogin(username,password)) {
                    req.setAttribute("username",username);
                    User loggedInUser = new User(username, password);
                    HttpSession session = req.getSession();
                    session.setAttribute("loggedInUser", loggedInUser);
                    req.getRequestDispatcher("WEB-INF/Instructor_page.jsp").forward(req, resp);
                    System.out.println("yes");

                }  else {
                    req.setAttribute("errorMessage","Wrong username or password!" );
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
