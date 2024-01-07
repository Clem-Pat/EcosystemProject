package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

class GamePanelTest {

    private GamePanel panel;
    private GameFrame frame;

    @Before
    void setUp() {
        // Mettre instance de GameFrame ici
        frame = new GameFrame(new PondApplication(), "Test");
        // Mettre instance d'image ici
        Image image = null;
        panel = new GamePanel(frame, image);
    }

    @Test
    void containsButtonShouldReturnTrueAfterAddingButton() {
        GameButton testButton = new GameButton(new Animal(), "testButton");
        panel.add(testButton);
        panel.revalidate();
        assertTrue(panel.contains(testButton));
    }

    @Test
    void containsButtonShouldReturnFalseIfButtonNotAdded() {
        GameButton testButton = new GameButton(new Animal(), "testButton");
        assertFalse(panel.contains(testButton));
    }

    @Test
    void mouseClickedShouldSetFocusToFrame() {
        // Aidé par le Chat
        panel.getMouseListeners()[0].mouseClicked(new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));


        assertTrue(frame.isFocused());
    }
}
