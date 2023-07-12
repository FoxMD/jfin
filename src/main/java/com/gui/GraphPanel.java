package com.gui;

import com.model.FinanceModel;

import java.util.Map;

import javax.swing.JPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;

/**
 * Graph with overview.
 */
public class GraphPanel extends JPanel {
    private final int graphWidth = 800;
    private final int graphHeight = 600;
    private final float contentSize = 0.7f;
    private final int angleOfRotation = 90;
    private Map<String, Float> values;

    private PieChart chart = new PieChartBuilder()
                        .width(graphWidth)
                        .height(graphHeight)
                        .title("My Pie Chart")
                        .theme(ChartTheme.GGPlot2)
                        .build();
    private FinanceModel modelOverview;

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

        JPanel panel = new XChartPanel<PieChart>(chart);
        return panel;
    }

    /**
     * Update values in chart.
     * @param dataModel Data
     */
    public void updateChart(FinanceModel dataModel) {
        for (Map.Entry<String, Float> entry : this.values.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }
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
}
