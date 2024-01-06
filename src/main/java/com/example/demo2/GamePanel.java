package com.example.demo2;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private final Image image;
    public Graphics g;

    // Constructor
    public GamePanel(Image image) {
        this.image = image;
        setBackground(Color.decode("#9ac23b"));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        if (this.image != null){
            this.image.getScaledInstance(this.getParent().getWidth(), this.getParent().getHeight(), java.awt.Image.SCALE_SMOOTH);
            int x = this.getParent().getWidth()/2 - this.image.getWidth(this)/2;
            int y = this.getParent().getHeight()/2 - this.image.getHeight(this)/2;
            g.drawImage(this.image,x,y,this);
        }
        else{
            g.drawOval(0, 0,  this.getParent().getWidth()/2, this.getParent().getHeight()/2);
        }
    }
}
