package com.gui;

//import com.core.Controller;
import com.model.FinanceModel;

import javax.swing.JFrame;

//mport org.junit.jupiter.api.Test;

public class AddTest {
    FinanceModel modelDB = new FinanceModel();
    FinanceModel modelSum = new FinanceModel();
    GraphPanel graph = new GraphPanel();
    JFrame frame = new JFrame();
    /*
    @Test
    public void addFrameTest() {
        Controller cnt = new Controller(modelDB, modelSum, graph, frame);
        AddFrame af = new AddFrame(cnt);
    }

    @Test
    public void chartTest() {
        GraphPanel cp = new GraphPanel();
        cp.graphPanelComposer(modelDB);
        cp.updateChart(modelDB);
        cp.clearChart();
    }
    */
}
