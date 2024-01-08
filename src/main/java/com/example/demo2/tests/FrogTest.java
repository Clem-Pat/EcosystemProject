package com.example.demo2.tests;
import com.example.demo2.Fly;
import com.example.demo2.Frog;
import com.example.demo2.GameButton;
import com.example.demo2.PondApplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrogTest {

    private PondApplication pond;

    @Before
    public void setUp() {
        pond = new PondApplication();
    }

    @Test
    public void testMove() {
        Frog frog = new Frog(pond, "Froggy", 100, 100);
        frog.button = new GameButton(frog, "frog");
        frog.canKill = true;
        // Test when there are no flies in the pond
        frog.mass = 0.1;
        frog.move();
        assertTrue(frog.isDead()); // The frog should be dead because it starves to death

        // Test when there is a fly in the pond
        frog.mass = 5;
        Fly fly = new Fly(pond, "TestFly", 100, 100);
        fly.button = new GameButton(fly, "fly");
        pond.listFlies.add(fly);
        frog.move();
        assertEquals(0, pond.listFlies.size()); // The fly should be eaten
    }

    @Test
    public void testGoTo() {
        Frog frog = new Frog(pond, "Froggy", 100, 100);
        frog.button = new GameButton(frog, "frog");
        // Test moving to a specific location
        frog.goTo(200, 200);
        assertEquals(120, frog.x);
        assertEquals(120, frog.y);

        // Test moving to a location outside the pond boundaries
        frog.x = 2500;
        frog.y = 2500;
        frog.goTo(2600, 2600);
        // Assuming the pond's width and height are 2000 x 1000
        assertEquals(2500, frog.x); // The frog should not go beyond the pond's width
        assertEquals(2500, frog.y); // The frog should not go beyond the pond's height
    }
    @Test
    public void testAging(){
        Frog frog = new Frog(pond, "Froggy", 100, 100);
        frog.button = new GameButton(frog, "frog");
        frog.aging();
        assertEquals(1.6, frog.tongueSpeed, 0.1);
        assertEquals(2.1, frog.speed, 0.1);
    }
    @Test
    public void testEat() {
        Frog frog = new Frog(pond, "Froggy", 100, 100);
        frog.button = new GameButton(frog, "frog");
        frog.canKill = true;
        // Test eating a dead fly
        Fly deadFly = new Fly(pond, "DeadFly", 110, 110);
        deadFly.button = new GameButton(deadFly, "deadFly");
        deadFly.kill();
        String result1 = frog.eat(deadFly);
        assertEquals("The fly DeadFly is already dead", result1);

        // Test eating a living fly successfully
        Fly livingFly = new Fly(pond, "livingFly", 120, 120);
        livingFly.button = new GameButton(livingFly, "fly");
        String result2 = frog.eat(livingFly);
        assertEquals("Froggy eats the fly livingFly", result2);

        // Test when the fly is too quick
        livingFly.mass = 3;
        livingFly.speed = 10;
        String result3 = frog.eat(livingFly);
        assertEquals("the fly livingFly is too quick !!", result3);

    }
}
