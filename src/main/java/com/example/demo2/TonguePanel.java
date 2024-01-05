package com.example.demo2;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TonguePanel extends JPanel {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public TonguePanel(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("tongue created");
        super.paintComponent(g);
        g.drawLine(this.x1,this.y1, this.x2, this.y2);
        this.setOpaque(false);
    };

}
