package com.example.demo90.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String welcomeMessage() {
        return "Welcome to the ADMIN page" +
                "\n-add student enter (1)" +
                "\n-delete student enter (2)" +
                "\n-add instructor enter (3)" +
                "\n-delete instructor enter (4)" +
                "\n-add course enter (5)" +
                "\n-delete course enter (6)" +
                "\n-delete student enter (7)" +
                "\n-register Student For Course enter (8)" +
                "\n-appoint instructor For Course (9)" +
                "\n-exit (10)";
    }

    public boolean checkLogin(String username, String password) {
        String sqlQuery = "SELECT password FROM admin WHERE username = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{username}, (resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getString("password").equals(password);
            } else {
                return false;
            }
        });
    }

    public void addStudent(String fullName, String username, String password) {
        String sqlQuery = "INSERT INTO students (full_name, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, fullName, username, password);
    }

    public void deleteStudent(int studentId) {
        String sqlQuery = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sqlQuery, studentId);
    }

    public void addInstructor(String fullName, String username, String password) {
        String sqlQuery = "INSERT INTO instructors (full_name, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, fullName, username, password);
    }

    public void deleteInstructor(int instructorId) {
        String sqlQuery = "DELETE FROM instructors WHERE instructor_id = ?";
        jdbcTemplate.update(sqlQuery, instructorId);
    }

    public void addCourse(String courseName, int instructorId) {
        String sqlQuery = "INSERT INTO courses (course_name, instructor_id) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, courseName, instructorId);
    }

    public void deleteCourse(int courseId) {
        String sqlQuery = "DELETE FROM courses WHERE course_id = ?";
        jdbcTemplate.update(sqlQuery, courseId);
    }

    public void registerStudentForCourse(int studentId, int courseId) {
        String sqlQuery = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, studentId, courseId);
    }

    public void appointTeacherForCourse(int instructorId, int courseId) {
        String sqlQuery = "UPDATE courses SET instructor_id = ? WHERE course_id = ?";
        jdbcTemplate.update(sqlQuery, instructorId, courseId);
    }
}
