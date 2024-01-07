package com.example.demo2;
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
        //panel = new GamePanel(new GameFrame(pond), "Test");
        testButton = new GameButton(new Animal(), "testButton");
    }

    @Test
    public void containsButtonShouldReturnTrueAfterAddingButton() {
        panel.add(testButton);
        panel.revalidate();
        assertTrue(panel.contains(testButton));
    }

    @Test
    public void containsButtonShouldReturnFalseIfButtonNotAdded() {
        assertFalse(panel.contains(testButton));
    }
}
