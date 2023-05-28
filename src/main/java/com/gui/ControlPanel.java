package com.gui;

import com.model.*;
import com.core.*;
import com.connector.*;

import java.awt.Dimension;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;

public class ControlPanel {
    public JPanel controlPanelComposer(Controller controller)
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
