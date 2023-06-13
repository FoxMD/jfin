package com.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Action panel for sumarisation.
 */
public class SumActionPanel {
    private final int minWidth = 500;
    private final int minHeight = 120;
    private final int rows = 8;
    private final int colmns = 1;
    private final int vGap = 10;
    private final int hGap = 10;

    /**
     * Composer for sum panel.
     * @return JPanel
     */
    public JPanel sumActionPanelComposer() {
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add entry");
        JButton removeButton = new JButton("Remove entry");

        JPanel ctrlPanel = new JPanel(new GridLayout(rows, colmns, hGap, vGap));
        ctrlPanel.setMinimumSize(new Dimension(minHeight, minWidth));
        ctrlPanel.add(searchButton);
        ctrlPanel.add(addButton);
        ctrlPanel.add(removeButton);

        return ctrlPanel;
    }
}
