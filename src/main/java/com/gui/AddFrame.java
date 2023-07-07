package com.gui;

import com.core.Controller;
import com.model.Utils;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Popup frame for adding a value to DB.
 */
public class AddFrame {
    private final int minWidth = 400;
    private final int minHeight = 120;
    private final int rows = 7;
    private final int colmns = 2;
    private final int vGap = 3;
    private final int hGap = 3;

    private Object[] descObj;

    public enum Caller {ADD, MODIFY};

    /**
     * Constructor for the popup frame.
     * @param controller
     */
    public AddFrame(Controller controller, Caller call) {
        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add");
        addButton.setName("AddEntry");
        addButton.addActionListener(controller);
        cancelButton.setActionCommand("CancelAction");

        descObj = new Object[Utils.Entries.values().length];
        controller.setCallerIDforAddFrame(call);

        if(call == Caller.MODIFY) {
            addButton.setText("Modify");
            descObj = controller.getEntry();
            fillStrings(descObj);
            addButton.setActionCommand("ModifyEntry");
        } else {
            fillObject();
            addButton.setActionCommand("AddEntry");
        }

        JTextField jYear = new JTextField((String) descObj[Utils.Entries.YEAR.ordinal()]);
        JTextField jMonth = new JTextField((String) descObj[Utils.Entries.MONTH.ordinal()]);
        JTextField jType = new JTextField((String) descObj[Utils.Entries.TYPE.ordinal()]);
        JTextField jValue = new JTextField();
        if (call == Caller.MODIFY) {
            jValue.setText(Float.toString((Float) descObj[Utils.Entries.VALUE.ordinal()]));
        } else {
            jValue.setText((String) descObj[Utils.Entries.VALUE.ordinal()]);
        }
        JTextField jCurrency = new JTextField((String) descObj[Utils.Entries.CURRENCY.ordinal()]);
        JTextField jDescription = new JTextField((String) descObj[Utils.Entries.DESC.ordinal()]);

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

        JFrame addFrame = new JFrame("Add entry");

        /**
         * Action listener for getting info to the controller.
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton) e.getSource()).getActionCommand().equals("ModifyEntry")) {
                    controller.setOriginalEntry(descObj);
                }
                Object[] data = new Object[Utils.Entries.values().length];
                data[Utils.Entries.YEAR.ordinal()] = jYear.getText();
                data[Utils.Entries.MONTH.ordinal()] = jMonth.getText();
                data[Utils.Entries.TYPE.ordinal()] = jType.getText();
                data[Utils.Entries.VALUE.ordinal()] = jValue.getText();
                data[Utils.Entries.CURRENCY.ordinal()] = jCurrency.getText();
                data[Utils.Entries.DESC.ordinal()] = jDescription.getText();

                controller.setValuesFromFormular(data);
                addFrame.dispose();
            };
        };

        addButton.addActionListener(listener);
        cancelButton.addActionListener(listener);

        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setMinimumSize(new Dimension(minWidth, minHeight));
        addFrame.add(ctrlPanel);
        addFrame.pack();
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    private void fillStrings(Object[] obj) {
        descObj = obj;
    }

    private void fillObject() {
        for (int i = 0; i < Utils.Entries.values().length; i++) {
            descObj[i] = "";
        }
    }
}
