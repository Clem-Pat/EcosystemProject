package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameButton extends JButton {
    public final GamePanel panel;
    public String name;
    public int x;
    public int y;
    public int height;
    public int width;
    public int imgWidth;
    public int imgHeight;
    public int attackRadius;
    public final AttackCircle attackCircle;
    public static final int DEFAULT_IMG_WIDTH = 40;
    public static final int DEFAULT_IMG_HEIGHT = 40;
    public GameButton(Animal obj, String imgName, int imgWidth, int imgHeight){
        this.name = obj.name;
        this.x = obj.x;
        this.y = obj.y;
        this.width = obj.radius;
        this.height = obj.radius;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.attackRadius = obj.attackRadius;
        this.panel = obj.pond.panel;
        setText(name);
        addActionListener(e -> System.out.println(obj));
        changeImage(imgName);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBounds(x, y, width, height);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        attackCircle = new AttackCircle(obj);
        panel.add(this);
        panel.revalidate();
    }
    public GameButton(Animal obj, String imgName) {
        this(obj, imgName, DEFAULT_IMG_WIDTH, DEFAULT_IMG_HEIGHT);
    }
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }
    public void changeImage(String imgName){
        URL imageURL = PondApplication.class.getResource("/com/example/demo2/" + imgName + ".png");
        if (imageURL != null) {
            // Create icon with URL
            ImageIcon icon = new ImageIcon(((new ImageIcon(imageURL)).getImage()).getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            setIcon(icon);
        } else {
            System.out.println("Image introuvable !");
        }
    }
    public void moveButton(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        attackCircle.moveButton(x, y);
    }
    public void hideButton(){
        this.setVisible(false);
        this.attackCircle.setVisible(false);
    }
    public class AttackCircle extends JButton{
        public int x;
        public int y;
        public int attackRadius;
        public AttackCircle(Animal obj){
            this.attackRadius = obj.attackRadius;
            this.x = (int) (obj.x - 0.5*(this.attackRadius - width));
            this.y = (int) (obj.y - 0.5*(this.attackRadius - width));
            addActionListener(e -> System.out.println(obj));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setBounds(this.x, this.y, this.attackRadius, this.attackRadius);
            panel.add(this);
            panel.revalidate();
        }
        protected void paintComponent(Graphics g) {
            if (attackRadius - width > 0) {g.setColor(Color.RED);}
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
            super.paintComponent(g);
        }
        public void moveButton(int x, int y) {
            this.x = (int) (x - 0.5*(this.attackRadius - width));
            this.y = (int) (y - 0.5*(this.attackRadius - width));
            setBounds(this.x, this.y, this.attackRadius, this.attackRadius);
        }
    }
}