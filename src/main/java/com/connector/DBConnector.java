package com.connector;

import java.sql.*;
import com.secret.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.table.*;
import java.util.*;
public class DBConnector {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mktest";
    static final String BASE_QUERY = "SELECT * FROM test";
    private String QUERY = "SELECT * FROM test WHERE"; 

    private static String USER = "";
    private static String PASS = "";

    /* Add to destructor
     *         rs.close();
        stmt.close();
        connection.close();
     * 
     */
    public DBConnector()
    {
        Credentials cred = new Credentials();
        USER = cred.getUser();
        PASS = cred.getPassword();
    }

    public Object[][] getQuery(String what, String with)
    {
        ArrayList<Object[]> Data = new ArrayList<>();
        String REQUEST = "";
        if("*".equals(with.trim()))
        {
            REQUEST = BASE_QUERY;
        }
        else
        {
            REQUEST = this.QUERY + " " + what + " LIKE \"" + with + "%\""; 
        }
        
        System.out.println(REQUEST);
        try 
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(REQUEST);
        )
        {
            while(rs.next()) {
                Data.add(new Object[]{  rs.getString("month"),
                                            rs.getString("type"),
                                        rs.getFloat("value"),
                                        rs.getString("currency")
                                    });
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        Object[][] dataObject = Data.toArray(new Object[0][0]);
        return dataObject; 
    }

    public Object[][] getPreciseQuery(String what, String with)
    {
        ArrayList<Object[]> Data = new ArrayList<>();

        Data.add(new Object[]{"January","GROCERY",199.99,"CZK"});
        Data.add(new Object[]{"February","GROCERY",9.99,"EUR"});

        Object retData[][] = Data.toArray(new Object[0][0]);
        return retData;
    }

    public void testConnection()
    {
        try 
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(BASE_QUERY);
        )
        {
            while(rs.next()) {
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));

                //rs.close();
                //stmt.close();
                //conn.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
