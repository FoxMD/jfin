package com.gui;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;

import com.model.FinanceModel;

public class GraphPanel extends JPanel {
    private PieChart chart = new PieChartBuilder().width(800).height(600).title("My Pie Chart").theme(ChartTheme.GGPlot2).build();
    private int i = 2;
    private FinanceModel modelOverview;

    public JPanel graphPanelComposer(FinanceModel modelOverview)
    {
        this.modelOverview = modelOverview;
        // Customize Chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.7);
        chart.getStyler().setStartAngleInDegrees(90);
                  
        //final SwingWrapper<PieChart> sw = new SwingWrapper<PieChart>(chart);
        JPanel panel = new XChartPanel<PieChart>(chart);
        return panel;
    }

    public void updateChart(FinanceModel dataModel)
    {
        i = dataModel.getChartValues();
                // Series
        chart.addSeries("Prague", this.modelOverview.getTest());
        chart.addSeries("Dresden", 4);
        chart.addSeries("Munich", 34);
        chart.addSeries("Hamburg", 22);
        chart.addSeries("Berlin", 29);    
    }

    public void clearChart()
    {
        chart.removeSeries("Prague");
        chart.removeSeries("Dresden");
        chart.removeSeries("Munich");
        chart.removeSeries("Hamburg");
        chart.removeSeries("Berlin");
        
    }
}