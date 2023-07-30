package com.gui;

import com.model.FinanceModel;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;

/**
 * Graph with overview.
 */
public class GraphPanel extends JPanel {
    private final int graphWidth = 400;
    private final int graphHeight = 300;
    private final float contentSize = 0.7f;
    private final int angleOfRotation = 90;

    private final int panelWidth = 400;
    private final int panelHeight = 600;

    private Map<String, Float> values;

    private PieChart chart = new PieChartBuilder()
                        .width(graphWidth)
                        .height(graphHeight)
                        .title("My Pie Chart")
                        .theme(ChartTheme.GGPlot2)
                        .build();
    private FinanceModel modelOverview;
    private final String[] columnNames = {"Article", "Cost"};
    private JTable textTest;
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    /**
     * Compose the graph.
     * @param modelOverview model for data.
     * @return JPanel
     */
    public JPanel graphPanelComposer(FinanceModel modelOverview) {
        this.modelOverview = modelOverview;

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotContentSize(contentSize);
        chart.getStyler().setStartAngleInDegrees(angleOfRotation);

        JPanel sup = new JPanel();
        JPanel panel = new XChartPanel<PieChart>(chart);

        textTest = new JTable(tableModel);
        textTest.setPreferredSize(new Dimension(panelWidth, panelHeight));
        sup.add(panel);
        sup.add(textTest);

        sup.setPreferredSize(new Dimension(panelWidth, panelHeight));

        return sup;
    }

    /**
     * Update values in chart.
     * @param dataModel Data
     */
    public void updateChart(FinanceModel dataModel) {
        for (Map.Entry<String, Float> entry : this.values.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }
        updateTable();
    }

    /**
     * Clear chart before updating.
     */
    public void clearChart() {
        if (this.values == null) {
            return;
        }
        for (String key : this.values.keySet()) {
            chart.removeSeries(key);
        }
        this.values.clear();
    }

    public void setValues(Map<String, Float> values) {
        this.values = values;
    }

    public Map<String, Float> getValues() {
        return this.values;
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, Float> entry : modelOverview.getTest().entrySet()) {
            Object[] objs = {entry.getKey(), entry.getValue()};
            tableModel.addRow(objs);
        }
    }
}
