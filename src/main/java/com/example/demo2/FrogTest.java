package com.example.demo2;
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

        // Test when there are no flies in the pond
        frog.move();
        assertTrue(frog.isDead()); // The frog should be dead because there are no flies to eat

        // Test when there is a fly in the pond
        Fly fly = new Fly(pond, "TestFly", 120, 120);
        pond.listFlies.add(fly);
        frog.move();
        assertEquals(1, pond.listFlies.size()); // The fly should be eaten
    }

    @Test
    public void testGoTo() {
        Frog frog = new Frog(pond, "Froggy", 100, 100);

        // Test moving to a specific location
        frog.goTo(200, 200);
        assertEquals(200, frog.getX());
        assertEquals(200, frog.getY());

        // Test moving to a location outside the pond boundaries
        frog.goTo(2500, 2500);
        // Assuming the pond's width and height are 2000 x 1000
        assertEquals(2000, frog.getX()); // The frog should not go beyond the pond's width
        assertEquals(1000, frog.getY()); // The frog should not go beyond the pond's height
    }

    @Test
    public void testEat() {
        Frog frog = new Frog(pond, "Froggy", 100, 100);

        // Test eating a dead fly
        Fly deadFly = new Fly(pond, "DeadFly", 110, 110);
        deadFly.kill();
        String result1 = frog.eat(deadFly);
        assertEquals("The fly DeadFly is already dead", result1);

        // Test eating a live fly successfully
        Fly liveFly = new Fly(pond, "LiveFly", 120, 120);
        String result2 = frog.eat(liveFly);
        assertEquals("Froggy eats the fly LiveFly", result2);

        // Test when the fly is too quick
        liveFly.move("flee");
        String result3 = frog.eat(liveFly);
        assertEquals("the fly LiveFly is too quick !!", result3);

    }
}
