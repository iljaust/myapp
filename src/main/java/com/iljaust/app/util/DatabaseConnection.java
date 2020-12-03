package com.iljaust.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con = null;

    static String url = "jdbc:mysql://localhost/myapp";
    static  String user = "root";
    static  String pass = "1234";

    public static Connection getConnection()
    {
        if(con == null) {
            try {
                con = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
