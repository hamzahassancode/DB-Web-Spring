package com.example.demo90.Controller;

import com.example.demo90.models.LoginRequest;
import com.example.demo90.models.LoginResponse;
import com.example.demo90.service.JWTService;
import com.example.demo90.service.StudentService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final JWTService jwtService;

    @Autowired
    public StudentController(StudentService studentService, JWTService jwtService) {
        this.studentService = studentService;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        if (studentService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = jwtService.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse("Login successful!", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Login failed!"));
        }
    }

    @GetMapping("/welcomeMessage")
    public String welcomeMessage(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("hamza ", "");
        String username = jwtService.getUsernameFromToken(token);
        return studentService.welcomeMessage(username);
    }

        @GetMapping("/displayGrades")
    public String displayGrades(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("hamza ", "");
        String username = jwtService.getUsernameFromToken(token);

        return studentService.display(username);
    }




//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
//        if (studentService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
//            session.setAttribute("username", loginRequest.getUsername());
//            return new LoginResponse("Login successful!", loginRequest.getUsername());
//        } else {
//            return new LoginResponse("Login failed!");
//        }
//    }
//
//
//    @GetMapping("/welcomeMessage")
//    public String welcomeMessage(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        return studentService.welcomeMessage(username);
//    }
//
//    @GetMapping("/login/displayGrades")
//    public String displayGrades(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        return studentService.display(username);
//    }
//
//    @GetMapping("/getCourseInformation")
//    public String getCourseInformation(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        return studentService.display(username);
//    }
//    @PostMapping("/courseInfo")
//    public String getCourseInformation(@RequestParam int course_id) {
//        return studentService.getCourseInformation(course_id);
//    }

}

