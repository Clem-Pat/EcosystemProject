package com.example.demo2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AnimalTest {

    private Animal testAnimal;

    @Before
    public void setUp() {
        testAnimal = new Animal();
    }

    @Test
    public void isDeadWhenMassIsZero() {
        testAnimal.mass = 0;
        assertTrue(testAnimal.isDead());
    }

    @Test
    public void isDeadWhenMassIsNegative() {
        testAnimal.mass = -1;
        assertTrue(testAnimal.isDead());
    }

    @Test
    public void isNotDeadWhenMassIsPositive() {
        testAnimal.mass = 5;
        assertFalse(testAnimal.isDead());
    }

    @Test
    public void killSetsMassToZero() {
        testAnimal.mass = 10;
        testAnimal.kill();
        assertEquals(0, testAnimal.mass);
    }

    @Test
    public void killRemovesFromPondListWhenFrog() {
        testAnimal.type = "frog";
        PondApplication pond = new PondApplication();
        testAnimal.pond = pond;
        pond.listFrogs.add(testAnimal);

        testAnimal.kill();

        assertTrue(pond.listFrogs.isEmpty());
    }

    @Test
    public void killRemovesFromPondListWhenFly() {
        testAnimal.type = "fly";
        PondApplication pond = new PondApplication();
        testAnimal.pond = pond;
        pond.listFlies.add(testAnimal);

        testAnimal.kill();

        assertTrue(pond.listFlies.isEmpty());
    }

    @Test
    public void findNearestObjectReturnsNullWhenListIsEmpty() {
        assertNull(testAnimal.findNearestObject(new ArrayList<>()));
    }

    @Test
    public void findNearestFlyReturnsNullWhenListFliesIsEmpty() {
        assertNull(testAnimal.findNearestFly());
    }

    @Test
    public void findNearestFrogReturnsNullWhenListFrogsIsEmpty() {
        assertNull(testAnimal.findNearestFrog());
    }

}

