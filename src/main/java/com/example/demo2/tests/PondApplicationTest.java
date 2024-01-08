package com.example.demo2.tests;


import com.example.demo2.PondApplication;
import com.example.demo2.Fox;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PondApplicationTest {

    private PondApplication pondApp;

    @Before
    public void setUp() {
        pondApp = new PondApplication();
    }

    @Test
    public void addFlyIncreasesListFliesSize() {
        int initialSize = pondApp.listFlies.size();
        pondApp.addFly();
        assertEquals(initialSize + 1, pondApp.listFlies.size());
    }

    @Test
    public void addFrogIncreasesListFrogsSize() {
        int initialSize = pondApp.listFrogs.size();
        pondApp.addFrog();
        assertEquals(initialSize + 1, pondApp.listFrogs.size());
    }

    @Test
    public void initiateGamePopulatesLists() {
        pondApp.initiateGame();
        assertFalse(pondApp.listFlies.isEmpty());
        assertFalse(pondApp.listFrogs.isEmpty());
        assertNotNull(pondApp.fox);
    }

    @Test
    public void goToNextDayIncrementsDay() {
        int initialDay = pondApp.day;
        pondApp.fox = new Fox(pondApp, "BrunoTest", 3, 3);
        pondApp.goToNextDay();
        assertEquals(initialDay + 1, pondApp.day);
    }

    @Test
    public void goToNextDayIncreasesListFliesIfEmpty() {
        pondApp.fox = new Fox(pondApp, "BrunoTest", 3, 3);
        pondApp.listFlies.clear();
        pondApp.goToNextDay();
        assertFalse(pondApp.listFlies.isEmpty());
    }

    @Test
    public void goToNextDayIncreasesListFrogsIfEmpty() {
        pondApp.fox = new Fox(pondApp, "BrunoTest", 3, 3);
        pondApp.listFrogs.clear();
        pondApp.goToNextDay();
        assertFalse(pondApp.listFrogs.isEmpty());
    }


}

