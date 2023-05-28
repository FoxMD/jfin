package com.gui;

import com.model.*;
import com.core.*;
import com.connector.*;

import java.awt.Dimension;
 
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class View {
    public View() {
        // Create views swing UI components 

        JTable table = new JTable();
        JTable summary = new JTable();
 
        // Create table model
        FinanceModel model = new FinanceModel();
        table.setModel(model);

        // Create database
        DBConnector database = new DBConnector();
        database.testConnection();
 
        // Create controller
        Controller controller = new Controller(model, database);
         
        // Set the view layout - Control Panel, Buttons
        ControlPanel cPanel = new ControlPanel();
        JPanel ctrlPanel = cPanel.controlPanelComposer(controller);

        // Set the search pannel
        SearchPanel sPanel = new SearchPanel();
        JPanel searchPanel = sPanel.searchPanelComposer(controller);

        // Set the database panel
        JScrollPane tableScrollPanel = new JScrollPane(table);
        tableScrollPanel = stylingDatabasePanel(tableScrollPanel, "Database - Test feedback", 200);
                
        // Set the summary pannel
        JScrollPane tableSummaryScrollPanel = new JScrollPane(summary);
        tableSummaryScrollPanel = stylingDatabasePanel(tableSummaryScrollPanel, "Summary - Test feedback", 500);

        // Set the filter summary panel

        // Split Layouts
        JSplitPane splitVerticalPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, tableScrollPanel);
        splitVerticalPanel.setEnabled(false);

        JSplitPane splitVerticalFramePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableSummaryScrollPanel, splitVerticalPanel);
        splitVerticalFramePanel.setEnabled(false);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ctrlPanel, splitVerticalFramePanel);
        splitPanel.setDividerLocation(150);
        splitPanel.setEnabled(false);
 
        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("Finance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 600));
        frame.setPreferredSize(new Dimension(1400, 800));
        frame.add(splitPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JScrollPane stylingDatabasePanel(JScrollPane tableScrollPanel, String title, int height)
    {
        tableScrollPanel.setMinimumSize(new Dimension(800, height));
        tableScrollPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title,
                TitledBorder.CENTER, TitledBorder.TOP));

        return tableScrollPanel;
    } 
}
