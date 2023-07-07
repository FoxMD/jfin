package com.gui;

import com.core.Controller;
import com.gui.AddFrame.Caller;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
        JButton updateButton = new JButton("Modify");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        JPanel ctrlPanel = new JPanel(new GridLayout(rows, colmns, hGap, vGap));
        ctrlPanel.setMinimumSize(new Dimension(minHeight, minWidth));
        ctrlPanel.add(updateButton);
        ctrlPanel.add(addButton);
        ctrlPanel.add(removeButton);

        /**
         * Action listener for getting info from controller.
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton) e.getSource()).getActionCommand().equals("AddEntry")) {
                    new AddFrame(controller, Caller.ADD);
                }
                if (((JButton) e.getSource()).getActionCommand().equals("Remove")) {
                    System.out.println("From remove");
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null,
                            "Would You Like to remove the entry?", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        System.out.println(controller.removeEntryRequest());
                        controller.updateOverview();
                    }
                }
                if (((JButton) e.getSource()).getActionCommand().equals("Update")) {
                    System.out.println("From update");
                    new AddFrame(controller, Caller.MODIFY);
                }
            }
        };

        addButton.setName("AddEntry");
        addButton.setActionCommand("AddEntry");
        addButton.addActionListener(listener);

        updateButton.setActionCommand("Update");
        updateButton.addActionListener(listener);

        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(listener);

        return ctrlPanel;
    }
}
