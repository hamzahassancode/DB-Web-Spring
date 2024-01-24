package Repository;

import java.sql.*;

public class StudentRepository implements RepositoryInterface {

    private  String username;
    private  String password;
    private Connection connection=new SecureConnection().getSecureConnection();
    private Statement statement= connection.createStatement();





    public StudentRepository(String username, String password) throws SQLException {
        this.username=username;
        this.password=password;
    }


    public  boolean checkLogin(String username, String password) throws SQLException {

        String sqlQuery = "select password from students where username = ?";
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

    public String welcomeMessage() throws SQLException {

        System.out.println("Welcome"+ username);
        ResultSet resultSet=statement.executeQuery("SELECT full_name AS student_name FROM students WHERE username ="+ username);
        System.out.println("Welcome"+ username);

        String message="";
        while (resultSet.next())
        {
            message= "Welcome "+resultSet.getString("student_name");
        }
        resultSet.close();
        return message;

    }
    public  String display() {

        String serverMessage = "";
        try {
            //serverMessage = "--------Student page-----------";
            // SQL query to get grades of a specific student for each course
            String sqlQuery = "SELECT s.full_name AS student_name, c.course_name, g.grade " +
                    "FROM students s " +
                    "JOIN grades g ON s.student_id = g.student_id " +
                    "JOIN courses c ON g.course_id = c.course_id " +
                    "WHERE s.username = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            System.out.println("studentService");
            System.out.println("studentService"+ username);

            // Set the student name parameter in the prepared statement
            preparedStatement.setString(1, username);

            // Execute the query and process the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String studentName = resultSet.getString("student_name");
                    serverMessage+="\nwelcome " + studentName;
//                    System.out.println("welcome " + studentName);

                    serverMessage+="\nyour grades: ";
                    System.out.println("your grades: ");
                    String courseName1 = resultSet.getString("course_name");
                    double grade1 = resultSet.getDouble("grade");
                    serverMessage+="\n"+courseName1 + "\t" + " Grade: " + grade1;
                    while (resultSet.next()) {
                        System.out.println("3");

                        String courseName = resultSet.getString("course_name");
                        double grade = resultSet.getDouble("grade");

                        serverMessage+="\n"+courseName + "\t" + " Grade: " + grade;
                    }

                }else {
                    serverMessage += "\nInvalid username or password!";
                    serverMessage += "\ntry again..";
                }

                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return serverMessage;
    }
    public  String showMarks() throws SQLException {
        System.out.println("SHOW MARKS"+ username);
        ResultSet resultSet=statement.executeQuery("select courses.course_name, grades.grade FROM grades " +
                " JOIN courses ON  courses.course_id=grades.course_id where grades.id="+ username);
        String message="";
        while (resultSet.next())
        {
            message+= "course: "+resultSet.getString(1)+" mark: "+resultSet.getInt(2)+"\n";
        }
        resultSet.close();
        return message;
    }


    public  String getCourseInformation(int course_id) throws SQLException, ClassNotFoundException {
        System.out.println("getCourseInformation");
        String message="";
        ResultSet resultSet=statement.executeQuery("select grades.grade FROM grades where course_id="+course_id +" && id="+ username);
        resultSet.next();
        message+="your grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select count(grade) FROM grades where course_id="+course_id);
        resultSet.next();
        message+="number of student who took the exam: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select max(grade) FROM grades where course_id="+course_id);
        resultSet.next();
        message+="max grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select min(grade) FROM grades where course_id="+course_id);
        resultSet.next();
        message+="min grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select avg(grade) FROM grades where course_id="+course_id);
        resultSet.next();
        message+="mean of grades: "+resultSet.getInt(1)+"\n";
        resultSet.close();
        return message;
    }

}
