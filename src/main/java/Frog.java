public class Frog {
    public String name;
    private double mass;
    public double age = 0; //Age in months
    private double tongueSpeed;
    public static final String DEFAULT_NAME = "Tea";
    public static final double DEFAULT_TONGUE_SPEED = 5;
    private boolean isFroglet;
    private static final String SPECIES = "Rare Pepe";




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
    public double ageInYears() {return (int) (age/12);}


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
    private boolean calculateIsFroglet() {
        return age > 1 && age < 7;
    }
    public void grow (int numberOfMonths) {
        age += numberOfMonths;

        for (int i = 0; i < numberOfMonths && age <= 12; i++) {
            tongueSpeed++;
        }

        if (age >= 30) {
            for (int i = 0; i < numberOfMonths; i++) {
                if (tongueSpeed > 5) {
                    tongueSpeed--;
                }
            }
        }

        // Update isFroglet accordingly
        updateIsFroglet();
    }

    public void grow() {
        // Age the Frog by one month
        age++;

        // Update tongueSpeed accordingly
        if (age <= 12) {
            tongueSpeed++;
        } else if (age >= 30 && tongueSpeed > 5) {
            tongueSpeed--;
        }

        // Update isFroglet accordingly
        updateIsFroglet();
    }

    // Helper method to update isFroglet based on age
    private void updateIsFroglet() {
        isFroglet = age <= 12;
    }

    public void kill(){
        this.set_mass(0);
    }
    public boolean isDead(){
        return this.mass == 0;
    }
    public String toString() {
        String frogType = isFroglet ? "froglet" : "frog";
        String outputFormat = "My name is %s and I'm a rare %s. I'm %d months old and my tongue has a speed of %.2f.";

        return String.format(outputFormat, name, frogType, age, tongueSpeed);
    }

}