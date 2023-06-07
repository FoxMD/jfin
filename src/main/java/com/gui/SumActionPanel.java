package com.gui;

import com.core.*;

import java.awt.GridLayout;
import java.awt.Dimension;
 
import javax.swing.JButton;
import javax.swing.JPanel;

public class SumActionPanel {
    public JPanel sumActionPanelComposer()
    {
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add entry");
        JButton removeButton = new JButton("Remove entry");

        JPanel ctrlPanel = new JPanel(new GridLayout(8, 1, 10, 10));
        ctrlPanel.setMinimumSize(new Dimension(120, 500));
        ctrlPanel.add(searchButton);
        ctrlPanel.add(addButton);
        ctrlPanel.add(removeButton);

        return ctrlPanel;
    }
}
