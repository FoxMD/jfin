package com.core;

import javax.swing.SwingUtilities;
import com.gui.View;

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
                    createAndShowGUI();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create UI and start the app.
     * @throws Exception For not starting
     */
    public static void createAndShowGUI() throws RuntimeException {
        System.out.println("Create GUI");
        new View();
    }
}
