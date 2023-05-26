package com.core;

import com.gui.MainFrame;
import com.gui.TestView;
import com.model.FinanceModel;

import javax.swing.*;

import com.connector.DBConnector;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try
                {
                    MainFrame myFrame = new MainFrame();
                    myFrame.initialize("January");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        DBConnector database = new DBConnector();
        database.testConnection();

        FinanceModel model = new FinanceModel(); // Retrieve from DB
        TestView view = new TestView();
        Controller cnt = new Controller(model, view);

        cnt.updateView();

        cnt.setMonth("Python");
        System.out.println("nAfter updating, Details are as follows");
 
        cnt.updateView();

        System.out.println("Hello World!");
    }
}
