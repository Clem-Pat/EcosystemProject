package com.example.demo2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    private final PondApplication app;
    public GameFrame(PondApplication app, String title) {
        super.setTitle(title);
        this.app = app;
        addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (!app.fox.isDead()){
            app.fox.move(KeyEvent.getKeyText(e.getKeyCode()));
            String direction = KeyEvent.getKeyText(e.getKeyCode());
            if (direction.equals("Up") || direction.equals("Down") || direction.equals("Right") || direction.equals("Left")){app.goToNextDay();}
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
