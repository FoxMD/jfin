package com.gui;

import javax.swing.JPanel;

import com.core.*;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;

import java.awt.event.KeyEvent;

public class SearchPanel extends VHelperPanel{
    
    SearchPanel(int height)
    {
        super(height);
    }

    public JPanel searchPanelComposer(Controller controller)
    {
        JPanel searchPanel = new JPanel();
        searchPanel.setMinimumSize(new Dimension(width, height));

        JButton filterButton = new JButton("Filter month");
        JTextField searchTermTextField = new JTextField(26);

        JRadioButton monthButton = new JRadioButton("Month");
        monthButton.setMnemonic(KeyEvent.VK_M);
        monthButton.setActionCommand("Month");
        monthButton.setSelected(true);

        JRadioButton yearButton = new JRadioButton("Year");
        monthButton.setMnemonic(KeyEvent.VK_Y);
        yearButton.setActionCommand("Year");

        JRadioButton typeButton = new JRadioButton("Type");
        monthButton.setMnemonic(KeyEvent.VK_T);
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
        typeButton.addActionListener(controller);
        monthButton.addActionListener(controller);
        yearButton.addActionListener(controller);

        controller.setFilterTextField(searchTermTextField);
        
        return searchPanel;
    }
}
