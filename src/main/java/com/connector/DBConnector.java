package com.connector;

import com.secret.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
    static final String DB_URL = "jdbc:mysql://localhost:3306/dummy";
    static final String BASE_QUERY = "SELECT * FROM test";
    private String QUERY = "SELECT * FROM test WHERE"; 

    private static String USER = "";
    private static String PASS = "";

    public DBConnector() {
        Credentials cred = new Credentials();
        USER = cred.getUser();
        PASS = cred.getPassword();
    }

    public Object[][] getQuery(String what, String with) {
        ArrayList<Object[]> Data = new ArrayList<>();
        String REQUEST = "";
        if ("*".equals(with.trim())) {
            REQUEST = BASE_QUERY;
        } else {
            REQUEST = this.QUERY + " " + what + " LIKE \"" + with + "%\"";
        }

        System.out.println(REQUEST);
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(REQUEST);
        ) {
            while (rs.next()) {
                Data.add(new Object[]{rs.getString("year"),
                                    rs.getString("month"),
                                    rs.getString("type"),
                                    rs.getFloat("value"),
                                    rs.getString("currency"),
                                    rs.getString("description"),
                });
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] dataObject = Data.toArray(new Object[0][0]);
        return dataObject;
    }

    public Object[][] getQueryForYearAndMonth(String year, String month) {
        ArrayList<Object[]> data = new ArrayList<>();
        String REQUEST = "";

        REQUEST = this.QUERY + " month='" + month + "' AND year='" + year + "'";

        System.out.println(REQUEST);
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(REQUEST);
        ) {
            while (rs.next()) {
                data.add(new Object[]{rs.getString("year"),
                                    rs.getString("month"),
                                    rs.getString("type"),
                                    rs.getFloat("value"),
                                    rs.getString("currency"),
                                    rs.getString("description"),
                });
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] dataObject = data.toArray(new Object[0][0]);
        return dataObject;
    }

    public Object[][] getPreciseQuery(String what, String with) {
        ArrayList<Object[]> data = new ArrayList<>();

        data.add(new Object[]{"January", "GROCERY", 199.99, "CZK"});
        data.add(new Object[]{"February", "GROCERY", 9.99, "EUR"});

        Object[][] retData = data.toArray(new Object[0][0]);
        return retData;
    }

    public int testConnection() {
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(BASE_QUERY);
        ) {
            while (rs.next()) {
                System.out.print("Month: " + rs.getString("month"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.print(", Value: " + rs.getFloat("value"));
                System.out.println(", Currency: " + rs.getString("currency"));

                //rs.close();
                //stmt.close();
                //conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
