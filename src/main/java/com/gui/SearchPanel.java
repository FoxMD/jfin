package com.gui;

import javax.swing.JPanel;

import com.core.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;

public class SearchPanel extends JPanel{
    public JPanel searchPanelComposer(Controller controller, int height)
    {
        JPanel searchPanel = new JPanel();
        JButton filterButton = new JButton("Filter month");
        JTextField searchTermTextField = new JTextField(26);

        JRadioButton monthButton = new JRadioButton("Month");
        //monthButton.setMnemonic(KeyEvent.VK_B);
        monthButton.setActionCommand("Month");
        monthButton.setSelected(true);

        JRadioButton yearButton = new JRadioButton("Year");
        //monthButton.setMnemonic(KeyEvent.VK_B);
        yearButton.setActionCommand("Year");

        JRadioButton typeButton = new JRadioButton("Type");
        //monthButton.setMnemonic(KeyEvent.VK_B);
        typeButton.setActionCommand("Type");

        ButtonGroup group = new ButtonGroup();
        group.add(monthButton);
        group.add(yearButton);
        group.add(typeButton);

        searchPanel.add(monthButton);
        searchPanel.add(yearButton);
        searchPanel.add(typeButton);

        searchPanel.add(searchTermTextField);
        searchPanel.add(filterButton);
        filterButton.addActionListener(controller);

        controller.setFilterTextField(searchTermTextField);
        
        return searchPanel;
    }
}
