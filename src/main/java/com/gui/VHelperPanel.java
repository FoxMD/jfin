package com.gui;

/**
 * VHelper abstract class.
 */
public class VHelperPanel {
    /**
     * Dimension variables.
     */
    protected int width;
    protected int height;
    private final int DEFAULT_WIDTH = 800;

    /**
     * Constructor with variable height.
     * @param height height
     */
    VHelperPanel(int height) {
        this.height = height;
        this.width = DEFAULT_WIDTH;
    }

    /**
     * Set the height of the panel.
     * @param height height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the panle width.
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
