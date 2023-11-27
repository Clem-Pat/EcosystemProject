import static org.junit.jupiter.api.Assertions.*;

class FlyTest {

    @org.junit.jupiter.api.Test
    void get_mass() {
        Fly fly = new Fly("TestFly", 0.1, 2.5);
        assertEquals("TestFly", fly.name);
        assertEquals(0.1, fly.get_mass());

    }

    @org.junit.jupiter.api.Test
    void get_speed() {
        Fly fly = new Fly("TestFly", 0.1, 2.5);
        assertEquals("TestFly", fly.name);
        assertEquals(2.5, fly.get_speed());
    }

    @org.junit.jupiter.api.Test
    void set_mass() {
        Fly fly = new Fly("TestFly", 0.1, 2.5 );
        assertEquals ("TestFly", fly.name);
        assertEquals (0.1, fly.get_mass());
        fly.set_mass(0.2);
        assertEquals (0.2, fly.get_mass());

    }

    @org.junit.jupiter.api.Test
    void set_speed() {
        Fly fly = new Fly("TestFly", 0.1, 2.5 );
        assertEquals ("TestFly", fly.name);
        assertEquals (2.5, fly.get_speed());
        fly.set_speed(3.0);
        assertEquals (3.0, fly.get_speed());

    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Fly fly = new Fly("TestFly", 0.1, 2.5 );
        assertEquals ("TestFly, mass : 0.1, speed : 2.5", fly.toString());
        Fly fly = new Fly ("TestDeadfly", 0.0, 0.0);
        assertEquals ("TestDeadFly DEAD", fly.toString());
    }

    @org.junit.jupiter.api.Test
    void grow() {
        Fly fly = new Fly("TestFly", 0.1, 2.5 );
        assertEquals (0.1, fly.get_mass());
        fly.grow(1);
        assertEquals (1.1, fly.get_mass());
    }

    @org.junit.jupiter.api.Test
    void kill() {
        Fly fly = new Fly("TestFly", 0.1, 2.5);
        assertEquals (0.1, fly.get_mass());
        fly.kill();
        assertEquals(0.0, fly.get_mass());

    }

    @org.junit.jupiter.api.Test
    void isDead() {
        Fly fly = new Fly("TestDeadFly", 0.0, 0.0);
        assertTrue (fly.isDead());

    }
}