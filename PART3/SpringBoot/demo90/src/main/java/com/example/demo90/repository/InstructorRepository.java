package com.example.demo90.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InstructorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getStudentFullName(String username) {
        String sqlQuery = "SELECT full_name FROM instructors WHERE username = ?";
        return jdbcTemplate.queryForObject(sqlQuery, String.class, username);
    }

    public boolean checkLogin(String username, String password) {
        String sqlQuery = "SELECT password FROM instructors WHERE username = ?";
        String actualPassword = jdbcTemplate.queryForObject(sqlQuery, String.class, username);
        return actualPassword != null && actualPassword.equals(password);
    }

    public String displayCoursesAndGrades(String username) {
        String sqlQuery = "SELECT c.course_id, c.course_name " +
                "FROM courses c " +
                "WHERE c.instructor_id = (SELECT instructor_id FROM instructors WHERE username = ?)";

        return jdbcTemplate.query(sqlQuery, new Object[]{username}, (resultSet) -> {
            List<String> courses = new ArrayList<>();
            List<Integer> courseIds = new ArrayList<>();
            while (resultSet.next()) {
                courseIds.add(resultSet.getInt("course_id"));
                courses.add(resultSet.getString("course_name"));
            }
            StringBuilder serverMessage = new StringBuilder();

            if (!courses.isEmpty()) {
                for (int i = 0; i < courses.size(); i++) {
                    serverMessage.append("\n").append(i + 1).append(". ").append(courses.get(i)).append(":");
                    serverMessage = appendGradesForCourse(courseIds.get(i), serverMessage);
                }
            } else {
                serverMessage.append("\nYou are not teaching any courses.");
            }
            return serverMessage.toString();
        });
    }

    private StringBuilder appendGradesForCourse(int courseId, StringBuilder serverMessage) {
        String sqlQuery = "SELECT s.full_name AS student_name, g.grade " +
                "FROM students s " +
                "JOIN grades g ON s.student_id = g.student_id " +
                "WHERE g.course_id = ?";
        jdbcTemplate.query(sqlQuery, new Object[]{courseId}, (resultSet) -> {
            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                double grade = resultSet.getDouble("grade");
                serverMessage.append("\n  - ").append(studentName).append(": ").append(grade);
            }
            return null;
        });
        return serverMessage;
    }

    public void insertGrades(int courseId, int studentId, double grade) {
        String sqlQuery = "INSERT INTO grades (course_id, student_id, grade) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, courseId, studentId, grade);
    }

    public boolean isTeachingCourse(String username, int courseId) {
        String sqlQuery = "SELECT COUNT(*) AS count FROM courses WHERE instructor_id = " +
                "(SELECT instructor_id FROM instructors WHERE username = ?)";
        return jdbcTemplate.queryForObject(sqlQuery, Integer.class, username) > 0;
    }
}
