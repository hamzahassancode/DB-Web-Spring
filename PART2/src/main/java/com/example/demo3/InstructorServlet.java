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

@WebServlet(urlPatterns ="/InstructorServlet")
public class InstructorServlet extends HttpServlet {
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

        boolean exit=false;
        while (!exit){
            String choice= req.getParameter("choice");
            switch (choice) {
                case "1":
                    System.out.println("choice 1");
                    String marks1 = instructorRepository.display();

                    req.setAttribute("marks1", marks1);
                    // req.setAttribute("id",id);
                    System.out.println(marks1);
                    req.getRequestDispatcher("WEB-INF/instructorData.jsp").forward(req, resp);
                    System.out.println("end choice 1");

                    break;
                case "2": {
                    System.out.println("case 2");
                    req.getRequestDispatcher("WEB-INF/instructor_put_grades.jsp").forward(req, resp);
                    break;
                }
                case "3":
                    exit = true;
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
                    break;
                default: {

                    break;
                }
            }

        }}
}
