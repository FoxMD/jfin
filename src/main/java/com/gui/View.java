package com.gui;

import com.model.FinanceModel;
import com.core.Controller;

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
        JFrame frame = new JFrame("Finance");

        // Create table model
        FinanceModel model_db = new FinanceModel();
        table.setModel(model_db);
        
        FinanceModel model_sum = new FinanceModel();
        summary.setModel(model_sum);
        
        // Set the graph pannel
        GraphPanel gPanel = new GraphPanel();
        JPanel graphPanel = gPanel.graphPanelComposer(model_sum);

        // Create controller
        Controller controller = new Controller(model_db, model_sum, gPanel, frame);
        
        // Set the view layout - Control Panel, Buttons
        ControlPanel cPanel = new ControlPanel();
        JPanel ctrlPanel = cPanel.controlPanelComposer(controller);
        
        // Set the search pannel
        SearchPanel sPanel = new SearchPanel(35);
        JPanel searchPanel = sPanel.searchPanelComposer(controller);
        
        // Set the selector pannel
        SelectorPanel slPanel = new SelectorPanel(35);
        JPanel selectorPanel = slPanel.selectorPanelComposer(controller);
        
        // Set the database panel
        JScrollPane tableScrollPanel = new JScrollPane(table);
        tableScrollPanel = stylingDatabasePanel(tableScrollPanel, "Database - Test feedback", 200);
        
        // Set the summary pannel
        JScrollPane tableSummaryScrollPanel = new JScrollPane(summary);
        tableSummaryScrollPanel = stylingDatabasePanel(tableSummaryScrollPanel, "Summary - Test feedback", 400);
        
        
        // Set the sum action panel
        SumActionPanel saPanel = new SumActionPanel();
        JPanel sumActPanel = saPanel.sumActionPanelComposer();
        
        // Split Layouts
        // Set the filter summary panel
        JSplitPane splitVerticalPanelSummary = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectorPanel, tableSummaryScrollPanel);
        splitVerticalPanelSummary.setEnabled(false);
        
        JSplitPane splitVerticalPanelDatabase = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, tableScrollPanel);
        splitVerticalPanelDatabase.setEnabled(false);
        
        JSplitPane splitHorizontalPanelSummary = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitVerticalPanelSummary, graphPanel);
        splitHorizontalPanelSummary.setEnabled(false);
        
        JSplitPane splitHorizontalPanelDatabase = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitVerticalPanelDatabase, sumActPanel);
        splitHorizontalPanelDatabase.setEnabled(false);
        
        JSplitPane splitVerticalFramePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitHorizontalPanelSummary, splitHorizontalPanelDatabase);
        splitVerticalFramePanel.setEnabled(false);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ctrlPanel, splitVerticalFramePanel);
        splitPanel.setDividerLocation(150);
        splitPanel.setEnabled(false);
 
        // Display it all in a scrolling window and make the window appear
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
