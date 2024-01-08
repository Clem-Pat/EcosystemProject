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
        panel = new GamePanel(pond);
        frame.add(panel);
    }

    @Test
    public void constructorWithImageNameAndSize() {
        Animal obj = new Frog(new PondApplication(), "TestFrog", 100, 100);
        GameButton button = new GameButton(obj, "frog", 80, 80);

        assertEquals("TestFrog", button.name);
        assertEquals(100, button.x);
        assertEquals(100, button.y);
        assertEquals(80, button.width);
        assertEquals(80, button.height);

        // Assuming DEFAULT_IMG_WIDTH and DEFAULT_IMG_HEIGHT are 80
        assertEquals(80, button.imgWidth);
        assertEquals(80, button.imgHeight);
        assertEquals(obj.attackRadius, button.attackCircle.attackRadius);
    }

    @Test
    public void constructorWithImageNameDefaultSize() {
        Animal fly = new Fly(new PondApplication(), "TestFly", 150, 150);
        GameButton button = new GameButton(fly, "fly");

        assertEquals("TestFly", button.name);
        assertEquals(150, button.x);
        assertEquals(150, button.y);
        assertEquals(GameButton.DEFAULT_IMG_WIDTH, button.imgWidth);
        assertEquals(GameButton.DEFAULT_IMG_HEIGHT, button.imgHeight);
        assertEquals(fly.attackRadius, button.attackCircle.attackRadius);
    }
}

