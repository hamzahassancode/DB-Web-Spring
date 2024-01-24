package com.example.demo3;

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

@WebServlet(urlPatterns ="/marks")
public class StudentServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        SecureConnection secureConnection=new SecureConnection();
secureConnection.getSecureConnection();
        super.init(config);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        StudentRepository studentRepository = null;
        try {
            studentRepository = new StudentRepository(loggedInUser.getUsername(), loggedInUser.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean exit=false;
        while (!exit){
        String choice= req.getParameter("choice");
            switch (choice) {
                case "1":
                    System.out.println("choice 1");
                    String marks = studentRepository.display();

                    req.setAttribute("marks", marks);
                    // req.setAttribute("id",id);
                    System.out.println(marks);
                    req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
                    System.out.println("end choice 1");

                    break;
                case "2": {
                    System.out.println("case 2");
                    req.getRequestDispatcher("WEB-INF/grades.jsp").forward(req, resp);
                    break;
                }
                case "3":
                    exit = true;
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
                    break;
                default: {
                    System.out.println("IN ELSE");
                    int courseId = Integer.parseInt(req.getParameter("course_id"));
                    System.out.println("E11");
                    System.out.println(courseId);
                    try {
                        System.out.println("E22");
                        String statistics = studentRepository.getCourseInformation(courseId);
                        //req.setAttribute("id",id);
                        System.out.println("E33");
                        req.setAttribute("statistics", statistics);
                        System.out.println("E44");
                        req.getRequestDispatcher("WEB-INF/grades.jsp").forward(req, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

    }}
}
