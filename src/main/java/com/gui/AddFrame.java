package com.gui;

import com.core.Controller;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFrame {
    private final int minWidth = 400;
    private final int minHeight = 120;
    private final int rows = 7;
    private final int colmns = 2;
    private final int vGap = 3;
    private final int hGap = 3;

    private String year = "";
    private String month = "";
    private String type = "";
    private String value = "";
    private String currency = "";
    private String description = "";
    /**
     * Composer for the button pannel.
     * @param controller MVC Controller
     * @return JPanel
     */
    public AddFrame(Controller controller) {
        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add");
        addButton.setName("AddEntry");
        addButton.setActionCommand("AddEntry");
        addButton.addActionListener(controller);

        JTextField jYear = new JTextField();
        JTextField jMonth = new JTextField();
        JTextField jType = new JTextField();
        JTextField jValue = new JTextField();
        JTextField jCurrency = new JTextField();
        JTextField jDescription = new JTextField();

        JLabel labelYear = new JLabel("Year: ");
        JLabel labelMonth = new JLabel("Month: ");
        JLabel labelType = new JLabel("Type: ");
        JLabel labelValue = new JLabel("Value: ");
        JLabel labelCurrency = new JLabel("Currency: ");
        JLabel labelDescription = new JLabel("Description: ");

        JPanel ctrlPanel = new JPanel(new GridLayout(rows, colmns, hGap, vGap));
        ctrlPanel.setMinimumSize(new Dimension(minHeight, minWidth));
        ctrlPanel.add(labelYear);
        ctrlPanel.add(jYear);
        ctrlPanel.add(labelMonth);
        ctrlPanel.add(jMonth);
        ctrlPanel.add(labelType);
        ctrlPanel.add(jType);
        ctrlPanel.add(labelValue);
        ctrlPanel.add(jValue);
        ctrlPanel.add(labelCurrency);
        ctrlPanel.add(jCurrency);
        ctrlPanel.add(labelDescription);
        ctrlPanel.add(jDescription);
        ctrlPanel.add(addButton);
        ctrlPanel.add(cancelButton);

        /**
         * Action listener for getting info from controller.
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] data = new Object[6];
                data[0] = jYear.getText();
                data[1] = jMonth.getText();
                data[2] = jType.getText();
                data[3] = jValue.getText();
                data[4] = jCurrency.getText();
                data[5] = jDescription.getText();

                controller.setValuesFromFormular(data);
            }
        };

        addButton.addActionListener(listener);

        JFrame addFrame = new JFrame("Add entry");
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setMinimumSize(new Dimension(minWidth, minHeight));
        addFrame.add(ctrlPanel);
        addFrame.pack();
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }
}
