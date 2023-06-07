package com.gui;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;


public class GraphPanel extends JPanel {
    public JPanel graphPanelComposer()
    {
        final PieChart chart = new PieChartBuilder().width(800).height(600).title("My Pie Chart").theme(ChartTheme.GGPlot2).build();
        // Customize Chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.7);
        chart.getStyler().setStartAngleInDegrees(90);
    
        // Series
        chart.addSeries("Prague", 2);
        chart.addSeries("Dresden", 4);
        chart.addSeries("Munich", 34);
        chart.addSeries("Hamburg", 22);
        chart.addSeries("Berlin", 29);
                
        JPanel panel = new XChartPanel<PieChart>(chart);
        return panel;
    }
}