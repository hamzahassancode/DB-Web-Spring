package model;

import java.sql.SQLException;

public interface RepositoryInterface {
    public String welcomeMessage()throws SQLException;

   // public  boolean checkLogin(String username, String password) throws SQLException;
    public  String display();
}
