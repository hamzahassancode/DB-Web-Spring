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

@WebServlet(urlPatterns ="/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
    LoginServlet loginServlet=new LoginServlet();
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

        String courseIdParameter = req.getParameter("course_id");

                    System.out.println(courseIdParameter);
                    if (courseIdParameter != null) {
                        int courseId = Integer.parseInt(courseIdParameter);
                        // Rest of your code that uses the courseId
                        String statistics = null;
                        try {
                            statistics = studentRepository.getCourseInformation(courseId);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("case 2  3");
                        req.setAttribute("statistics", statistics);
                        req.getRequestDispatcher("WEB-INF/CourseStatistics.jsp").forward(req, resp);
                    } else {
                        System.out.println("ERORR IN CORSE ID FORMAT");
                        // Handle the case where "course_id" parameter is not present in the request
                        // You can throw an exception, send an error response, or handle it based on your use case.
                    }
                    //int courseId = Integer.parseInt(req.getParameter("course_id"));
                    System.out.println("case 2  2");


    }
}
