package com.core;

import com.gui.GraphPanel;
import com.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
 
public class Controller implements ActionListener {
     
    private JTextField searchTermTextField = new JTextField(26);
    private FinanceModel model;
    private FinanceModel modelOverview;
    private GraphPanel graph;
    private JFrame frame;
    //private DBConnector database;

    private String searchColumn = "Month";
    private String searchYear = "";
    private String searchMonth = "";
    
    public void setFilterOption(String filterOption)
    {
        this.searchColumn = filterOption;
    }

    public void setOverviewDetails(String year, String month)
    {
        this.searchYear = year;
        this.searchMonth = month;
    }
 
    public Controller(FinanceModel model_db, FinanceModel model_sum, GraphPanel graph, JFrame frame) 
    {
        super();
        this.model = model_db;
        this.modelOverview = model_sum;
        this.frame = frame;
        this.graph = graph;
    }

    public void setFilterTextField(JTextField searchTermTextField)
    {
        this.searchTermTextField = searchTermTextField;
    }
    private int i = 0;
    @Override
    public void actionPerformed(ActionEvent e) {      
        String searchTerm = searchTermTextField.getText();
        if(((JButton)e.getSource()).getActionCommand() == "Update")
        {            
            System.out.println("Info od update");
            Object[][] data = ((FinanceModel)modelOverview).getDataForSpecificDate(searchYear, searchMonth);

            graph.clearChart();
            modelOverview.setDataVector(data, Constants.TABLE_HEADER);
            modelOverview.setChartValues(i++);
            graph.updateChart(modelOverview);
            frame.validate();
            frame.repaint();
        }

        if(((JButton)e.getSource()).getActionCommand() == "Filter")
        {            
            System.out.println("Info od filtru");
        
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
}