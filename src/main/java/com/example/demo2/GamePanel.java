package com.example.demo2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GamePanel extends JPanel {
    private final Image image;
    public Graphics g;

    // Constructor
    public GamePanel(GameFrame frame, Image image) {
        this.image = image;
        setBackground(Color.decode("#9ac23b"));
        // When we click on panel, we set the focus back to the frame so the keyboard event listener can work
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.toFront();
                frame.requestFocus();
            }
        });
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
