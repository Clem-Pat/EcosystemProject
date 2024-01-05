package com.example.demo2;

public class Fox extends PondObject{
    public static final double DEFAULT_MASS = 5;
    public static final int DEFAULT_X = 30;
    public static final int DEFAULT_Y = 30;


    public Fox(PondApplication pond, String name, double mass, int x, int y) {
        this.type = "fox";
        this.pond = pond;
        this.name = name;
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.attackRadius = 70;
        this.speed = 1;
    }
    public Fox(PondApplication pond, String name) {
        this(pond, name, DEFAULT_MASS, DEFAULT_X, DEFAULT_Y);
    }
    public void move(String direction){
        this.mass = this.mass - 0.1; //It starves as it moves
        int dx =0; int dy = 0;
        int nP = 20;
        if (direction.equals("Left")) {dx = -1;}
        if (direction.equals("Right")) {dx = 1;}
        if (direction.equals("Up")) {dy = -1;}
        if (direction.equals("Down")) {dy = 1;}
        this.x = (int) (this.x + dx*nP*this.speed);
        this.y = (int) (this.y + dy*nP*this.speed);
        this.button.moveButton(this.x, this.y);
    }
    public String eat(Frog frog){

        if (Math.sqrt(Math.pow(this.x - frog.x, 2) + Math.pow(this.y - frog.y, 2)) <= this.attackRadius){
            if (frog.isDead()){
                return "The fly "+frog.name+" is already dead";
            }
            else{
                this.mass = this.mass + frog.mass;
                frog.kill();
                return this.name + " eats the frog " + frog.name;
            }
        }
        return "false";
    }
}
