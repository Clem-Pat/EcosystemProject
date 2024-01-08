package com.example.demo2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AnimalTest {

    private Animal testAnimal;
    private PondApplication pond;

    @Before
    public void setUp() {
        testAnimal = new Animal();
        pond = new PondApplication();
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


    @Test
    public void testAgingForYoungAnimal() {
        Animal youngAnimal = new Animal();
        youngAnimal.age = 3; // Âge inférieur à 24
        youngAnimal.speed = 5; // Vitesse initiale

        youngAnimal.aging();

        assertEquals(4, youngAnimal.age);
        assertEquals(6, youngAnimal.speed);
    }

    @Test
    public void testAgingForOldAnimal() {
        Animal oldAnimal = new Animal();
        oldAnimal.age = 25; // Âge supérieur à 24
        oldAnimal.speed = 8; // Vitesse initiale

        oldAnimal.aging();

        assertEquals(26, oldAnimal.age);
        assertEquals(7, oldAnimal.speed); // La vitesse diminue car l'animal est vieux
    }

    @Test
    public void testAgingForYoungFrog() {
        Frog youngFrog = new Frog(pond, "Testfrog",1,2);
        youngFrog.age = 3; // Âge inférieur à 24
        youngFrog.speed = 5; // Vitesse initiale
        youngFrog.tongueSpeed = 10; // Vitesse de la langue initiale

        youngFrog.aging();

        assertEquals(4, youngFrog.age);
        assertEquals(6, youngFrog.speed);
        assertEquals(11, youngFrog.tongueSpeed); // La vitesse de la langue doit augmenter pour les grenouilles
    }
}


