package com.example.demo2.tests;


import com.example.demo2.Fly;
import com.example.demo2.Fox;
import com.example.demo2.Frog;
import com.example.demo2.PondApplication;
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

        Frog frog = new Frog(pond, "TestFrog", 110, 100);

        // Test eating a frog within attack radius
        String result = fox.eat(frog);
        assertEquals("TestFox eats the frog TestFrog", result);
        assertTrue(frog.isDead());
        assertEquals(10.0, fox.mass);

        // Test eating a dead frog
        result = fox.eat(frog);
        assertEquals("The fly TestFrog is already dead", result);
    }

    @Test
    public void findDirectionOfNearestFrog() {
        Fox fox = new Fox(pond, "TestFox", 100, 100);

        Frog frog = new Frog(pond, "TestFrog", 130, 130);

        String result = fox.findDirectionOfNearestFrog();
        assertEquals("Grenouille la plus proche : 36.06 pixels dans la direction : SE", result);
    }

    @Test
    public void findDirectionOfNearestFly() {
        Fox fox = new Fox(pond, "TestFox", 100, 100);

        Fly fly = new Fly(pond, "TestFly", 80, 80);

        String result = fox.findDirectionOfNearestFly();
        assertEquals("Mouche la plus proche : 28.28 pixels dans la direction : NW", result);
    }
}


