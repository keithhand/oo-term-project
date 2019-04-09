package Controller;

import Model.FakeButton.FakeButton;
import Model.GameData;
import Model.Image;
import Model.Kevin;
import Model.MousePointer;
import Model.Text;
import View.MyWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static boolean running;
    public static boolean instructions;

    public static int INDEX_MOUSE_POINTER = 0; // in gameData.fixedObject
    public static int INDEX_Kevin = 1;

    public static int FPS = 60; // frames per second

    public static void main(String[] args){
        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();
        instructionScreen();
        initGame();
        gameLoop();
    }

    static void startScreen(){
        Font font = new Font("Courier New", Font.BOLD, 40);
        gameData.friendObject.add(new Text("Agalag", 255,200, Color.GREEN, font));
//        gameData.friendObject.add(new Text("START", 120,320, Color.WHITE, font));
//        gameData.friendObject.add(new Text("EXIT", 420,320, Color.WHITE, font));
        while (!instructions){
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        ImageIcon i = new ImageIcon("Images/howto_units.png");
//        i.paintIcon(this,g,50,50);
//    }

    static void instructionScreen(){
        gameData.clear();
        Font font = new Font("Courier New", Font.BOLD, 40);
        Font arrowFont = new Font("Courier New", Font.BOLD, 140);
        Font goalFont = new Font("Courier New", Font.BOLD, 20);
        gameData.friendObject.add(new Image("src/Images/howto_units.png", -80,160));
        gameData.friendObject.add(new Text("How to play", 199,100,Color.WHITE, font));
        gameData.friendObject.add(new Text("Goal: complete 5 waves by", 20, 130, Color.CYAN, goalFont));
        gameData.friendObject.add(new Text("eliminating all enemies on screen", 20, 150, Color.CYAN, goalFont));
        gameData.friendObject.add(new Text("User", 105,335, Color.GREEN, font));
        gameData.enemyObject.add(new Text("Enemies", 410,335, Color.RED, font));
        gameData.friendObject.add(new Text("Space to fire", 172,400,Color.ORANGE, font));
        gameData.friendObject.add(new Text("←", 20,550, Color.BLUE, arrowFont));
        gameData.friendObject.add(new Text("→", 220,550, Color.BLUE, arrowFont));
        gameData.friendObject.add(new Text("←", 340,550, Color.BLUE, arrowFont));
        gameData.friendObject.add(new Text("→", 540,550, Color.BLUE, arrowFont));
        gameData.friendObject.add(new Text("or", 300,600, Color.BLUE, font));
        gameData.friendObject.add(new Text("A", 60,600,Color.WHITE, font));
        gameData.friendObject.add(new Text("D", 260,600,Color.WHITE, font));
        gameData.friendObject.add(new Text("H", 380,600,Color.WHITE, font));
        gameData.friendObject.add(new Text("L", 580,600,Color.WHITE, font));
        gameData.friendObject.add(new Text("Movement", 240,700,Color.GRAY, font));





        while (!running){
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void initGame(){

        gameData.clear();
        gameData.fixedObject.add(new MousePointer(0,0));

        int x = Main.win.getWidth()/2;
        int y = Main.win.getHeight() - 100;
        gameData.fixedObject.add(new Kevin(x, y));

        //addUFOwithListener(100, 100);
    }

//    public static void addUFOwithListener(int x, int y){
//        var ufo = new UFO(x, y);
//        ufo.attachListener(new UFOObserverAddNew());
//        gameData.enemyObject.add(ufo);
//    }

    static void gameLoop(){

        running = true;

        while (running){
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
            processCollisions();
            gameData.update();
            win.canvas.render();
            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            long sleepTime = (long) (1000.0 / FPS - timeSpent);

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void processCollisions(){
        var Kevin =  (Kevin) Main.gameData.fixedObject.get(Main.INDEX_Kevin);

        for (var enemy: Main.gameData.enemyObject){
            if (Kevin.collideWith(enemy)){
                ++Kevin.hitCount;
                ++enemy.hitCount;
            }
        }
        for (var friend: Main.gameData.friendObject){
            for (var enemy: Main.gameData.enemyObject){
                if (friend.collideWith(enemy)){
                    ++friend.hitCount;
                    ++enemy.hitCount;
                }
            }
        }
    }
}
