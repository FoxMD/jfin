package com.core;

import com.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.*;
 
public class Controller implements ActionListener {
     
    private JTextField searchTermTextField = new JTextField(26);
    private FinanceModel model;
    //private DBConnector database;

    private String searchColumn = "Month";

    public void setFilterOption(String filterOption)
    {
        this.searchColumn = filterOption;
    }
 
    public Controller(FinanceModel model) {
        super();
        this.model = model;
    }

    public void setFilterTextField(JTextField searchTermTextField)
    {
        this.searchTermTextField = searchTermTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {      
        String searchTerm = searchTermTextField.getText();
        if(((JButton)e.getSource()).getActionCommand() == "Filter")
        {            
            System.out.println("Info od filtru");
        }
        Object[][] data = ((FinanceModel)model).getDataFromDB(searchColumn, searchTerm);

        Constants.DB_DATA = data;
        int position = Constants.getColumnPosition(searchColumn);
        if (searchTerm != null && !"".equals(searchTerm)) {
            Object[][] newData = new Object[Constants.DB_DATA.length][];
            int idx = 0;
            for (Object[] o: Constants.DB_DATA) {
                if ("*".equals(searchTerm.trim())) {
                    newData[idx++] = o;
                } else {
                    if((String.valueOf(o[position]).toUpperCase()).startsWith(searchTerm.toUpperCase().trim())){
                        newData[idx++] = o;
                    }   
                }   
            }
            model.setDataVector(newData, Constants.TABLE_HEADER);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Search term is empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

 
}