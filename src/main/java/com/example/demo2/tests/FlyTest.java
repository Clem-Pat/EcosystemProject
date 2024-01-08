package com.example.demo2.tests;
import com.example.demo2.Fly;
import com.example.demo2.Fox;
import com.example.demo2.GameButton;
import com.example.demo2.PondApplication;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class FlyTest {
    private PondApplication pond;

    @Before
    public void setUp() {
        pond = new PondApplication();
    }

    @Test
    public void moveRandomly() {
        PondApplication pond = new PondApplication();
        Fly fly = new Fly(pond, "TestFly", 50, 50);
        fly.button = new GameButton(fly, fly.type);
        int initialX = fly.x;
        int initialY = fly.y;

        fly.move("random");
        assertNotEquals(initialX, fly.x);
        assertNotEquals(initialY, fly.y);
        assertTrue(fly.mass < 5);
    }

    @Test
    public void moveToFlee() {
        PondApplication pond = new PondApplication();
        Fly fly = new Fly(pond, "TestFly", 300, 300);

        int initialX = fly.x;
        int initialY = fly.y;

        fly.move("flee");

        assertNotEquals(initialX, fly.x);
        assertNotEquals(initialY, fly.y);

        assertTrue(fly.mass < 5);
    }

    @Test
    public void stingFox() {
        PondApplication pond = new PondApplication();
        Fly fly = new Fly(pond, "TestFly", 50, 50);
        Fox fox = new Fox(pond, "TestFox", 100, 100);

        fly.sting(fox);


        assertTrue(fox.isDead());
    }

    @Test
    public void noStingWhenFoxDead() {
        PondApplication pond = new PondApplication();
        Fly fly = new Fly(pond, "TestFly", 50, 50);
        Fox fox = new Fox(pond, "TestDeadFox", 100, 100);
        fox.kill();

        fly.sting(fox);
        assertFalse(fox.isDead());
    }
}
