package com.model;

import com.connector.DBConnector;
import javax.swing.table.DefaultTableModel;

public class FinanceModel extends DefaultTableModel{
    private DBConnector database;

    public FinanceModel()
    {
        super(Constants.DB_DATA, Constants.TABLE_HEADER);
        
        DBConnector database = new DBConnector();
        if(database.testConnection() != -1)
        {
            this.database = database;
        }
    }

    // DB Function abstraction
    // Filter update
    public Object[][] getDataFromDB(String searchColumn, String searchTerm)
    {
        return database.getQuery(searchColumn, searchTerm);
    }

    // Overview update
    public Object[][] getDataForSpecificDate(String year, String month)
    {
        return database.getQueryForYearAndMonth(year, month);
    }

    //public void addEntryToDB(String, String, )
}
