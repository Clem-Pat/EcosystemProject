package com.example.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Frog extends PondObject{
    public double tongueSpeed;
    public static final double DEFAULT_MASS = 5;
    public static final double DEFAULT_TONGUE_SPEED = 8;

    //Constructor
    public Frog(PondApplication pond, String name, double mass, double tongueSpeed, int x, int y) {
        this.type = "frog";
        this.pond = pond;
        this.name = name;
        this.mass = mass;
        this.tongueSpeed = tongueSpeed;
        this.x = x;
        this.y = y;
        this.attackRadius = 100;
        this.speed = 1;
    }
    public Frog(PondApplication pond, String name, int x, int y) {
        this(pond, name, DEFAULT_MASS, DEFAULT_TONGUE_SPEED, x, y);
    }
    public String eat(Fly fly){
        if (Math.sqrt(Math.pow(this.x - fly.x, 2) + Math.pow(this.y - fly.y, 2)) <= 100){
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
    public void move() {
        if (!(pond.listFlys.isEmpty())){
            Fly nearestFly = findNearestFly(pond.listFlys);
            goTo(nearestFly.x, nearestFly.y);
            String success = eat(nearestFly);
            if (success.contains("eat")){pond.listFlys.remove(nearestFly);}  // the frog eats the fly, we erase it
            else if (success.contains("quick")) {nearestFly.move("flee");} // the frog tried to catch the fly so the fly flees
        }
        else {
            int x = ThreadLocalRandom.current().nextInt(1, 1800);
            int y = ThreadLocalRandom.current().nextInt(1, 600);
            goTo(x, y);
        }
    }
    public Fly findNearestFly(ArrayList<Fly> listFlys){
        if (listFlys.isEmpty()){ return null ;}
        else{
            ArrayList<Float> listDistances = new ArrayList<Float>();
            for (Fly fly : listFlys){
                listDistances.add((float) Math.sqrt(Math.pow(this.x - fly.x, 2) + Math.pow(this.y - fly.y, 2)));
            }
            return listFlys.get(listDistances.indexOf(Collections.min(listDistances)));
        }
    }
    public void goTo(int x2, int y2){
        this.mass = this.mass - 0.1; // if it can't eat, it starves
        if (this.isDead()){this.kill();}
        int nP = 10; //Number of pixels to go through each jump
        ArrayList<Float> listDistances = new ArrayList<Float>();
        listDistances.add((float) Math.sqrt(Math.pow(this.x - nP*speed - x2, 2) + Math.pow(this.y - nP*speed - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x            - x2, 2) + Math.pow(this.y - nP*speed - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x + nP*speed - x2, 2) + Math.pow(this.y - nP*speed - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x - nP*speed - x2, 2) + Math.pow(this.y            - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x            - x2, 2) + Math.pow(this.y            - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x + nP*speed - x2, 2) + Math.pow(this.y            - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x - nP*speed - x2, 2) + Math.pow(this.y + nP*speed - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x            - x2, 2) + Math.pow(this.y + nP*speed - y2, 2)));
        listDistances.add((float) Math.sqrt(Math.pow(this.x + nP*speed - x2, 2) + Math.pow(this.y + nP*speed - y2, 2)));

        int minIndex = listDistances.indexOf(Collections.min(listDistances));
        if (minIndex == 0){this.x = (int) (this.x - nP*speed); this.y = (int) (this.y - nP*speed);}
        if (minIndex == 1){this.x = this.x           ; this.y = (int) (this.y - nP*speed);}
        if (minIndex == 2){this.x = (int) (this.x + nP*speed); this.y = (int) (this.y - nP*speed);}
        if (minIndex == 3){this.x = (int) (this.x - nP*speed); this.y = this.y           ;}
        if (minIndex == 5){this.x = (int) (this.x + nP*speed); this.y = this.y           ;}
        if (minIndex == 6){this.x = (int) (this.x - nP*speed); this.y = (int) (this.y + nP*speed);}
        if (minIndex == 7){this.x = this.x           ; this.y = (int) (this.y + nP*speed);}
        if (minIndex == 8){this.x = (int) (this.x + nP*speed); this.y = (int) (this.y + nP*speed);}
        button.moveButton(this.x, this.y);
    }
}