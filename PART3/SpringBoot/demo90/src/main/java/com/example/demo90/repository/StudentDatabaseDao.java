package com.example.demo90.repository;

import com.example.demo90.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("database")
public class StudentDatabaseDao implements StudentDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student add(Student student) {
        final String sql = "INSERT INTO students(username , password , full_name) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, student.getUsername());
            statement.setString(2, student.getPassword());
            statement.setString(2, student.getFull_name());

            return statement;

        }, keyHolder);

        student.setStudent_id(keyHolder.getKey().intValue());

        return student;    }

    @Override
    public List<Student> getAll() {
        final String sql = "SELECT student_id, username, password, full_name FROM students;";
        return jdbcTemplate.query(sql, new ToDoMapper());    }

    @Override
    public Student findById(int student_id) {
        final String sql = "SELECT student_id, username, password, full_name "
                + "FROM students WHERE student_id = ?;";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), student_id);    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean deleteById(int student_id) {
        final String sql = "DELETE FROM students WHERE student_id = ?;";
        return jdbcTemplate.update(sql, student_id) > 0;    }
    private static final class ToDoMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student student = new Student();
            student.setStudent_id(rs.getInt("student_id"));
            student.setUsername(rs.getString("username"));
            student.setPassword(rs.getString("password"));
            student.setFull_name(rs.getString("full_name"));
            return student;
        }
    }
    public List<Map<String, Object>> getStudentGrades(String username) {
        String sqlQuery = "SELECT s.full_name AS student_name, c.course_name, g.grade " +
                "FROM students s " +
                "JOIN grades g ON s.student_id = g.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "WHERE s.username = ?";

        return jdbcTemplate.queryForList(sqlQuery, username);
    }
    public boolean checkLogin(String username, String password) {
        String sqlQuery = "SELECT password FROM students WHERE username = ?";
        String actualPassword = jdbcTemplate.queryForObject(sqlQuery, String.class, username);
        return actualPassword != null && actualPassword.equals(password);
    }

    public String getStudentFullName(String username) {
        String sqlQuery = "SELECT full_name FROM students WHERE username = ?";
        return jdbcTemplate.queryForObject(sqlQuery, String.class, username);
    }

    public Map<String, Object> getCourseInformation(int course_id) {
        String courseInfoQuery = "SELECT course_name FROM courses WHERE course_id = ?";
        String studentCountQuery = "SELECT COUNT(grade) FROM grades WHERE course_id = ?";
        String maxGradeQuery = "SELECT MAX(grade) FROM grades WHERE course_id = ?";
        String minGradeQuery = "SELECT MIN(grade) FROM grades WHERE course_id = ?";
        String avgGradeQuery = "SELECT AVG(grade) FROM grades WHERE course_id = ?";

        String courseName = jdbcTemplate.queryForObject(courseInfoQuery, String.class, course_id);
        int studentCount = jdbcTemplate.queryForObject(studentCountQuery, Integer.class, course_id);
        double maxGrade = jdbcTemplate.queryForObject(maxGradeQuery, Double.class, course_id);
        double minGrade = jdbcTemplate.queryForObject(minGradeQuery, Double.class, course_id);
        double avgGrade = jdbcTemplate.queryForObject(avgGradeQuery, Double.class, course_id);

        Map<String, Object> courseInformation = new HashMap<>();
        courseInformation.put("courseName", courseName);
        courseInformation.put("studentCount", studentCount);
        courseInformation.put("maxGrade", maxGrade);
        courseInformation.put("minGrade", minGrade);
        courseInformation.put("avgGrade", avgGrade);

        return courseInformation;
    }


}
