package com.example.demo2;

import java.util.concurrent.ThreadLocalRandom;

public class Fly extends PondObject{
    public static final int DEFAULT_MASS = 3;
    public static final int DEFAULT_SPEED = 2;

    //Constructor
    public Fly(PondApplication pond, String name, int x, int y, double mass, double speed) {
        this.type = "fly";
        this.pond = pond;
        this.name = name;
        this.mass = mass;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.attackRadius = 70;
    }
    public Fly(PondApplication pond, String name, int x, int y) {
        this(pond, name, x, y, DEFAULT_MASS, DEFAULT_SPEED);
    }
    public void move(String order){
        this.mass = this.mass - 0.05;
        int directionX = 0;
        int directionY = 0;
        int nP = 0;
        if (order.equals("random")){ // randomly flies
            nP = 10; //Number of pixel it can move through each day
            directionX = ThreadLocalRandom.current().nextInt(-1, 2);
            directionY = ThreadLocalRandom.current().nextInt(-1, 2);
        }
        else if (order.equals("flee")){ //A frog tried to catch us, we try to flee
            nP = 20; //Number of pixel it can move through each day
            if (this.x > 600){directionX = -1;}
            else {directionX = 1;}
            if (this.y > 400){directionY = -1;}
            else {directionY = 1;}
        }
        this.x = (int) (this.x + directionX*nP*speed);
        this.y = (int) (this.y + directionY*nP*speed);
        button.moveButton(this.x, this.y);
    }
    public void sting(Fox fox) {
        if (!fox.isDead()){
            if (Math.sqrt(Math.pow(this.x - fox.x, 2) + Math.pow(this.y - fox.y, 2)) <= this.attackRadius){
                System.out.println("The fly " + this.name + " stings the fox " + fox.name);
                fox.kill();
            }
        }
    }
}