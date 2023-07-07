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
     * Sets income value.
     * @param income complete income;
     */
    public void setIncome(float income) {
        this.income = income;
    }

    /**
     * Returns all the expenses.
     * @return expenses
     */
    public float getExpenses() {
        return this.expenses;
    }

    /**
     * Sets the complete amount on expenses.
     * @param expenses Sum of expenses.
     */
    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    /**
     * Returns the difference between income and expenses.
     * @return difference
     */
    public float getDifference() {
        this.difference = this.income - this.expenses;
        return this.difference;
    }

    /**
     * Test function.
     * @return some data
     */
    public float getTest() {
        return data;
    }

    public void addEntryToDB(String year, String month, String type, float value, String currency, String description) {
        //database.writeQuery(year, month, type, value, currency, description);
    }

    public void addEntryToDB(Object[] entry) {
        database.writeQuery(entry);
    }

    public void removeEntryFromDB(Object[] entry) {
        database.removeEntryFromDB(entry);
    }

    public void modifyEntryFromDB(Object[] entry, Object[] original) {
        database.modifyEntryFromDB(entry, original);
    }
}
