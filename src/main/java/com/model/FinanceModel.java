package com.model;

import javax.swing.table.DefaultTableModel;

public class FinanceModel extends DefaultTableModel{
    public FinanceModel()
    {
        super(Constants.DATA, Constants.TABLE_HEADER);
    }
}
