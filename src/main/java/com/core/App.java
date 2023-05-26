package com.core;

import com.gui.*;
import javax.swing.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println("Program started");
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
    }

    public static void createAndShowGUI() throws Exception {
        System.out.println("Create GUI");
        new View();
    }
}
