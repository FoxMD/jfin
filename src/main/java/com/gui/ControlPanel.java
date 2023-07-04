package com.gui;

import com.core.Controller;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Control panel for buttons.
 */
public class ControlPanel {
    private final int minWidth = 500;
    private final int minHeight = 120;
    private final int rows = 8;
    private final int colmns = 1;
    private final int vGap = 10;
    private final int hGap = 10;

    /**
     * Composer for the button pannel.
     * @param controller MVC Controller
     * @return JPanel
     */
    public JPanel controlPanelComposer(Controller controller) {
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add entry");
        JButton removeButton = new JButton("Remove entry");

        JPanel ctrlPanel = new JPanel(new GridLayout(rows, colmns, hGap, vGap));
        ctrlPanel.setMinimumSize(new Dimension(minHeight, minWidth));
        ctrlPanel.add(searchButton);
        ctrlPanel.add(addButton);
        ctrlPanel.add(removeButton);

        /**
         * Action listener for getting info from controller.
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame(controller);
            }
        };

        addButton.setName("AddEntry");
        addButton.setActionCommand("AddEntry");
        addButton.addActionListener(listener);

        return ctrlPanel;
    }
}
