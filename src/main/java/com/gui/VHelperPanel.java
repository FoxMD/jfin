package com.gui;

public class VHelperPanel {
    protected int width = 800;
    protected int height;

    VHelperPanel(int height)
    {
        this.height = height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    } 

    public void setWidth(int width)
    {
        this.width = width;
    }
}
