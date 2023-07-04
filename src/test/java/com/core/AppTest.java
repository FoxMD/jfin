package com.core;

import org.junit.jupiter.api.Test;

import com.gui.GraphPanel;
import com.model.FinanceModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Unit test for simple App.
 */
class AppTest {
    private final int size = 6;

    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        Start.main(null);
        assertEquals(1, 1);
    }

    /**
     * Controller test.
     */
    @Test
    public void controlTest() {
        FinanceModel modelDB = new FinanceModel();
        FinanceModel modelSum = new FinanceModel();
        GraphPanel graph = new GraphPanel();
        JFrame frame = new JFrame();

        JTextField testField = new JTextField("Filter");

        Controller cnt = new Controller(modelDB, modelSum, graph, frame);
        cnt.setFilterOption("Filter");
        cnt.setOverviewDetails("2666", "June");
        cnt.setFilterTextField(testField);

        Object[] testObj = new Object[size];
        cnt.setValuesFromFormular(testObj);
    }
}
