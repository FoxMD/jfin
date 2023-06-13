package com.gui;

import com.core.Controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * Panel for selection of year and month.
 */
public class SelectorPanel extends VHelperPanel {

    /**
     * Constructor.
     * @param height height
     */
    SelectorPanel(int height)
    {
        super(height);
    }

    /**
     * Composer of the JPanel.
     * @param controller controller who controls the actions.
     * @return JPanel type
     */
    public JPanel selectorPanelComposer(Controller controller) {
        String[] choicesMonth = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};

        String[] choicesYear = {"2019", "2020", "2021", "2022", "2023", "2024"};

        final JComboBox<String> cbMonth = new JComboBox<String>(choicesMonth);
        final JComboBox<String> cbYear = new JComboBox<String>(choicesYear);

        JButton updateButton = new JButton("Update");

        JPanel selectorPanel = new JPanel();
        selectorPanel.setMinimumSize(new Dimension(width, height));
        selectorPanel.add(cbMonth);
        selectorPanel.add(cbYear);
        selectorPanel.add(updateButton);

        /**
         * Action listener for getting info from controller.
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setOverviewDetails(cbYear.getSelectedItem().toString(), cbMonth.getSelectedItem().toString());
            }
        };

        updateButton.addActionListener(controller);
        updateButton.addActionListener(listener);

        return selectorPanel;
    }
}
