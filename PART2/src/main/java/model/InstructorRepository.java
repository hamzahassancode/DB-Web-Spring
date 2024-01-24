package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorRepository implements RepositoryInterface {
    private String  username;
    private String password;
    private static Connection connection=new SecureConnection().getSecureConnection();
    private Statement statement;

    public InstructorRepository(String username, String password){
        this.username=username;
        this.password =password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String welcomeMessage() throws SQLException {
        //System.out.println("Welcome"+ username);
        ResultSet resultSet=statement.executeQuery("SELECT full_name  FROM instructors WHERE username ="+ username);
        String message="";
        while (resultSet.next())
        {
            message= "Welcome "+resultSet.getString("full_name");
        }
        resultSet.close();
        return message;    }

    public static boolean checkLogin(String username, String password) throws SQLException {
        String sqlQuery = "select password from instructors where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        // Set the student name parameter in the prepared statement
        preparedStatement.setString(1, username);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next())
                return resultSet.getString(1).equals(password);
            else
                return false;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public String display() {
        String serverMessage = "";
        try {
            PreparedStatement preparedStatement = prepareQuery();
            serverMessage = generateCourseAndGradeInfo(preparedStatement);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serverMessage;
    }

    private PreparedStatement prepareQuery() throws SQLException {
        String sqlQuery = "SELECT c.course_id, c.course_name " +
                "FROM courses c " +
                "WHERE c.instructor_id = (SELECT instructor_id FROM instructors WHERE username = ? AND password = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }


    private String generateCourseAndGradeInfo(PreparedStatement preparedStatement) throws SQLException {
        List<String> courses = new ArrayList<>();
        List<Integer> coursesIds = new ArrayList<>();

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                coursesIds.add(resultSet.getInt("course_id"));
                courses.add(resultSet.getString("course_name"));
            }
        }
        String serverMessage = "";
        if (!courses.isEmpty()) {
            for (int i = 0; i < courses.size(); i++) {

                serverMessage += "\n" + (i + 1) + ". " + courses.get(i) + ":";
                serverMessage = appendGradesForCourse(coursesIds.get(i), serverMessage);
            }
        } else {
            serverMessage += "\nYou are not teaching any courses.";
        }
        return serverMessage;
    }

    private String appendGradesForCourse(int courseId, String serverMessage) throws SQLException {
        String sqlQuery = "SELECT s.full_name AS student_name, g.grade " +
                "FROM students s " +
                "JOIN grades g ON s.student_id = g.student_id " +
                "WHERE g.course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, courseId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                double grade = resultSet.getDouble("grade");
                serverMessage += "\n  - " + studentName + ": " + grade;
            }
        }
        return serverMessage;
    }
    public void insertGrades(int courseId,int studentId,double grade) {
        try {
            System.out.println("IN INSERT");
            String sqlQuery = "INSERT INTO grades (course_id, student_id, grade) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            System.out.println("IN INSERT 1");
                preparedStatement.setInt(1, courseId);
            System.out.println("IN INSERT 2");

            preparedStatement.setInt(2, studentId);
            System.out.println("IN INSERT 3");

            preparedStatement.setDouble(3, grade);
            System.out.println("IN INSERT 4");

            preparedStatement.executeUpdate();
            System.out.println("IN INSERT 5");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
