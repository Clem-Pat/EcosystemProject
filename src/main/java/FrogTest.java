import static org.junit.jupiter.api.Assertions.*;

class FrogTest {

    @org.junit.jupiter.api.Test
    void get_mass() {
        Frog frog = new Frog ("TestFrog", 3, 5);
        assertEquals("TestFrog", frog.name);
        assertEquals(3, frog.get_mass());
    }

    @org.junit.jupiter.api.Test
    void get_tongue_speed() {
        Frog frog = new Frog ("TestFrog", 3, 5);
        assertEquals("TestFrog", frog.name);
        assertEquals(5, frog.get_tongue_speed());
    }

    @org.junit.jupiter.api.Test
    void set_mass() {
        Frog frog = new Frog ("TestFrog", 3, 5);
        assertEquals("TestFrog", frog.name);
        assertEquals(3, frog.get_mass());
        frog.set_mass(4);
        assertEquals(4, frog.get_mass());
    }

    @org.junit.jupiter.api.Test
    void set_tongue_speed() {
        Frog frog = new Frog ("TestFrog", 3, 5);
        assertEquals("TestFrog", frog.name);
        assertEquals(5, frog.get_tongue_speed());
        frog.set_tongue_speed(6);
        assertEquals(6, frog.get_tongue_speed());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Frog frog = new Frog ("TestFrog", 3, 5);
        assertEquals("TestFrog, mass : 3, tongueSpeed : 5", frog.toString());

        Frog deadFrog = new Frog("TestDeadFrog", 0, 0);
        assertEquals("TestDeadFrog DEAD", deadFrog.toString());
    }

    @org.junit.jupiter.api.Test
    void eat() {
        Frog frog = new Frog ("TestFrog1", 3, 5);
        Fly fly = new Fly ("TestFly1", 0.1, 2.5);
        String result = frog.eat(fly);
        assertEquals("the fly TestFly1 is eaten by TestFrog1", result);

        Frog frog1 = new Frog ("TestFrog2", 3, 2);
        Fly fly1 = new Fly ("TestFly1", 0.1, 2.5);
        String result1 = frog1.eat(fly1);
        assertEquals("the fly TestFly1 is too quick !!", result1);

        Frog frog2 = new Frog ("TestFrog3", 3, 2);
        Fly fly2 = new Fly ("TestFly2", 0.0, 0.0);
        String result2 = frog2.eat(fly2);
        assertEquals("the fly TestFly2 is already dead", result2);
    }

    @org.junit.jupiter.api.Test
    void aging() {
        Frog frog = new Frog("TestFrog", 3, 5);
        frog.aging();

        assertEquals(1, frog.get_age());
        assertEquals(6, frog.get_tongue_speed());
    }
    @org.junit.jupiter.api.Test
    void agingMassAbove20() {
        Frog frog = new Frog("TestFrog", 25, 5);
        frog.aging();

        assertEquals(2, frog.get_age());
        assertEquals(4, frog.get_tongue_speed();
    }

    @org.junit.jupiter.api.Test
    void kill() {
        // Ajoute des tests pour la méthode kill si nécessaire
    }
}
