package com.example.demo2.tests;


import com.example.demo2.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameButtonTest {

    private GamePanel panel;

    @Before
    public void setUp() {
        PondApplication pond = new PondApplication();
        GameFrame frame = new GameFrame(pond, "Test Frame");
        panel = new GamePanel(frame, null);
        frame.add(panel);
    }

    @Test
    public void constructorWithImageNameAndSize() {
        Animal obj = new Frog(new PondApplication(), "TestFrog", 100, 100);
        GameButton button = new GameButton(obj, "frog", 30, 30);

        assertEquals("TestFrog", button.name);
        assertEquals(100, button.x);
        assertEquals(100, button.y);
        assertEquals(30, button.width);
        assertEquals(30, button.height);

        // Assuming DEFAULT_IMG_WIDTH and DEFAULT_IMG_HEIGHT are 40
        assertEquals(40, button.imgWidth);
        assertEquals(40, button.imgHeight);
        assertEquals(obj.attackRadius, button.attackCircle.attackRadius);

        // Assuming the panel is the parent
        assertSame(panel, button.panel);

        // Test that the button is added to the panel
        assertTrue(panel.getComponentCount() > 0);
        assertTrue(panel.contains(button));
    }

    @Test
    public void constructorWithImageNameDefaultSize() {
        Animal obj = new Fox(new PondApplication(), "TestFox", 150, 150);
        GameButton button = new GameButton(obj, "fox");

        assertEquals("TestFox", button.name);
        assertEquals(150, button.x);
        assertEquals(150, button.y);
        assertEquals(GameButton.DEFAULT_IMG_WIDTH, button.width);
        assertEquals(GameButton.DEFAULT_IMG_HEIGHT, button.height);
        assertEquals(GameButton.DEFAULT_IMG_WIDTH, button.imgWidth);
        assertEquals(GameButton.DEFAULT_IMG_HEIGHT, button.imgHeight);
        assertEquals(obj.attackRadius, button.attackCircle.attackRadius);

        // Assuming the panel is the parent
        assertSame(panel, button.panel);

        // Test that the button is added to the panel
        assertTrue(panel.getComponentCount() > 0);
        assertTrue(panel.contains(button));
    }
}

