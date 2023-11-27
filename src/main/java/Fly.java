public class Fly {
    public String name;
    private double mass;
    private double speed;
    public static final String DEFAULT_NAME = "Tibo";

    //Constructor
    public Fly(String name, double mass, double speed) {
        this.name = name;
        this.mass = mass;
        this.speed = speed;
    }
    public Fly(double mass, double speed){
        this(DEFAULT_NAME, mass, speed);
    }
    public double get_mass() { return mass; }
    public double get_speed() { return speed; }
    public void set_mass(double mass) { this.mass = mass;}
    public void set_speed(double speed) { this.speed = speed;}

    public String toString(){
        if (this.mass == 0) {return this.name + "DEAD"; }
        else {return this.name + ", mass : " + mass + ", speed : " + speed; }
    }
    public void grow(int addMass) {
        mass += addMass;
    }
    public void kill() {this.set_mass(0);}
    public boolean isDead() { return mass==0 ;}
}
