package com.gui;

import javax.swing.JPanel;

import com.core.*;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JButton filterButton = new JButton("Filter");
        JTextField searchTermTextField = new JTextField(26);

        JRadioButton monthButton = new JRadioButton("Month");
        monthButton.setMnemonic(KeyEvent.VK_M);
        monthButton.setActionCommand("Month");
        monthButton.setSelected(true);
        monthButton.setName("Month");

        JRadioButton yearButton = new JRadioButton("Year");
        yearButton.setMnemonic(KeyEvent.VK_Y);
        yearButton.setActionCommand("Year");
        yearButton.setName("Year");

        JRadioButton typeButton = new JRadioButton("Type");
        typeButton.setMnemonic(KeyEvent.VK_T);
        typeButton.setActionCommand("Type");
        typeButton.setName("Type");

        ButtonGroup group = new ButtonGroup();
        group.add(monthButton);
        group.add(yearButton);
        group.add(typeButton);

        searchPanel.add(monthButton);
        searchPanel.add(yearButton);
        searchPanel.add(typeButton);

        searchPanel.add(searchTermTextField);
        searchPanel.add(filterButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton btn = (JRadioButton) e.getSource();
                System.out.println("Selected Button = " + btn.getName());
                controller.setFilterOption(btn.getName());
            }
        };
       
        filterButton.addActionListener(controller);
        monthButton.addActionListener(listener);
        yearButton.addActionListener(listener);
        typeButton.addActionListener(listener);

        controller.setFilterTextField(searchTermTextField);
        
        return searchPanel;
    }
}
