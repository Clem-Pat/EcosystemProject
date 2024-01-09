package com.example.demo2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * La classe GameFrame représente la fenêtre principale de l'application de notre écosystème.
 * Elle étend JFrame et implémente KeyListener pour gérer les interactions du joueur via son clavier.
 */

public class GameFrame extends JFrame implements KeyListener {
    /**
     * L'application principale de notre écosystème associée à la fenêtre.
     */
    private final PondApplication app;
    /**
     * Constructeur de la classe GameFrame.
     *
     * @param app    L'application principale de notre écosystème.
     * @param title  Le titre de la fenêtre.
     */
    public GameFrame(PondApplication app, String title) {
        super.setTitle(title);
        this.app = app;
        addKeyListener(this);
    }
    /**
     * Gère les événements des actions sur les touches.
     *
     * @param e L'événement KeyEvent associé à l'utilisation des touches, ici juste
     *          un appui non prolongé sur une touche.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        String direction = KeyEvent.getKeyText(e.getKeyCode());
        if (direction.equals("Up") || direction.equals("Down") || direction.equals("Right") || direction.equals("Left")){
            if (!app.fox.isDead()){
                app.fox.move(KeyEvent.getKeyText(e.getKeyCode()));
                app.goToNextDay();
            }
        }
    }
    /**
     * Gère les événements des actions sur les touches.
     *
     * @param e L'événement KeyEvent associé à l'utilisation des touches, ici
     *          un appui prolongé sur une touche.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyTyped(e);
    }
    /**
     * Gère les événements des actions sur les touches.
     *
     * @param e L'événement KeyEvent associé à l'utilisation des touches, ici
     *      *          leur relâchement..
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
