package com.example.demo90.Controller;


import com.example.demo90.models.LoginRequest;
import com.example.demo90.models.LoginResponse;
import com.example.demo90.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/login")
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (adminService.checkLogin(loginRequest.getUsername(), loginRequest.getPassword())) {
            session.setAttribute("username", loginRequest.getUsername());
            return new LoginResponse("Login successful!", loginRequest.getUsername());
        } else {
            return new LoginResponse("Login failed!");
        }    }



    @PostMapping("/login/addStudent")
    public String addStudent(@RequestParam String fullName, @RequestParam String username, @RequestParam String password) {
        adminService.addStudent(fullName, username, password);
    return "student successfully added. ";
    }

    @PostMapping("/login/deleteStudent")
    public String deleteStudent(@RequestParam int studentId) {
        adminService.deleteStudent(studentId);
        return "student successfully Delete. ";
    }

    @PostMapping("/login/addInstructor")
    public String addInstructor(@RequestParam String fullName, @RequestParam String username, @RequestParam String password) {
        adminService.addInstructor(fullName, username, password);
        return "student successfully added. ";

    }

    @PostMapping("/login/deleteInstructor")
    public void deleteInstructor(@RequestParam int instructorId) {
        adminService.deleteInstructor(instructorId);
    }

    @PostMapping("/login/addCourse")
    public void addCourse(@RequestParam String courseName, @RequestParam int instructorId) {
        adminService.addCourse(courseName, instructorId);
    }

    @PostMapping("/login/deleteCourse")
    public void deleteCourse(@RequestParam int courseId) {
        adminService.deleteCourse(courseId);
    }

    @PostMapping("/login/registerStudentForCourse")
    public void registerStudentForCourse(@RequestParam int studentId, @RequestParam int courseId) {
        adminService.registerStudentForCourse(studentId, courseId);
    }

    @PostMapping("/login/appointTeacherForCourse")
    public void appointTeacherForCourse(@RequestParam int instructorId, @RequestParam int courseId) {
        adminService.appointTeacherForCourse(instructorId, courseId);
    }
}
