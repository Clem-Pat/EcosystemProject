package com.example.demo2;

import java.util.concurrent.ThreadLocalRandom;

public class Fly extends Animal{

    //Constructor
    public Fly(PondApplication pond, String name, int x, int y) {
        this.type = "fly";
        this.pond = pond;
        this.name = name;
        this.mass = 5;
        this.speed = 2;
        this.x = x;
        this.y = y;
        this.radius = 70;
        this.attackRadius = this.radius;
    }
    public void move(String order){
        this.mass = this.mass - 0.3;
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
        if ((this.x + directionX*nP*speed > 100) && (this.x + directionX*nP*speed < pond.screenSize.getWidth() - 200)){
            this.x = (int) (this.x + directionX*nP*speed);
        }
        if ((this.y + directionY*nP*speed > 100) && (this.y + directionY*nP*speed < pond.screenSize.getHeight() - 200)){
            this.y = (int) (this.y + directionY*nP*speed);
        }
        button.moveButton(this.x, this.y);
    }
    public void sting(Fox fox) {
        if (this.canKill){
            if (!fox.isDead()){
                if (Math.sqrt(Math.pow((this.x+0.5*this.radius) - (fox.x+0.5*fox.radius), 2) + Math.pow((this.y+0.5*this.radius) - (fox.y+0.5*fox.radius), 2)) <=  this.radius + 0.5*(this.attackRadius-this.radius)){
                    System.out.println("The fly " + this.name + " stings the fox " + fox.name);
                    fox.kill();
                }
            }
        }
    }
}
