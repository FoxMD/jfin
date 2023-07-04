package com.core;

import com.gui.GraphPanel;
import com.model.Constants;
import com.model.FinanceModel;
import com.model.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Controller for the MVC.
 */
public class Controller implements ActionListener {
    private JTextField searchTermTextField = new JTextField(26);
    private FinanceModel model;
    private FinanceModel modelOverview;
    private GraphPanel graph;
    private JFrame frame;

    private String searchColumn = "Month";
    private String searchYear = "";
    private String searchMonth = "";
    private Object[] valuesToAdd = new Object[6];

    /**
    * Contructor for the MVC controller.
    * @param modelDB db pointer
    * @param modelSum filtered db pointer (year and month)
    * @param graph graph panel for refresh
    * @param frame frame panel for repaint
    */
    public Controller(FinanceModel modelDB, FinanceModel modelSum, GraphPanel graph, JFrame frame) {
        super();
        this.model = modelDB;
        this.modelOverview = modelSum;
        this.frame = frame;
        this.graph = graph;
    }

    /**
     * Search for a specific string.
     * @param filterOption search term
     */
    public void setFilterOption(String filterOption) {
        this.searchColumn = filterOption;
    }

    /**
     * Sets the year and motnh option.
     * @param year year
     * @param month month
     */
    public void setOverviewDetails(String year, String month) {
        this.searchYear = year;
        this.searchMonth = month;
    }

    /**
     * Sets the filter for searching term.
     * @param searchTermTextField search term
     */
    public void setFilterTextField(JTextField searchTermTextField) {
        this.searchTermTextField = searchTermTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchTermTextField.getText();
        if (((JButton) e.getSource()).getActionCommand().equals("Update")) {
            System.out.println("Info od update");
            Object[][] data = ((FinanceModel) modelOverview).getDataForSpecificDate(searchYear, searchMonth);

            graph.clearChart();
            modelOverview.setDataVector(data, Constants.TABLE_HEADER);
            //modelOverview.setChartValues(++i);
            graph.updateChart(modelOverview);
            frame.validate();
            frame.repaint();
        }

        if (((JButton) e.getSource()).getActionCommand().equals("Filter")) {
            System.out.println("Info od filtru");
            actionOnFilterButton(searchTerm);
        }

        if (((JButton) e.getSource()).getActionCommand().equals("AddEntry")) {
            System.out.println("Update table");
            for (Object s: this.valuesToAdd) {
                System.out.println(s);
            }
            model.addEntryToDB((String) this.valuesToAdd[Utils.YEAR],
                (String) this.valuesToAdd[Utils.MONTH],
                (String) this.valuesToAdd[Utils.TYPE],
                Float.parseFloat((String) this.valuesToAdd[Utils.VALUE]),
                (String) this.valuesToAdd[Utils.CURRENCY],
                (String) this.valuesToAdd[Utils.DESC]
            );
        }
    }

    /**
     * Filter button handler.
     * @param searchTerm What is you searching for.
     */
    private void actionOnFilterButton(String searchTerm) {
        Object[][] data = ((FinanceModel) model).getDataFromDB(searchColumn, searchTerm);

        if (!(searchTerm != null && !"".equals(searchTerm))) {
            JOptionPane.showMessageDialog(null, "Search term is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Constants.databaseData = data;
        int position = Constants.getColumnPosition(searchColumn);
        Object[][] newData = new Object[Constants.databaseData.length][];
        int idx = 0;

        for (Object[] o: Constants.databaseData) {
            if ("*".equals(searchTerm.trim())) {
                newData[idx++] = o;
            } else if ((String.valueOf(o[position]).toUpperCase()).startsWith(searchTerm.toUpperCase().trim())) {
                newData[idx++] = o;
            }
        }
        model.setDataVector(newData, Constants.TABLE_HEADER);
    }

    public void setValuesFromFormular(Object[] valuesFromForm) {
        valuesToAdd = valuesFromForm;
    }
}
