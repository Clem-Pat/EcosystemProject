package com.example.demo2.tests;
import com.example.demo2.Animal;
import com.example.demo2.GameButton;
import com.example.demo2.GamePanel;
import com.example.demo2.PondApplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;

public class GameFrameTest {

    private GamePanel panel;
    private GameButton testButton;

    @Before
    public void setUp() {
        PondApplication pond = new PondApplication();
        panel = new GamePanel(pond);
        pond.panel = panel;
        Animal animal = new Animal();
        animal.pond = pond;
        testButton = new GameButton(animal, "testButton");
    }

    @Test
    public void containsButtonShouldReturnTrueAfterAddingButton() {
        panel.add(testButton);
        panel.revalidate();
        assertTrue(panel.contains(testButton));
    }
}
