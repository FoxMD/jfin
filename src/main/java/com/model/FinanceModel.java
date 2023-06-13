package com.model;

import com.connector.DBConnector;
import javax.swing.table.DefaultTableModel;

public class FinanceModel extends DefaultTableModel {
    private DBConnector database;
    private int data;

    private float income;
    private float expenses;
    private float difference;

    private final int testValue = 13;

    public FinanceModel() {
        super(Constants.databaseData, Constants.TABLE_HEADER);

        DBConnector database = new DBConnector();
        if (database.testConnection() != -1) {
            this.database = database;
            this.data = testValue;
        }
    }

    // DB Function abstraction
    // Filter update
    public Object[][] getDataFromDB(String searchColumn, String searchTerm) {
        return database.getQuery(searchColumn, searchTerm);
    }

    // Overview update
    public Object[][] getDataForSpecificDate(String year, String month) {
        Object[][] overview;
        overview = database.getQueryForYearAndMonth(year, month);

        for (Object[] o: overview) {
            System.out.println(o[2]);
        }

        return overview;
    }

    public void setChartValues(int data) {
        this.data += data;
    }

    public int getChartValues() {
        return this.data;
    }

    public float getIncome() {
        return this.income;
    }

    public float getExpenses() {
        return this.expenses;
    }

    public float getDifference() {
        return this.difference;
    }

    public float getTest() {
        return data;
    }
    //public void addEntryToDB(String, String, )
}
