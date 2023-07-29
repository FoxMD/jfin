package com.core;

import javax.swing.SwingUtilities;

import com.connector.DBConnector;
import com.gui.View;
import com.model.FinanceModel;

/**
 * Start of the program.
 */
public final class Start {
    /**
     * Constructor of start.
     */
    private Start() {

    }

    /**
     * Main function - heart of the program.
     * @param args args
     */
    public static void main(String[] args) {
        System.out.println("Program started");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createDependenciesAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create UI and start the app.
     * @throws Exception For not starting
     */
    public static void createDependenciesAndShowGUI() throws Exception {
        System.out.println("Create GUI");
        DBConnector database = DBConnector.getInstance();
        if (database.testConnection() != -1) {
            FinanceModel modelDB = new FinanceModel(database);
            FinanceModel modelSum = new FinanceModel(database);
            Controller controller = new Controller(modelDB, modelSum);

            new View(modelDB, modelSum, controller);
        }
    }
}
