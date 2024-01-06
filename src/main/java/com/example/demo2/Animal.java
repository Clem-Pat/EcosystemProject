package com.example.demo2;

import java.util.ArrayList;
import java.util.Collections;

public class Animal {
    public String type;
    public String name;
    public double mass;
    public int x;
    public int y;
    public int radius;
    public int attackRadius;
    public double speed;
    public GameButton button;
    public PondApplication pond;

    public Animal(){
    }
    public boolean isDead(){return this.mass <= 0;}
    public void kill() {
        this.mass = 0;
        button.changeImage("dead"+type.substring(0,1).toUpperCase()+type.substring(1));
        if (type.equals("frog")){pond.listFrogs.remove(this);}
        else if (type.equals("fly")){pond.listFlies.remove(this);}
    }
    public Animal findNearestObject(ArrayList<Animal> listObject){
        if (listObject.isEmpty()){ return null ;}
        else{
            ArrayList<Float> listDistances = new ArrayList<>();
            for (Animal obj : listObject){
                listDistances.add((float) Math.sqrt(Math.pow(this.x - obj.x, 2) + Math.pow(this.y - obj.y, 2)));
            }
            return listObject.get(listDistances.indexOf(Collections.min(listDistances)));
        }
    }
    public Fly findNearestFly(){return (Fly) findNearestObject(pond.listFlies);}
    public Frog findNearestFrog(){return (Frog) findNearestObject(pond.listFrogs);}
    public void render(){
        if (type.equals("fox")){button = new GameButton(this, "fox", 60, 60);}
        else{button = new GameButton(this, type);} //For a fly, a frog or another potential animal
    }
    public String toString(){
        if (this.isDead()) {return String.format("I'm a %s named %s. I'm DEAD", type, name);}
        else {return String.format("I'm a %s named %s. Mass : %.2f.", type, name, mass);}
    }
}
