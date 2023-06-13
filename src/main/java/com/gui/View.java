package com.gui;

import com.core.Controller;
import com.model.FinanceModel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

/**
 * View of the MVC model.
 */
public class View {
    private final int dividerLocation = 150;
    private final int minWidth = 1200;
    private final int prefWidth = 1400;
    private final int minHeight = 600;
    private final int prefHeight = 800;
    private final int helperBarHeight = 35;
    private final int overviewHeight = 400;
    private final int databaseHeight = 200;
    private final static int scrollPanelWidth = 800;

    /**
     * Constructor for the MVC.
     */
    public View() {
        // Create views swing UI components
        JTable table = new JTable();
        JTable summary = new JTable();
        JFrame frame = new JFrame("Finance");

        // Create table model
        FinanceModel modelDB = new FinanceModel();
        table.setModel(modelDB);

        FinanceModel modelSum = new FinanceModel();
        summary.setModel(modelSum);

        // Set the graph pannel
        GraphPanel gPanel = new GraphPanel();
        JPanel graphPanel = gPanel.graphPanelComposer(modelSum);

        // Create controller
        Controller controller = new Controller(modelDB, modelSum, gPanel, frame);

        // Set the view layout - Control Panel, Buttons
        ControlPanel cPanel = new ControlPanel();
        JPanel ctrlPanel = cPanel.controlPanelComposer(controller);

        // Set the search pannel
        SearchPanel sPanel = new SearchPanel(helperBarHeight);
        JPanel searchPanel = sPanel.searchPanelComposer(controller);

        // Set the selector pannel
        SelectorPanel slPanel = new SelectorPanel(helperBarHeight);
        JPanel selectorPanel = slPanel.selectorPanelComposer(controller);

        // Set the database panel
        JScrollPane tableScrollPanel = new JScrollPane(table);
        tableScrollPanel = stylingDatabasePanel(tableScrollPanel,
                    "Database - Test feedback", databaseHeight);

        // Set the summary pannel
        JScrollPane tableSummaryScrollPanel = new JScrollPane(summary);
        tableSummaryScrollPanel = stylingDatabasePanel(tableSummaryScrollPanel,
                    "Summary - Test feedback", overviewHeight);

        // Set the sum action panel
        SumActionPanel saPanel = new SumActionPanel();
        JPanel sumActPanel = saPanel.sumActionPanelComposer();

        // Split Layouts
        // Set the filter summary panel
        JSplitPane splitVerticalPanelSummary = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    selectorPanel, tableSummaryScrollPanel);
        splitVerticalPanelSummary.setEnabled(false);

        JSplitPane splitVerticalPanelDatabase = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    searchPanel, tableScrollPanel);
        splitVerticalPanelDatabase.setEnabled(false);

        JSplitPane splitHorizontalPanelSummary = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    splitVerticalPanelSummary, graphPanel);
        splitHorizontalPanelSummary.setEnabled(false);

        JSplitPane splitHorizontalPanelDatabase = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    splitVerticalPanelDatabase, sumActPanel);
        splitHorizontalPanelDatabase.setEnabled(false);

        JSplitPane splitVerticalFramePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    splitHorizontalPanelSummary, splitHorizontalPanelDatabase);
        splitVerticalFramePanel.setEnabled(false);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    ctrlPanel, splitVerticalFramePanel);
        splitPanel.setDividerLocation(dividerLocation);
        splitPanel.setEnabled(false);

        // Display it all in a scrolling window and make the window appear
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(minWidth, minHeight));
        frame.setPreferredSize(new Dimension(prefWidth, prefHeight));
        frame.add(splitPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JScrollPane stylingDatabasePanel(JScrollPane tableScrollPanel, String title, int height) {
        tableScrollPanel.setMinimumSize(new Dimension(scrollPanelWidth, height));
        tableScrollPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title,
                TitledBorder.CENTER, TitledBorder.TOP));

        return tableScrollPanel;
    }
}
