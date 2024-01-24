package com.example.demo90.service;

import com.example.demo90.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public String welcomeMessage(String username) {
        String fullName = instructorRepository.getStudentFullName(username);
        if (fullName != null) {
            return "Welcome " + fullName;
        } else {
            return "Invalid username or password!";
        }
    }
    public boolean authenticate(String username, String password) {
        return instructorRepository.checkLogin(username, password);
    }

    public String getCoursesAndGrades(String username) {
        return instructorRepository.displayCoursesAndGrades(username);
    }

    public void addGrade(int courseId, int studentId, double grade) {
        instructorRepository.insertGrades(courseId, studentId, grade);
    }

    public boolean isTeachingCourse(String username, int courseId) {
        return instructorRepository.isTeachingCourse(username, courseId);
    }
}
