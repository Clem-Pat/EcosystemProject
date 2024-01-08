package com.example.demo2.tests;


import com.example.demo2.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class FoxTest {

    private PondApplication pond;

    @Before
    public void setUp() {
        pond = new PondApplication();
    }

    @Test
    public void move() {
        Fox fox = new Fox(pond, "TestFox", 100, 100);
        fox.button = new GameButton(fox, "fox");

        // Test movement to the right
        fox.move("Right");
        assertEquals(120, fox.x);

        // Test movement to the left
        fox.move("Left");
        assertEquals(100, fox.x);

        // Test movement upward
        fox.move("Up");
        assertEquals(80, fox.y);

        // Test movement downward
        fox.move("Down");
        assertEquals(100, fox.y);
    }

    @Test
    public void eat() {
        Fox fox = new Fox(pond, "TestFox", 100, 100);
        Frog frog = new Frog(pond, "TestFrog", 100, 100);
        fox.button = new GameButton(fox, "fox");
        frog.button = new GameButton(frog, "frog");
        fox.canKill = true;
        // Test eating a frog within attack radius
        String result = fox.eat(frog);
        assertEquals("TestFox eats the frog TestFrog", result);
        assertTrue(frog.isDead());
        assertEquals(10.0, fox.mass, 0.01);

        // Test eating a dead frog
        result = fox.eat(frog);
        assertEquals("The fly TestFrog is already dead", result);
    }

    @Test
    public void findDirectionOfNearestFrog() {
        Fox fox = new Fox(pond, "TestFox", 100, 100);
        Frog frog = new Frog(pond, "TestFrog", 300, 100);
        pond.listFrogs.add(frog);

        String result = fox.findDirectionOfNearestFrog();
        assertEquals("Grenouille la plus proche : 200,00 pixels dans la direction : E", result);
    }

    @Test
    public void findDirectionOfNearestFly() {
        Fox fox = new Fox(pond, "TestFox", 200, 100);
        Fly fly = new Fly(pond, "TestFly", 50, 100);
        pond.listFlies.add(fly);

        String result = fox.findDirectionOfNearestFly();
        assertEquals("Mouche la plus proche : 150,00 pixels dans la direction : O", result);
    }
}


