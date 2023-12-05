public class Frog {
    public String name;
    private double mass;
    public double age = 0;
    private double tongueSpeed;
    public static final String DEFAULT_NAME = "Tea";
    public static final double DEFAULT_TONGUE_SPEED = 5;

    //Constructor
    public Frog(String name, double mass, double tongueSpeed) {
        this.name = name;
        this.mass = mass;
        this.tongueSpeed = tongueSpeed;
    }
    public Frog(double mass, double tongueSpeed) {
        this(DEFAULT_NAME, mass, tongueSpeed);
    }
    public Frog(double mass) {
        this(DEFAULT_NAME, mass, DEFAULT_TONGUE_SPEED);
    }
    public double get_mass() { return mass; }
    public double get_tongue_speed() { return tongueSpeed; }
    public void set_mass(double mass) { this.mass = mass;}
    public void set_tongue_speed(double tongueSpeed) { this.tongueSpeed = tongueSpeed;}

    public String toString(){
        if (this.mass == 0) {return this.name + "DEAD"; }
        else {return this.name + ", mass : " + mass + ", tongueSpeed : " + tongueSpeed; }
    }

    public String eat(Fly fly){
        if (fly.isDead()){
            return "The fly"+fly.name+" is already dead";
        }
        else {
            if (this.tongueSpeed >= fly.get_speed()){
                this.set_mass(fly.get_mass());
                fly.kill();
                return "the fly " + fly.name + " is eaten by " + this.name;
            }
            else {
                return "the fly " + fly.name + " is too quick !!";
            }
        }
    }

    public void aging(){
        this.age++;
        if (this.mass > 20){
            this.age++;
        }
        if (this.age > 70){
            this.tongueSpeed--;
            if (this.age > 100){
                this.kill();
            }
        }
        else {
            this.tongueSpeed++;
        }

    }
    public void kill(){
        this.set_mass(0);
    }
    public boolean isDead(){
        return this.mass == 0;
    }

}