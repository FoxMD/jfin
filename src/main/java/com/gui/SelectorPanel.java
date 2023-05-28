package com.gui;

import com.core.*;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class SelectorPanel {
    public JPanel selectorPanelComposer(Controller controller, int height)
    {
        String[] choicesMonth = { "January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October", "November", "December" };

        String[] choicesYear = { "2019", "2020", "2021", "2022",
        "2023", "2024" };

        final JComboBox<String> cbMonth = new JComboBox<String>(choicesMonth);
        final JComboBox<String> cbYear = new JComboBox<String>(choicesYear);

        JButton updateButton = new JButton("Update");

        JPanel selectorPanel = new JPanel();
        selectorPanel.setMinimumSize(new Dimension(1024, height));
        selectorPanel.add(cbMonth);
        selectorPanel.add(cbYear);
        selectorPanel.add(updateButton);

        return selectorPanel;
    }
}
