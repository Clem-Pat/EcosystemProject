package com.example.demo2;

import java.util.concurrent.ThreadLocalRandom;
/**
 * La classe Frog représente une grenouille dans le contexte de notre jeu.
 * Elle hérite de la classe Animal et possède des caractéristiques spécifiques aux grenouilles.
 */
public class Frog extends Animal{
    /**
     * La vitesse de la langue de la grenouille, qui va dépendre de son âge
     */
    public double tongueSpeed;
    /**
     * Constructeur de la classe Frog.
     *
     * @param pond L'application de l'écosystème auquel la grenouille appartient.
     * @param name Le nom de la grenouille.
     * @param x    La position horizontale initiale de la grenouille.
     * @param y    La position verticale initiale de la grenouille.
     */
    //Constructor
    public Frog(PondApplication pond, String name, int x, int y) {
        this.type = "frog";
        this.pond = pond;
        this.name = name;
        this.x = x;
        this.y = y;
        this.mass = 5;
        this.tongueSpeed = 1.5;
        this.radius = 80;
        this.attackRadius = this.radius + 40;
        this.speed = 2;
    }
    /**
     * Fait bouger la grenouille vers une position donnée.
     * Si une mouche (listFlies) est à proximité, la grenouille tente de la manger.
     * Si aucune mouche n'est présente, la grenouille se déplace aléatoirement.
     */
    public void move() {
        this.mass = this.mass - 0.1;  // if it can't eat, it starves
        if (this.mass <= 0){this.kill();}
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
    /**
     * Déplace la grenouille vers une position spécifiée.
     *
     * @param x2 La position horizontale vers laquelle la grenouille se déplace.
     * @param y2 La position verticale vers laquelle la grenouille se déplace.
     */
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
    /**
     * Tente de manger une mouche.
     *
     * @param fly La mouche cible de la tentative de la grenouille.
     * @return Un message indiquant le résultat de la tentative de la grenouille.
     */
    public String eat(Fly fly){
        if (this.canKill){
            if (Math.sqrt(Math.pow((this.x+0.5*this.radius) - (fly.x+0.5*fly.radius), 2) + Math.pow((this.y+0.5*this.radius) - (fly.y+0.5*fly.radius), 2)) <= this.radius + 0.5*(this.attackRadius-this.radius)){
                if (fly.isDead()){
                    return "The fly "+fly.name+" is already dead";
                }
                else {
                    if (this.tongueSpeed >= fly.speed){
                        this.mass += fly.mass;
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