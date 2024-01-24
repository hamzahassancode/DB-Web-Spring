package com.example.demo3;

import model.InstructorRepository;
import model.SecureConnection;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ="/putgrades")
public class PutGradesServlet extends HttpServlet {
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
        InstructorRepository instructorRepository = new InstructorRepository(loggedInUser.getUsername(), loggedInUser.getPassword());



        int courseId = Integer.parseInt(req.getParameter("courseId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        double grade = Double.parseDouble(req.getParameter("grade"));
        System.out.println(courseId);

            String statistics = null;
                instructorRepository.insertGrades(courseId,studentId,grade);
        String message = "Grade entry successful for Course ID: " + courseId +
                ", Student ID: " + studentId +
                ", Grade: " + grade;

            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/addGradDone.jsp").forward(req, resp);

        System.out.println("case 2  2");


    }
}
