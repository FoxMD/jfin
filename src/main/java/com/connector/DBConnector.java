package com.connector;

import java.sql.*;
import com.secret.*;

public class DBConnector {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mktest";
    static final String QUERY = "SELECT * FROM test";

    private static String USER = "";
    private static String PASS = "";

    public DBConnector()
    {
        Credentials cred = new Credentials();
        USER = cred.getUser();
        PASS = cred.getPassword();
    }

    public void testConnection()
    {
        try 
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        )
        {
            while(rs.next()) {
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
