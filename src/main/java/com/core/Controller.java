package com.core;

import com.connector.DBConnector;
import com.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
 
public class Controller implements ActionListener {
     
    private JTextField searchTermTextField = new JTextField(26);
    private DefaultTableModel model;
    private DBConnector database;

    private String searchColumn = "month";

    public void setFilterOption(String filterOption)
    {
        this.searchColumn = filterOption;
    }
 
    public Controller(DefaultTableModel model, DBConnector database) {
        super();
        this.database = database;
        //this.searchTermTextField = searchTermTextField;
        this.model = model;
    }

    public void setFilterTextField(JTextField searchTermTextField)
    {
        this.searchTermTextField = searchTermTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JButton source = (JButton)e.getSource();

        
        String searchTerm = searchTermTextField.getText();
        if(((JButton)e.getSource()).getActionCommand() == "Filter month")
        {            
            System.out.println("Info od filtru");
            //searchColumn = "month";
        }
        Object[][] data = database.getQuery(searchColumn, searchTerm);

        Constants.DB_DATA = data;
        if (searchTerm != null && !"".equals(searchTerm)) {
            Object[][] newData = new Object[Constants.DB_DATA.length][];
            int idx = 0;
            for (Object[] o: Constants.DB_DATA) {
                if ("*".equals(searchTerm.trim())) {
                    newData[idx++] = o;
                } else {
                    if((String.valueOf(o[0]).toUpperCase()).startsWith(searchTerm.toUpperCase().trim())){
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