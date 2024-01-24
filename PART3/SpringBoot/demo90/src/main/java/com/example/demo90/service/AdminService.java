package com.example.demo90.service;


import com.example.demo90.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String getWelcomeMessage() {
        return adminRepository.welcomeMessage();
    }

    public boolean checkLogin(String username, String password) {
        return adminRepository.checkLogin(username, password);
    }

    public void addStudent(String fullName, String username, String password) {
        adminRepository.addStudent(fullName, username, password);
    }

    public void deleteStudent(int studentId) {
        adminRepository.deleteStudent(studentId);
    }

    public void addInstructor(String fullName, String username, String password) {
        adminRepository.addInstructor(fullName, username, password);
    }

    public void deleteInstructor(int instructorId) {
        adminRepository.deleteInstructor(instructorId);
    }

    public void addCourse(String courseName, int instructorId) {
        adminRepository.addCourse(courseName, instructorId);
    }

    public void deleteCourse(int courseId) {
        adminRepository.deleteCourse(courseId);
    }

    public void registerStudentForCourse(int studentId, int courseId) {
        adminRepository.registerStudentForCourse(studentId, courseId);
    }

    public void appointTeacherForCourse(int instructorId, int courseId) {
        adminRepository.appointTeacherForCourse(instructorId, courseId);
    }
}
