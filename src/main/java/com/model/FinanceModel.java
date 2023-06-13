package com.model;

import com.connector.DBConnector;
import javax.swing.table.DefaultTableModel;

/**
 * Financial model for tables.
 */
public class FinanceModel extends DefaultTableModel {
    private DBConnector database;
    private int data;

    private float income;
    private float expenses;
    private float difference;

    private final int testValue = 13;
    
    /**
     * Constructor for the financial model.
     */
    public FinanceModel() {
        super(Constants.databaseData, Constants.TABLE_HEADER);

        DBConnector database = new DBConnector();
        if (database.testConnection() != -1) {
            this.database = database;
            this.data = testValue;
        }
    }

    /**
     * Return data from a database.
     * @param searchColumn What column is needed
     * @param searchTerm What are you looking for
     * @return return your request
     */
    public Object[][] getDataFromDB(String searchColumn, String searchTerm) {
        return database.getQuery(searchColumn, searchTerm);
    }

    /**
     * Returns data for a specific month and year.
     * @param year  Selected year
     * @param month Selected month
     * @return returns an array of arrays containing db information
     */
    public Object[][] getDataForSpecificDate(String year, String month) {
        Object[][] overview;
        overview = database.getQueryForYearAndMonth(year, month);

        for (Object[] o: overview) {
            System.out.println(o[2]);
        }

        return overview;
    }

    /**
     * Sets the dataset for the chart overview.
     * @param data input data
     */
    public void setChartValues(int data) {
        this.data += data;
    }

    /**
     * Returns the private data for the chart.
     * @return chart values.
     */
    public int getChartValues() {
        return this.data;
    }

    /**
     * Returns income.
     * @return income
     */
    public float getIncome() {
        return this.income;
    }

    /**
     * Returns all the expenses.
     * @return expenses
     */
    public float getExpenses() {
        return this.expenses;
    }

    /**
     * Returns the difference between income and expenses.
     * @return difference
     */
    public float getDifference() {
        return this.difference;
    }

    /**
     * Test function.
     * @return some data
     */
    public float getTest() {
        return data;
    }
    //public void addEntryToDB(String, String, )
}
