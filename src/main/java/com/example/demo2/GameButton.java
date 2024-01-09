package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 * La classe GameButton représente le bouton graphique associé à un animal dans notre jeu.
 * Chaque bouton représente un Animal et possède une image, une position et un cercle d'attaque (attackCircle) associé.
 */
public class GameButton extends JButton {
    /**
     * Le panneau graphique auquel le bouton appartient.
     */
    public final GamePanel panel;
    /**
     * Le nom de l'animal associé au bouton.
     */
    public String name;
    /**
     * La position horizontale du bouton.
     */
    public int x;
    /**
     * La position verticale du bouton.
     */
    public int y;
    /**
     * La hauteur du bouton.
     */
    public int height;
    /**
     * La largeur du bouton.
     */
    public int width;
    /**
     * La largeur de l'image du bouton.
     */
    public int imgWidth;
    /**
     * La hauteur de l'image du bouton.
     */
    public int imgHeight;
    /**
     * Le rayon d'attaque  du bouton (jusqu'où l'animal peut-il attaquer ?)
     */
    public int attackRadius;
    /**
     * Le cercle d'attaque associé au bouton (l'animal attaque dans cette zone tout
     * autre animal s'y présentant)
     */
    public final AttackCircle attackCircle;
    /**
     * La largeur par défaut de l'image du bouton.
     */
    public static final int DEFAULT_IMG_WIDTH = 40;
    public static final int DEFAULT_IMG_HEIGHT = 40;
    /**
     * Constructeur de la classe GameButton avec spécification de la largeur et de la hauteur de l'image.
     *
     * @param obj      L'animal associé au bouton.
     * @param imgName  Le nom de l'image du bouton.
     * @param imgWidth La largeur de l'image du bouton.
     * @param imgHeight La hauteur de l'image du bouton.
     */

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
    /**
     * Constructeur de la classe GameButton avec utilisation des valeurs
     * par défaut pour la largeur et la hauteur de l'image.
     *
     * @param obj     L'animal associé au bouton.
     * @param imgName Le nom de l'image du bouton.
     */
    public GameButton(Animal obj, String imgName) {
        this(obj, imgName, DEFAULT_IMG_WIDTH, DEFAULT_IMG_HEIGHT);
    }
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }
    /**
     * Change l'image du bouton en fonction du nom de l'image spécifiée.
     *
     * @param imgName Le nom de la nouvelle image.
     */
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
    /**
     * Déplace le bouton à une nouvelle position spécifiée.
     *
     * @param x La nouvelle position horizontale du bouton.
     * @param y La nouvelle position verticale du bouton.
     */
    public void moveButton(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        attackCircle.moveButton(x, y);
    }
    /**
     * Cache le bouton et son cercle d'attaque.
     */
    public void hideButton(){
        this.setVisible(false);
        this.attackCircle.setVisible(false);
    }
    /**
     * Cette classe interne à notre classe représente un cercle d'attaque associé au GameButton.
     */
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