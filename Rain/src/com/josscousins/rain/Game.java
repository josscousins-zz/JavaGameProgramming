package com.josscousins.rain;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 2103324365296745661L;
    //GameWindow variable
    public static final int WIDTH = 300;
    public static final int HEIGHT = (WIDTH/ 16) * 9;
    public static final int SCALE = 3;

    private Thread thread;

    //Game State variables
    private boolean running =false;

    //Game Window
    private JFrame frame;

    public Game(){
        final Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(SIZE);
        frame = new JFrame();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();//set size of frame according to components
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); //center window
        game.frame.setVisible(true);

        game.start();
    }
    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    public synchronized void stop(){

        try {
           thread.join();
        }catch (InterruptedException e){
           e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running){
            System.out.println("Running...");
        }
    }
}
