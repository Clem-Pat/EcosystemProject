package com.example.demo2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GamePanel extends JPanel {
    private Image image;
    public Graphics g;
    List<Circle> circles = new ArrayList<>();

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
//        for (Circle circle : circles) {
//            circle.draw(g);
//        }
    }
    public void addCircle(Circle circle) {
        circles.add(circle);
        revalidate();
        repaint();
    }

    public void moveCircle(Circle circle, int x, int y){
        Circle newCircle = new Circle(x, y, circle.radius);
        circles.remove(circle);
        removeAll();
        revalidate();
        repaint();
        addCircle(newCircle);
    }

    static class Circle {
        int x, y, radius;
        public Circle(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
        public void draw(Graphics g) {
            g.drawOval(x-radius/2, y-radius/2, 2*radius, 2*radius);
        }
    }
}
