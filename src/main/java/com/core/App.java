package com.core;

import com.gui.*;
import com.model.*;

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
                    createAndShowGUI();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        DBConnector database = new DBConnector();
        database.testConnection();



        System.out.println("Hello World!");
    }

    public static void createAndShowGUI() throws Exception {
        new View();
    }
}
