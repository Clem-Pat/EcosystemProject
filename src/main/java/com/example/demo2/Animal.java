package com.example.demo2;

import java.util.ArrayList;
import java.util.Collections;

public class Animal {
    public String type = "none";
    public String name = "none";
    public int age = 0;
    public double mass = 0;
    public double speed = 0;
    public int x = 0;
    public int y = 0;
    public int radius = 0;
    public int attackRadius = 0;
    public boolean canKill = false;
    public double dateOfDeath = Double.POSITIVE_INFINITY;
    public GameButton button;
    public PondApplication pond;

    public Animal(){
    }
    public boolean isDead(){return this.mass <= 0;}
    public void kill() {
        this.mass = 0;
        this.dateOfDeath = pond.day;
        button.changeImage("dead"+type.substring(0,1).toUpperCase()+type.substring(1));
        if (type.equals("frog")){pond.listFrogs.remove(this);}
        else if (type.equals("fly")){pond.listFlies.remove(this);}
        pond.listDeads.add(this);
    }
    public void aging(){
        this.age += 1;
        if (this.age > 3){this.canKill = true;}
        if (this.age < 24){
            if (this.speed < 2){
                this.speed += 0.1;          //The speed of young animals increases
            }
            if (this.type.equals("frog")){  //The tongueSpeed of frogs grow each day
                Frog frog = (Frog) this;
                frog.tongueSpeed += 0.2;
            }
        }
        else{
            if (this.speed > 1){
                this.speed -= 0.1;          //The speed of old animals decreases
            }
            if (this.type.equals("frog")){      //If the frog is too old, its tongueSpeed decreases
                Frog frog = (Frog) this;
                frog.tongueSpeed -= 0.1;
            }
            if (!this.type.equals("fox") && this.age > 50){this.kill();}
        }
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
    public String findDirectionOfNearestObject(Animal nearestObject){
        String result = "";
        result = result + String.format("%.2f", Math.sqrt(Math.pow(this.x - nearestObject.x, 2) + Math.pow(this.y - nearestObject.y, 2))) + " pixels dans la direction : ";
        if      (nearestObject.y - this.y < - attackRadius) {result = result + "N";}
        else if (nearestObject.y - this.y > attackRadius)   {result = result + "S";}
        if      (nearestObject.x - this.x < - attackRadius) {result = result + "O";}
        else if (nearestObject.x - this.x > attackRadius)   {result = result + "E";}
        return result;
    }
    public String findDirectionOfNearestFrog(){
        return "Grenouille la plus proche : " + findDirectionOfNearestObject(findNearestFrog());
    }
    public String findDirectionOfNearestFly(){
        return "Mouche la plus proche : " + findDirectionOfNearestObject(findNearestFly());
    }
    public void render(){
        if (type.equals("fox")){button = new GameButton(this, "fox", 60, 60);}
        else{button = new GameButton(this, type);} //For a fly, a frog or another potential animal
    }
    public String toString(){
        if (this.isDead()) {return String.format("I'm a %s named %s. I'm DEAD", type, name);}
        else {return String.format("I'm a %s named %s. Mass : %.2f. Age : %o.", type, name, mass, age);}
    }
}
