package com.example.demo2;

import java.util.concurrent.ThreadLocalRandom;

public class Frog extends Animal{
    public double tongueSpeed;

    //Constructor
    public Frog(PondApplication pond, String name, int x, int y) {
        this.type = "frog";
        this.pond = pond;
        this.name = name;
        this.x = x;
        this.y = y;
        this.mass = 10;
        this.tongueSpeed = 8;
        this.radius = 80;
        this.attackRadius = this.radius + 40;
        this.speed = 2;
    }
    public int getX() {
        return this.x;
    }
    public int getY () {
        return this.y;
    }
    public double getMass (){
        return this.mass;
    }
    public void move() {
        this.mass = this.mass - 0.1;  // if it can't eat, it starves
        if (!(pond.listFlies.isEmpty())){
            Fly nearestFly = findNearestFly();
            goTo(nearestFly.x, nearestFly.y);
            String success = eat(nearestFly);
            if (success.contains("eat")){pond.listFlies.remove(nearestFly);}  // the frog eats the fly, we erase it
            else if (success.contains("quick")) {nearestFly.move("flee");} // the frog tried to catch the fly so the fly flees
        }
        else {
            int x = ThreadLocalRandom.current().nextInt(1, 1800);
            int y = ThreadLocalRandom.current().nextInt(1, 600);
            goTo(x, y);
        }
    }
    public void goTo(int x2, int y2){
        if (this.isDead()){this.kill();}
        int nP = 10;                                                    //Number of pixels to go through each jump
        int futureX = 0, futureY = 0;

        if      (y2 - this.y < 0) {futureY = (int) (this.y - nP*speed);} //The fly is North
        else if (y2 - this.y > 0) {futureY = (int) (this.y + nP*speed);} //The fly is South
        if      (x2 - this.x < 0) {futureX = (int) (this.x - nP*speed);} //The fly is West
        else if (x2 - this.x > 0) {futureX = (int) (this.x + nP*speed);} //The fly is East

        if ((futureX > 100) && (futureX < (int) pond.screenSize.getWidth() - 200)){this.x = futureX;}  //Check if it does not get out of the screen
        if ((futureY > 100) && (futureY < (int) pond.screenSize.getHeight() - 200)) {this.y = futureY;}
        button.moveButton(this.x, this.y); //Move the related button
    }
    public String eat(Fly fly){
        if (this.canKill){
            if (Math.sqrt(Math.pow((this.x+0.5*this.radius) - (fly.x+0.5*fly.radius), 2) + Math.pow((this.y+0.5*this.radius) - (fly.y+0.5*fly.radius), 2)) <= this.radius + 0.5*(this.attackRadius-this.radius)){
                if (fly.isDead()){
                    return "The fly "+fly.name+" is already dead";
                }
                else {
                    if (this.tongueSpeed >= fly.speed){
                        this.mass = this.mass + fly.mass;
                        fly.kill();
                        return this.name + " eats the fly " + fly.name;
                    }
                    else {
                        return "the fly " + fly.name + " is too quick !!";
                    }
                }
            }
            else {
                return "the fly is too far";
            }
        }
        return "cannot kill";
    }
}