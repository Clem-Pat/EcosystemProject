package com.example.demo2;

import javafx.application.Application;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;

public class GamePanel extends JPanel {
    public final Image image;
    public Graphics g;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;


    // Constructor
    public GamePanel(PondApplication pond) {
        Image image = null;
        final URL imageURL = PondApplication.class.getResource("/com/example/demo2/pond.jpg");
        if (imageURL != null) {
            image = ((new ImageIcon(imageURL)).getImage()).getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), java.awt.Image.SCALE_SMOOTH);
        }
        GameFrame frame = (GameFrame) pond.frame;
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
    public boolean contains(GameButton button) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component == button) {
                return true;
            }
        }
        return false;
    }

}
