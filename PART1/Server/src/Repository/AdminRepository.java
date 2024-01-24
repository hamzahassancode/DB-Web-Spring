package Repository;

import java.sql.*;

public class AdminRepository implements RepositoryInterface {

    private Connection connection = new SecureConnection().getSecureConnection();
    private Statement statement;
    private String  username;
    private String  password;
    public AdminRepository(String username, String password) {
        this.username=username;
        this.password=password;
    }

    @Override
    public String welcomeMessage() throws SQLException {
        //System.out.println("Welcome"+ username);
        ResultSet resultSet=statement.executeQuery("SELECT full_name  FROM admin WHERE username ="+ username);
        String message="Welcome to the ADMIN page  "+
                "\n-add student enter (1)  "+
                "\n-delete student enter  (2)  "+
                "\n-add instructor enter (3)  "+
                "\n-delete instructor enter (4)  "+
                "\n-add course enter (5)  "+
                "\n-delete course enter  (6)  "+
                "\n-delete student enter  (7)  "+
                "\n-register Student For Course enter (8)"+
                "\n-appoint instructor For Course (9)"+
                "\n-exit (10)"
                ;



        while (resultSet.next())
        {
            message= "Welcome "+resultSet.getString("full_name");
        }
        resultSet.close();
        return message;
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        String sqlQuery = "select password from admin where username = ?";
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
        }    }

    @Override
    public String display() {
        return null;
    }



        // Constructor, if needed

        // Add a new student to the database
        public void addStudent(String fullName, String username, String password) {
            try {
                String sqlQuery = "INSERT INTO students (full_name, username, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete a student from the database
        public void deleteStudent(int studentId) {
            try {
                String sqlQuery = "DELETE FROM students WHERE student_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Add a new teacher to the database
        public void addInstructor(String fullName, String username, String password) {
            try {
                String sqlQuery = "INSERT INTO instructors (full_name, username, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete a teacher from the database
        public void deleteInstructor(int instructorId) {
            try {
                String sqlQuery = "DELETE FROM instructors WHERE instructor_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, instructorId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Add a new course to the database
        public void addCourse(String courseName, int instructorId) {
            try {
                String sqlQuery = "INSERT INTO courses (course_name, instructor_id) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, courseName);
                preparedStatement.setInt(2, instructorId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete a course from the database
        public void deleteCourse(int courseId) {
            try {
                String sqlQuery = "DELETE FROM courses WHERE course_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, courseId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Register a student for a course
        public void registerStudentForCourse(int studentId, int courseId) {
            try {
                String sqlQuery = "INSERT INTO courses (student_id, course_id) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, courseId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Appoint a teacher for a course
        public void appointTeacherForCourse(int instructorid, int courseId) {
            try {
                String sqlQuery = "UPDATE courses SET instructor_id = ? WHERE course_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, instructorid);
                preparedStatement.setInt(2, courseId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


