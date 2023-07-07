package com.core;

import com.gui.GraphPanel;
import com.gui.AddFrame.Caller;
import com.model.Constants;
import com.model.FinanceModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Controller for the MVC.
 */
public class Controller implements ActionListener {
    private JTextField searchTermTextField = new JTextField(26);
    private FinanceModel model;
    private FinanceModel modelOverview;
    private GraphPanel graph;
    private JFrame frame;
    private JTable summary;

    private String searchColumn = "Month";
    private String searchYear = "";
    private String searchMonth = "";
    private Object[] valuesToAdd = new Object[com.model.Utils.Entries.values().length];

    /**
    * Contructor for the MVC controller.
    * @param modelDB db pointer
    * @param modelSum filtered db pointer (year and month)
    * @param graph graph panel for refresh
    * @param frame frame panel for repaint
    */
    public Controller(FinanceModel modelDB, FinanceModel modelSum, GraphPanel graph, JFrame frame, JTable sum) {
        super();
        this.model = modelDB;
        this.modelOverview = modelSum;
        this.frame = frame;
        this.graph = graph;
        this.summary = sum;
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
            updateOverview();
        }

        if (((JButton) e.getSource()).getActionCommand().equals("Filter")) {
            System.out.println("Info od filtru");
            actionOnFilterButton(searchTerm);
        }

        if (((JButton) e.getSource()).getActionCommand().equals("AddEntry")) {
            System.out.println("Add table");
            model.addEntryToDB(this.valuesToAdd);
            updateOverview();
        }

        if (((JButton) e.getSource()).getActionCommand().equals("ModifyEntry")) {
            System.out.println("Update table");
            model.modifyEntryFromDB(this.valuesToAdd);
            updateOverview();
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

    public void updateOverview() {
        Object[][] data = ((FinanceModel) modelOverview).getDataForSpecificDate(searchYear, searchMonth);

        graph.clearChart();
        modelOverview.setDataVector(data, Constants.TABLE_HEADER);
        //modelOverview.setChartValues(++i);
        graph.updateChart(modelOverview);
        frame.validate();
        frame.repaint();
    }

    public void setValuesFromFormular(Object[] valuesFromForm) {
        valuesToAdd = valuesFromForm;
    }

    public Object[] getEntry() {
        Vector<Vector> table = modelOverview.getDataVector();
        int row = summary.getSelectedRow();
        System.out.println("Which row: " + row);
        if (row != -1) {
            Object[] entry = table.get(row).toArray();
            //model.removeEntryFromDB(entry);
            System.out.println(entry);
            return entry;
        }
        Object[] error = new Object[1];
        error[0] = "-1";
        return error;
    }

    /**
     * Remove an entry from DB.
     * @return Status.
     */
    public String removeEntryRequest() {
        Object[] entry = getEntry();
        if (entry[0].equals("-1")) {
            return "Error empty line";
        }
        model.removeEntryFromDB(entry);
        return "Jeeeej";
    }

    /**
     * Set the ID for mod od add function.
     * @param caller MODIFY/ADD.
     */
    public void setCallerIDforAddFrame(Caller caller) {
        System.out.println(caller.ordinal());
    }
}
