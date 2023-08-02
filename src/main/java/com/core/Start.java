package com.core;

import javax.swing.SwingUtilities;

import com.connector.Credentials;
import com.connector.DBConnector;
import com.connector.IDatabase;
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
                    Credentials loginData = new Credentials();
                    createDependenciesAndShowGUI(loginIntoDatabase(loginData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static IDatabase loginIntoDatabase(Credentials loginData) {
        DBConnector database = DBConnector.getInstance(loginData);
        return database;
    }

    /**
     * Create UI and start the app.
     * @throws Exception For not starting
     */
    public static void createDependenciesAndShowGUI(IDatabase database) throws Exception {
        System.out.println("Create GUI");
        if (database.testConnection() != -1) {
            FinanceModel modelDB = new FinanceModel(database);
            FinanceModel modelSum = new FinanceModel(database);
            Controller controller = new Controller(modelDB, modelSum);

            new View(modelDB, modelSum, controller);
        }
    }
}
