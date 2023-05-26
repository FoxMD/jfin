package com.core;

import com.gui.MainFrame;
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
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();

        DBConnector database = new DBConnector();
        database.testConnection();

        System.out.println("Hello World!");
    }
}
