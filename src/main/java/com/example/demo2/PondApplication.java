package com.example.demo2;

import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe PondApplication est la classe  principale de notre jeu.
 * Elle étend la classe Application de JavaFX pour gérer l'interface utilisateur graphique.
 */
public class PondApplication extends javafx.application.Application {
    /**
     * La fenêtre principale de l'application.
     */

    public JFrame frame = new GameFrame(this, "Pond");
    /**
     * Le panneau principal de l'application.
     */
    public GamePanel panel = new GamePanel(this);
    /**
     * Le renard.
     */
    public Fox fox;
    /**
     * Liste des grenouilles présentes dans l'écosystème.
     */
    public ArrayList<Animal> listFrogs = new ArrayList<>();
    /**
     * Liste des mouches présentes dans l'écosystème.
     */
    public ArrayList<Animal> listFlies = new ArrayList<>();
    /**
     * Liste des animaux morts de l'écosystème.
     */
    public ArrayList<Animal> listDeads = new ArrayList<>();
    //Useful to keep dead animal to mind then forget them and make their corpse disappear
    /**
     * Nombre de mouches créées.
     */
    private int i; //Number of flies created
    /**
     * Nombre de grenouilles créées.
     */
    private int j; //Number of frogs created
    /**
     * Dimension de l'écran.
     */
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Jour courant dans la simulation.
     */
    public int day = 1;
    /**
     * Méthode principale de notre application.
     *
     * @param stage Le stage principal de l'application.
     */

    @Override
    public void start(Stage stage){
        panel.setLayout(null);
        JButton button = new JButton("Next day");
        button.setBounds(10, 10, 150, 30);
        button.addActionListener(e -> goToNextDay());
        panel.add(button);
        initiateGame();
        frame.setFocusable(true);
        frame.add(panel);
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setIconImage (new ImageIcon ("/com/example/demo2/fly.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * Ajoute une mouche.
     */
    public void addFly(){
        int x = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getWidth() - 200);
        int y = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getHeight() - 200);
        Fly fly = new Fly(this, Integer.toString(i), x, y);
        fly.render();
        listFlies.add(fly);
    }
    /**
     * Ajoute une grenouille (nommée parmi les noms listés).
     */
    public void addFrog(){
        ArrayList<String> listNamesFrogs = new ArrayList<>(Arrays.asList("Noé", "Thibaut", "Benjamin", "Baptiste", "Tea", "Alice", "Antoine", "Clément"));
        int j1 = j%listNamesFrogs.size();
        int x = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getWidth() - 200);
        int y = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getHeight() - 200);
        Frog frog = new Frog(this, listNamesFrogs.get(j1), x, y);
        frog.render();
        listFrogs.add(frog);
    }
    /**
     * Initialise la simulation.
     */
    public void initiateGame() {

//        Create Frogs
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int randomNum = ThreadLocalRandom.current().nextInt(3, 5 + 1);
        for (j=0; j<randomNum; ++j){
            addFrog();
        }
        System.out.println(listFrogs.size() + " frogs in the pond");

//        Create Flies
        int randomNum2 = ThreadLocalRandom.current().nextInt(3, 5 + 1);
        for (i=0; i<randomNum2; ++i){
            addFly();
        }
        System.out.println(listFlies.size() + " flies in the pond");

//        Create Fox
        int x = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getWidth() - 200);
        int y = ThreadLocalRandom.current().nextInt(100, (int) screenSize.getHeight() - 200);
        fox = new Fox(this, "Bruno", x, y);
        fox.render();
        System.out.println(1 + " fox in the pond");
        System.out.println("\nJour "+day);
        System.out.println(fox.findDirectionOfNearestFrog());
        System.out.println(fox.findDirectionOfNearestFly());
    }
    /**
     * Passe au jour suivant dans la simulation.
     */
    public void goToNextDay(){
        day += 1;
        if (listFlies.size() < 4){                   //We spawn a fly if they are not enough in the game
            i++;
            addFly();
        }
        if (listFrogs.size() < 4){                    //We spawn a frog if they are not enough in the game
            j++;
            addFrog();
        }
        fox.aging();
        try {
            for (Animal frog : listFrogs){
                String success1 = fox.eat((Frog) frog);
                if (!success1.contains("false")){    //If the fox eats a frog, we print a message
                    System.out.println(success1);
                }
                ((Frog) frog).move();                //We make every frog running to the nearest fly
                frog.aging();
            }
        }
        catch(Exception ignored) {}                  //If the fox eats a frog or if the frog ages to death : this raises an error due to the for loop, but it does not matter
        try{
            for (Animal fly : listFlies){
                ((Fly) fly).move("random");    //Every fly flies randomly...
                ((Fly) fly).sting(fox);              //... and try to sting the fox
                fly.aging();                         //Making flies aging can kill them so erase them from the list we are currently looking at. This raises an error
            }
        }
        catch (Exception ignored){}
        try{
            for (Animal animal : listDeads){             // we make their corpse disappear 10 days after their death
                if (day - animal.dateOfDeath > 10){
                        animal.button.hideButton();
                        this.listDeads.remove(animal);   //removing an animal from the list we are looking at can raise an error
                }
            }
        }
        catch (Exception ignored){}
        System.out.println("\nJour "+day);
        System.out.println(fox.findDirectionOfNearestFrog());
        System.out.println(fox.findDirectionOfNearestFly());
    }
}

