package com.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Action panel for sumarisation.
 */
public class SumActionPanel {
    private final int minWidth = 400;
    private final int minHeight = 35;
    private final int rows = 1;
    private final int colmns = 3;
    private final int vGap = 10;
    private final int hGap = 10;

    /**
     * Composer for sum panel.
     * @return JPanel
     */
    public JPanel sumActionPanelComposer() {
        JButton searchButton = new JButton("Nope");
        JButton addButton = new JButton("Nada");
        JButton removeButton = new JButton("Niet");

        JPanel ctrlPanel = new JPanel(new GridLayout(rows, colmns, hGap, vGap));
        ctrlPanel.setMinimumSize(new Dimension(minWidth, minHeight));
        ctrlPanel.setPreferredSize(new Dimension(minWidth, minHeight));
        ctrlPanel.add(searchButton);
        ctrlPanel.add(addButton);
        ctrlPanel.add(removeButton);

        return ctrlPanel;
    }
}
