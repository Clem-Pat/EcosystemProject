package com.example.demo2;

public class PondObject {
    public String type;
    public String name;
    public double mass;
    public int x;
    public int y;
    public int attackRadius;
    public double speed;
    public GameButton button;
    public PondApplication pond;

    public PondObject(){
    }

    public boolean isDead(){return this.mass <= 0;}

    public void kill() {
        this.mass = 0;
        button.changeImage("dead"+type.substring(0,1).toUpperCase()+type.substring(1));
        if (type.equals("frog")){pond.listFrogs.remove(this);}
        else if (type.equals("fly")){pond.listFlys.remove(this);}
    }

    public void render(){
        if (type.equals("frog")){button = new GameButton(this, "frog", 80, 80);}
        else if (type.equals("fox")){button = new GameButton(this, "fox", 100, 100, 60, 60);}
        else{button = new GameButton(this, type);} //Une mouche ou un potentiel autre animal
    }
    public String toString(){
        if (this.isDead()) {return String.format("I'm a %s named %s. I'm DEAD", type, name);}
        else {return String.format("I'm a %s named %s. Mass : %.2f.", type, name, mass);}
    }
}
