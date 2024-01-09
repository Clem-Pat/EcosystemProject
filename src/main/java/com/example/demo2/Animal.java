package com.example.demo2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * La classe Animal représente notre entité de base de notre jeu d'écosystème.
 * Les animaux peuvent être de différents types tels que grenouille, mouche, renard, etc.
 */
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

    /**
     * Constructeur par défaut de la classe Animal.
     */
    public Animal(){
    }

    /**
     * Vérifie si l'animal est mort en fonction de sa masse.
     *
     * @return true si l'animal est mort, false sinon.
     */
    public boolean isDead(){return this.mass <= 0;}

    /**
     * Tue l'animal, met à jour sa masse, change son image, et le retire de la liste s'il est mort.
     */
    public void kill() {
        this.mass = 0;
        this.dateOfDeath = pond.day;
        button.changeImage("dead"+type.substring(0,1).toUpperCase()+type.substring(1));
        if (type.equals("frog")){pond.listFrogs.remove(this);}
        else if (type.equals("fly")){pond.listFlies.remove(this);}
        pond.listDeads.add(this);
    }

    /**
     * Gère le processus de vieillissement de l'animal.
     * Ajuste la vitesse, la vitesse de la langue pour les grenouilles, et tue l'animal s'il est trop vieux.
     */
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
    /**
     * Trouve l'objet le plus proche parmi une liste d'objets donnée.
     *
     * @param listObject Liste d'objets (nos animaux) parmi lesquels on cherche le plus proche.
     * @return L'objet le plus proche ou null si la liste est vide.
     */
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
    /**
     * Crée un bouton graphique associé à l'animal dans l'interface utilisateur.
     */
    public void render(){
        if (type.equals("fox")){button = new GameButton(this, "fox", 60, 60);}
        else{button = new GameButton(this, type);} //For a fly, a frog or another potential animal
    }
    /**
     * Fournit une représentation sous forme de chaîne de l'animal.
     *
     * @return Une chaîne de caractère représentant l'état (nom, masse, âge, vivant ou mort) actuel de l'animal.
     */
    public String toString(){
        if (this.isDead()) {return String.format("I'm a %s named %s. I'm DEAD", type, name);}
        else {return String.format("I'm a %s named %s. Mass : %.2f. Age : %o.", type, name, mass, age);}
    }
}
