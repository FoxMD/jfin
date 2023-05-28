package com.model;

import com.connector.DBConnector;
import javax.swing.table.DefaultTableModel;

public class FinanceModel extends DefaultTableModel{
    private DBConnector database;

    public FinanceModel()
    {
        super(Constants.DATA, Constants.TABLE_HEADER);
        // Create database
        DBConnector database = new DBConnector();
        if(database.testConnection() != -1)
        {
            this.database = database;
        }
        
    }

    public Object[][] getDataFromDB(String searchColumn, String searchTerm)
    {
        return database.getQuery(searchColumn, searchTerm);
    }
}
