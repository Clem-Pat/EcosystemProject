package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final AttackCircle attackCircle;
    public static final int DEFAULT_IMG_WIDTH = 40;
    public static final int DEFAULT_IMG_HEIGHT = 40;

    public GameButton(PondObject obj, String imgName, int imgWidth, int imgHeight){
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
        attackCircle = new AttackCircle(x, y, attackRadius);
        panel.add(this);
        panel.revalidate();
    }
    public GameButton(PondObject obj, String imgName) {
        this(obj, imgName, DEFAULT_IMG_WIDTH, DEFAULT_IMG_HEIGHT);
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
        attackCircle.moveButton(x, y);
    }
    class AttackCircle extends JButton{
        public int x;
        public int y;
        public int attackRadius;
        public AttackCircle(int x, int y, int attackRadius){
            this.attackRadius = attackRadius;
            this.x = (int) (x - 0.5*(this.attackRadius - width));
            this.y = (int) (y - 0.5*(this.attackRadius - width));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setBounds(this.x, this.y, this.attackRadius, this.attackRadius);
            panel.add(this);
            panel.revalidate();
        }
        protected void paintComponent(Graphics g) {
            g.setColor(Color.RED);
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