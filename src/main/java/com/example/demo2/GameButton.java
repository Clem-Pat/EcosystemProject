package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.net.URL;

public class GameButton extends JButton {

    private final PondObject obj;
    private final GamePanel.Circle attackCircle;
    public final GamePanel panel;
    public String name;
    public int x;
    public int y;
    public int height;
    public int width;
    public int imgWidth;
    public int imgHeight;
    public int attackRadius;
    public static final int DEFAULT_WIDTH = 70;
    public static final int DEFAULT_HEIGHT = 70;
    public static final int DEFAULT_IMG_WIDTH = 40;
    public static final int DEFAULT_IMG_HEIGHT = 40;

    public GameButton(PondObject obj, String imgName, int width, int height, int imgWidth, int imgHeight){
        this.obj = obj;
        this.name = obj.name;
        this.x = obj.x;
        this.y = obj.y;
        this.width = width;
        this.height = height;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.attackRadius = obj.attackRadius;
        this.panel = obj.pond.panel;
        setText(name);
        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(obj);
            }
        });
        URL imageURL = PondApplication.class.getResource("/com/example/demo2/" + imgName + ".png");
        if (imageURL != null) {
            // Création de l'icône à partir de l'URL
            ImageIcon icon = new ImageIcon(((new ImageIcon(imageURL)).getImage()).getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            setIcon(icon);
        } else {
            System.err.println("Image introuvable !");
        }

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBounds(x, y, width, height);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        attackCircle = new GamePanel.Circle(x, y, attackRadius);
        panel.addCircle(attackCircle);
        panel.add(this);
        panel.revalidate();
    }
    public GameButton(PondObject obj, String imgName, int width, int height) {
        this(obj, imgName, width, height, DEFAULT_IMG_WIDTH, DEFAULT_IMG_HEIGHT);
    }
    public GameButton(PondObject obj, String imgName) {
        this(obj, imgName, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_IMG_WIDTH, DEFAULT_IMG_HEIGHT);
    }
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }
    public void changeImage(String imgName){
        URL imageURL = PondApplication.class.getResource("/com/example/demo2/" + imgName + ".png");
        if (imageURL != null) {
            // Création de l'icône à partir de l'URL
            ImageIcon icon = new ImageIcon(((new ImageIcon(imageURL)).getImage()).getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            setIcon(icon);
        } else {
            System.err.println("Image introuvable !");
        }
    }
    public void moveButton(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
//        panel.moveCircle(attackCircle, x, y);
    }
}