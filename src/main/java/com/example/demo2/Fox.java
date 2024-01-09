package com.example.demo2;
/**
 * La classe Fox représente un renard dans le contexte de notre jeu.
 * Elle hérite de la classe Animal et possède des caractéristiques spécifiques à notre renard.
 */
public class Fox extends Animal{
    /**
     * Constructeur de la classe Fox.
     *
     * @param pond L'application de l'écosystème auquel le renard appartient.
     * @param name Le nom du renard.
     * @param x    La position horizontale initiale du renard.
     * @param y    La position verticale initiale du renard.
     */
    public Fox(PondApplication pond, String name, int x, int y) {
        this.type = "fox";
        this.pond = pond;
        this.name = name;
        this.mass = 5;
        this.x = x;
        this.y = y;
        this.radius = 100;
        this.attackRadius = this.radius;
        this.speed = 1;
    }
    /**
     * Déplace le renard dans une direction donnée en fonction des choix du joueur.
     *
     * @param direction La direction du déplacement ("Left", "Right", "Up", "Down").
     */
    public void move(String direction){
        this.mass = this.mass - 0.2; //It starves as it moves
        if (this.mass <= 0){this.kill();}
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
    /**
     * Tente de manger une grenouille.
     *
     * @param frog La grenouille cible du renard.
     * @return Un message indiquant le résultat de la tentative du renard.
     */
    public String eat(Frog frog){
        if (this.canKill){
            if (Math.sqrt(Math.pow((this.x+0.5*this.radius) - (frog.x+0.5*frog.radius), 2) + Math.pow((this.y+0.5*this.radius) - (frog.y+0.5*frog.radius), 2)) <=  this.radius + 0.5*(this.attackRadius-this.radius)){
                if (frog.isDead()){
                    return "The fly "+frog.name+" is already dead";
                }
                else{
                    this.mass = this.mass + frog.mass;

                    frog.kill();
                    return this.name + " eats the frog " + frog.name;
                }
            }
        }
        return "false";
    }
}
