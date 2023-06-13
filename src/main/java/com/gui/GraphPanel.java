package com.gui;

import com.model.FinanceModel;
import javax.swing.JPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler.AnnotationType;

/**
 * Graph with overview.
 */
public class GraphPanel extends JPanel {
    private final int graphWidth = 800;
    private final int graphHeight = 600;
    private final float anotationDst = 1.15f;
    private final float contentSize = 0.7f;
    private final int angleOfRotation = 90;

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
        chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(anotationDst);
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
        final int someValue1 = 4;
        final int someValue2 = 34;
        final int someValue3 = 22;
        final int someValue4 = 29;

        chart.addSeries("Prague", this.modelOverview.getTest());
        chart.addSeries("Dresden", someValue1);
        chart.addSeries("Munich", someValue2);
        chart.addSeries("Hamburg", someValue3);
        chart.addSeries("Berlin", someValue4);
    }

    /**
     * Clear chart before updating.
     */
    public void clearChart() {
        chart.removeSeries("Prague");
        chart.removeSeries("Dresden");
        chart.removeSeries("Munich");
        chart.removeSeries("Hamburg");
        chart.removeSeries("Berlin");
    }
}
