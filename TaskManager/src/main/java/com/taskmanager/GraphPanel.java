package com.taskmanager;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {
    public static int dataArrayLenth = 60; // count of points on graph. (60 points - 60 seconds for full graph update)
    public int WIDTH;
    public int HEIGHT;
    public Graphics2D G2D; // main drawing obj
    public Color graphColor = Color.RED; // default color
    public int[] collectedData = new int[dataArrayLenth]; // array with all datas

    public GraphPanel() {
        for (int i = 0; i < dataArrayLenth; i++) collectedData[i] = 0; // default values on graph - 0
    }

    @Override
    protected void paintComponent(Graphics g) { // for graphic update
        super.paintComponent(g);
        G2D = (Graphics2D) g;

        this.WIDTH = getWidth();
        this.HEIGHT = getHeight();

        // init main graph
        this.G2D.setColor(this.graphColor);
        this.G2D.setStroke(new BasicStroke(2));
        this.drawBorder();
        this.drawGraphData();
    }

    public void drawBorder() {
        this.G2D.drawLine(0, 0, this.WIDTH, 0);
        this.G2D.drawLine(0, this.HEIGHT, this.WIDTH, this.HEIGHT);
        this.G2D.drawLine(0, 0, 0, this.HEIGHT);
        this.G2D.drawLine(this.WIDTH, 0, this.WIDTH, this.HEIGHT);
    }

    public void addNewValue(int value) { // adding new value at the end of array
        for (int i = 0; i < dataArrayLenth - 1; i++) {
            this.collectedData[i] = this.collectedData[i + 1];
        }

        this.collectedData[dataArrayLenth - 1] = value;
    }

    public void drawGraphData() {
        int border = 4; // borders for better look
        int[] xPoints = new int[dataArrayLenth];
        int[] yPoints = new int[dataArrayLenth];

        for (int dataIndex = 0; dataIndex < dataArrayLenth; dataIndex++) {
            int data = this.collectedData[dataIndex];

            // calculate point`s cords
            xPoints[dataIndex] = border + (this.WIDTH - 2 * border) * dataIndex / (dataArrayLenth - 1);
            yPoints[dataIndex] = this.HEIGHT - border - (((this.HEIGHT - 2 * border) * data) / 100);
        }

        this.G2D.drawPolyline(xPoints, yPoints, dataArrayLenth); // draw line of graph using points
    }
}