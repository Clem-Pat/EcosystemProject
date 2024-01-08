package com.example.demo2;

public class Fox extends Animal{

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
    public void move(String direction){
        this.mass = this.mass - 0.2; //It starves as it moves
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
