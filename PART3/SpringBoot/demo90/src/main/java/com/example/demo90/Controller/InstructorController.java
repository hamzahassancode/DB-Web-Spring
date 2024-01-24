package com.example.demo90.Controller;


import com.example.demo90.models.LoginRequest;
import com.example.demo90.models.LoginResponse;
import com.example.demo90.service.InstructorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (instructorService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            session.setAttribute("username", loginRequest.getUsername());
            return new LoginResponse("Login successful!", loginRequest.getUsername());
        } else {
            return new LoginResponse("Login failed!");
        }
    }

    @GetMapping("/login/welcomeMessage")
    public String getWelcomeMessage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return instructorService.welcomeMessage(username);
    }


    @GetMapping("/login/getCoursesAndGrades")
    public String getCoursesAndGrades(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return instructorService.getCoursesAndGrades(username);
    }

    @PostMapping("/login/addGrade")
    public String addGrade(@RequestParam int courseId, @RequestParam int studentId, @RequestParam double grade) {
        instructorService.addGrade(courseId, studentId, grade);
        return "Grade successfully added. ";
    }

    @PostMapping("/login/teachingCourse")
    public boolean isTeachingCourse(HttpSession session, @RequestParam int course_id) {
        String username = (String) session.getAttribute("username");
        return instructorService.isTeachingCourse(username, course_id);
    }
}
