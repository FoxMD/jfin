package com.model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.connector.IDatabase;
import com.connector.IDatabase.QTYPE;

/**
 * Financial model for tables.
 */
public class FinanceModel extends DefaultTableModel {
    private IDatabase database;
    private Map<String, Float> data;

    private float income;
    private float expenses;
    private float difference;

    private float transferRate = 23.88f;

    /**
     * Constructor for the financial model.
     */
    public FinanceModel(IDatabase database) {
        super(Constants.databaseData, Constants.TABLE_HEADER);
        if (database == null) {
            return;
        }

        this.database = database;
        data = new HashMap<String, Float>();
        this.expenses = 0.0f;
        this.income = 0.0f;
    }

    public IDatabase getDBInstance() {
        return this.database;
    }

    /**
     * Return data from a database.
     * @param searchColumn What column is needed
     * @param searchTerm What are you looking for
     * @return return your request
     */
    public Object[][] getDataFromDB(String searchColumn, String searchTerm) {
        return database.getQuery(searchColumn, searchTerm, QTYPE.STANDARD);
    }

    /**
     * Returns data for a specific month and year.
     * @param year  Selected year
     * @param month Selected month
     * @return returns an array of arrays containing db information
     */
    public Object[][] getDataForSpecificDate(String year, String month) {
        Object[][] overview;
        overview = database.getQuery(year, month, QTYPE.DATE);

        for (Object[] o: overview) {
            System.out.println(o[2]);
        }

        return overview;
    }

    /**
     * Sets the dataset for the chart overview.
     * @param data input data
     */
    public void setNewDataValues(Object[][] data) {
        this.expenses = 0.0f;
        this.income = 0.0f;

        for (Object[] o : data) {
            String sCurrency = (String) o[Utils.Entries.CURRENCY.ordinal()];
            if ("CZK".equals(sCurrency)) {
                o[Utils.Entries.VALUE.ordinal()] = (float) o[Utils.Entries.VALUE.ordinal()] / transferRate;
            }

            String sType = (String) o[Utils.Entries.TYPE.ordinal()];
            if ("Income".equals(sType)) {
                setIncome((Float) o[Utils.Entries.VALUE.ordinal()]);
                setChartValues("Savings", getDifferenceForChart());
            } else {
                setExpenses((Float) o[Utils.Entries.VALUE.ordinal()]);
                setChartValues((String) o[Utils.Entries.TYPE.ordinal()],
                                (Float) o[Utils.Entries.VALUE.ordinal()]);
            }
        }
    }

    private void setChartValues(String key, Float value) {
        addToChartData(key, value);
    }

    private void addToChartData(String key, Float value) {
        if (this.data.get(key) == null) {
            this.data.put(key, value);
        } else {
            this.data.merge(key, value, Float::sum);
        }
    }

    /**
     * Returns the private data for the chart.
     * @return chart values.
     */
    public Map<String, Float> getChartValues() {
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
        this.income += income;
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
        this.expenses += expenses;
    }

    /**
     * Returns the difference between income and expenses.
     * @return difference
     */
    public float getDifference() {
        this.difference = this.income - this.expenses;
        return this.difference;
    }

    public float getDifferenceForChart() {
        this.difference = this.income - this.expenses;
        return this.difference > 0.0f ? this.difference : 0.0f;
    }

    /**
     * Test function.
     * @return some data
     *//*
    public Map<String, Float> getTest() {
        return this.data;
    }
    */
    public int writeQuery(Object[] entry) {
        return database.writeQuery(entry);
    }

    public int removeEntryFromDB(Object[] entry) {
        return database.removeEntryFromDB(entry);
    }

    public int modifyEntryFromDB(Object[] entry) {
        return database.modifyEntryFromDB(entry);
    }
}
