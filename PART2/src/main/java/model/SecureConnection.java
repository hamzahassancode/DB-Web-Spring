package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SecureConnection {
    private final String user= "root";
    private final String pass ="hamza@h2h";
    private final String url ="jdbc:mysql://localhost:3306/student_grading_system";
    private Connection connection;
    private Statement statement;

    public Connection getSecureConnection(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student_grading_system","root","hamza@h2h");
        this.statement=connection.createStatement();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return connection;
}

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getUrl() {
        return url;
    }
}
