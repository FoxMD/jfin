package com.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.Utils;

import java.sql.PreparedStatement;

/**
 * Connector for database server, handles work with mysql.
 */
public class DBConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dummy";
    private static final String BASE_QUERY = "SELECT * FROM test";
    private static final String USER = SysHandler.getVariable("USER_DB_KEY");
    private static final String PASSWORD = SysHandler.getVariable("PASSWORD_DB_KEY");
    private String query = "SELECT * FROM test WHERE";

    /**
     * Constructor for the class.
     */
    public DBConnector() {
        System.out.println("Connecting to database with: " + USER + ", PW: " + PASSWORD);
    }

    /**
     * Write a query request for the DB.
     * @param year Year.
     * @param month Month.
     * @param type Type of expense.
     * @param value Cost of it.
     * @param currency EUR or CZK.
     * @param description Description of buyed item.
     * @return
     */
    public int writeQuery(Object[] entry) {
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO test VALUES (?,?,?,?,?,?)");
        ) {
            pstmt.setString(Utils.Entries.YEAR.ordinal() + 1, (String) entry[Utils.Entries.YEAR.ordinal()]);
            pstmt.setString(Utils.Entries.MONTH.ordinal() + 1, (String) entry[Utils.Entries.MONTH.ordinal()]);
            pstmt.setString(Utils.Entries.TYPE.ordinal() + 1, (String) entry[Utils.Entries.TYPE.ordinal()]);
            pstmt.setFloat(Utils.Entries.VALUE.ordinal() + 1,
                        Float.parseFloat((String) entry[Utils.Entries.VALUE.ordinal()])
            );
            pstmt.setString(Utils.Entries.CURRENCY.ordinal() + 1, (String) entry[Utils.Entries.CURRENCY.ordinal()]);
            pstmt.setString(Utils.Entries.DESC.ordinal() + 1, (String) entry[Utils.Entries.DESC.ordinal()]);
            pstmt.executeUpdate(); // "rows" save the affected rows

            //rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * Returns an array of results from the database.
     * @param what column entry name
     * @param with entry name starts with
     * @return array of mysql entry as array
     */
    public Object[][] getQuery(String what, String with) {
        ArrayList<Object[]> data = new ArrayList<>();
        String request = "";
        if ("*".equals(with.trim())) {
            request = BASE_QUERY;
        } else {
            request = this.query + " " + what + " LIKE \"" + with + "%\"";
        }

        System.out.println(request);
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(request);
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

    /**
     * Method to get a specific entries for a month in a year.
     * @param year year you want to search
     * @param month month in this year
     * @return array of mysql array entries for this specific date
     */
    public Object[][] getQueryForYearAndMonth(String year, String month) {
        ArrayList<Object[]> data = new ArrayList<>();
        String request = "";

        request = this.query + " month='" + month + "' AND year='" + year + "'";

        System.out.println(request);
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(request);
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

    /**
     * Returns a precize querry.
     * @param what column entry name
     * @param with what is the value of the coumn
     * @return array of mysql array entries for this specific date
     */
    public Object[][] getPreciseQuery(String what, String with) {
        ArrayList<Object[]> data = new ArrayList<>();

        final float testCZK = 199.99f;
        final float testEUR = 9.99f;

        data.add(new Object[]{"January", "GROCERY", testCZK, "CZK"});
        data.add(new Object[]{"February", "GROCERY", testEUR, "EUR"});

        Object[][] retData = data.toArray(new Object[0][0]);
        return retData;
    }

    public int removeEntryFromDB(Object[] entry) {
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM test WHERE year=? AND month=? AND type=? AND value=? AND currency=? AND description=?"
            );
        ) {
            pstmt.setString(Utils.Entries.YEAR.ordinal() + 1, (String) entry[Utils.Entries.YEAR.ordinal()]);
            pstmt.setString(Utils.Entries.MONTH.ordinal() + 1, (String) entry[Utils.Entries.MONTH.ordinal()]);
            pstmt.setString(Utils.Entries.TYPE.ordinal() + 1, (String) entry[Utils.Entries.TYPE.ordinal()]);
            pstmt.setFloat(Utils.Entries.VALUE.ordinal() + 1, (Float) entry[Utils.Entries.VALUE.ordinal()]);
            pstmt.setString(Utils.Entries.CURRENCY.ordinal() + 1, (String) entry[Utils.Entries.CURRENCY.ordinal()]);
            pstmt.setString(Utils.Entries.DESC.ordinal() + 1, (String) entry[Utils.Entries.DESC.ordinal()]);
            pstmt.executeUpdate(); // "rows" save the affected rows

            //rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int modifyEntryFromDB(Object[] entry, Object[] origin) {
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE test SET year=?, month=?, type=?, value=?, currency=?, description=? WHERE year=? AND month=? AND type=? AND value=? AND currency=? AND description=?"
            );
        ) {
            pstmt.setString(Utils.Entries.YEAR.ordinal() + 1, (String) entry[Utils.Entries.YEAR.ordinal()]);
            pstmt.setString(Utils.Entries.MONTH.ordinal() + 1, (String) entry[Utils.Entries.MONTH.ordinal()]);
            pstmt.setString(Utils.Entries.TYPE.ordinal() + 1, (String) entry[Utils.Entries.TYPE.ordinal()]);
            pstmt.setFloat(Utils.Entries.VALUE.ordinal() + 1, Float.parseFloat((String) entry[Utils.Entries.VALUE.ordinal()]));
            pstmt.setString(Utils.Entries.CURRENCY.ordinal() + 1, (String) entry[Utils.Entries.CURRENCY.ordinal()]);
            pstmt.setString(Utils.Entries.DESC.ordinal() + 1, (String) entry[Utils.Entries.DESC.ordinal()]);

            pstmt.setString(Utils.Entries.YEAR.ordinal() + 7, (String) origin[Utils.Entries.YEAR.ordinal()]);
            pstmt.setString(Utils.Entries.MONTH.ordinal() + 7, (String) origin[Utils.Entries.MONTH.ordinal()]);
            pstmt.setString(Utils.Entries.TYPE.ordinal() + 7, (String) origin[Utils.Entries.TYPE.ordinal()]);
            pstmt.setFloat(Utils.Entries.VALUE.ordinal() + 7, (Float) origin[Utils.Entries.VALUE.ordinal()]);
            pstmt.setString(Utils.Entries.CURRENCY.ordinal() + 7, (String) origin[Utils.Entries.CURRENCY.ordinal()]);
            pstmt.setString(Utils.Entries.DESC.ordinal() + 7, (String) origin[Utils.Entries.DESC.ordinal()]);

            pstmt.executeUpdate(); // "rows" save the affected rows

            //rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }


    /**
     * Method for testing a connection.
     * @return returns true 0 if everythink is OK
     */
    public int testConnection() {
        try
        (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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
