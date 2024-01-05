package com.example.demo2;

import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PondApplication extends javafx.application.Application {

    public JFrame frame = new GameFrame(this, "Pond");
    static GamePanel panel;
    public Fox fox;
    public static ArrayList<Frog> listFrogs = new ArrayList<>();
    public static ArrayList<Fly> listFlys = new ArrayList<>();
    private int i; //Number of flies created
    private int j; //Number of frogs created
    private Dimension screenSize;


    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image image = null;
        final URL imageURL = PondApplication.class.getResource("/com/example/demo2/pond.jpg");
        if (imageURL != null) {
            image = ((new ImageIcon(imageURL)).getImage()).getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), java.awt.Image.SCALE_SMOOTH);
        }
        panel = new GamePanel(image);
        panel.setLayout(null);
        JButton button = new JButton("Jour suivant");
        button.setBounds(10, 10, 150, 30);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {goToNextDay();}
        });
        panel.add(button);
        initiateGame(panel);
        frame.setFocusable(true);
        frame.add(panel);
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setIconImage (new ImageIcon ("/com/example/demo2/fly.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void addFly(){
        int x = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getWidth() - 150);
        int y = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getHeight() - 100);
        Fly fly = new Fly(this, Integer.toString(i), x, y);
        fly.render();
        listFlys.add(fly);
    }
    private void addFrog(){
        ArrayList<String> listNamesFrogs = new ArrayList<>(Arrays.asList("Noé", "Thibaut", "Benjamin", "Baptiste", "Tea", "Alice", "Antoine"));
        int j1 = j%listNamesFrogs.size();
        int x = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getWidth() - 150);
        int y = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getHeight() - 100);
        Frog frog = new Frog(this, listNamesFrogs.get(j1), x, y);
        frog.render();
        listFrogs.add(frog);
    }
    private void initiateGame(GamePanel panel) {

//        Create Frogs
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int randomNum = ThreadLocalRandom.current().nextInt(3, 5 + 1);
        for (j=0; j<randomNum; ++j){
            addFrog();
        }
        System.out.println(listFrogs.size() + " frogs in the pond");

//        Create Flys
        int randomNum2 = ThreadLocalRandom.current().nextInt(3, 5 + 1);
        for (i=0; i<randomNum2; ++i){
            addFly();
        }
        System.out.println(listFlys.size() + " flys in the pond");

//        Create Fox
        int x = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getWidth() - 150);
        int y = ThreadLocalRandom.current().nextInt(10, (int) screenSize.getHeight() - 100);
        fox = new Fox(this, "Bruno", 7., x, y);
        fox.render();
        System.out.println(1 + " fox in the pond");
    }

    void goToNextDay(){
        if (listFlys.size() < 3){                   //We spawn a fly if they are not enough in the game
            i++;
            addFly();
        }
        if (listFrogs.size() < 3){                  //We spawn a frog if they are not enough in the game
            j++;
            addFrog();
        }
        try {
            for (Frog frog : listFrogs){
                String success1 = fox.eat(frog);
                if (!success1.contains("false")){   //If the fox eats a frog, we print a message
                    System.out.println(success1);
                }
                frog.move();                //We make every frog running to the nearest fly
            }
        }
        catch(Exception e) {                        //If the fox eats a frog this raises an error due to the for loop but it does not matter
        }
        for (Fly fly : listFlys){
            fly.move("random");              //Every fly flies randomly...
            fly.sting(fox);                         //... and try to sting the fox
        }
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}

