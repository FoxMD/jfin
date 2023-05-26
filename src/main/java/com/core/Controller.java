package com.core;

import com.connector.DBConnector;
import com.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
public class Controller implements ActionListener {
     
    private JTextField searchTermTextField = new JTextField(26);
    private DefaultTableModel model;
    private DBConnector database;
 
    public Controller(JTextField searchTermTextField, DefaultTableModel model, DBConnector database) {
        super();
        this.database = database;
        this.searchTermTextField = searchTermTextField;
        this.model = model;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        String searchTerm = searchTermTextField.getText();
        database.testConnection();

        if (searchTerm != null && !"".equals(searchTerm)) {
            Object[][] newData = new Object[Constants.DATA.length][];
            int idx = 0;
            for (Object[] o: Constants.DATA) {
                if ("*".equals(searchTerm.trim())) {
                    newData[idx++] = o;
                } else {
                    if(String.valueOf(o[0]).startsWith(searchTerm.toUpperCase().trim())){
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