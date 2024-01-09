package com.example.demo2;

import javafx.application.Application;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
/**
 * La classe GamePanel représente le panneau principal de notre jeu.
 * Elle étend JPanel pour fournir une zone graphique dans laquelle nos composants peuvent être ajoutés.
 */

public class GamePanel extends JPanel {
    /**
     * Notre image de fond.
     */
    public final Image image;
    /**
     * Constructeur de la classe GamePanel.
     *
     * @param pond L'application principale de l'écosystème.
     */

    // Constructor
    public GamePanel(PondApplication pond) {
        Image image = null;
        final URL imageURL = PondApplication.class.getResource("/com/example/demo2/pond.jpg");
        if (imageURL != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
    /**
     * Redéfinition de la méthode paintComponent pour dessiner le contenu du panneau.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
    /**
     * Vérifie si le panneau contient un bouton spécifié.
     *
     * @param button Le bouton à vérifier.
     * @return true si le panneau contient le bouton, sinon false.
     */
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
