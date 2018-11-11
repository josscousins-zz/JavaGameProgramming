package com.josscousins.rain;

import com.josscousins.rain.Graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 2103324365296745661L;
    //GameWindow variable
    public static final int WIDTH = 300;
    public static final int HEIGHT = (WIDTH/ 16) * 9; // ~168
    public static final int SCALE = 3;

    private Thread thread;

    //Game State variables
    private boolean running =false;

    //screen
    private Screen screen;

    //Game Window
    private JFrame frame;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = (    (DataBufferInt) image.getRaster().getDataBuffer()   ).getData()    ;

    public Game(){
        final Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(SIZE);

        screen = new Screen(WIDTH,HEIGHT);

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
        long lastTime = System.nanoTime();
        long timer =System.currentTimeMillis();
        final double nanoSeconds =1000000000.0 / 60.0;
        double delta = 0;
        int frames =0;
        int updates = 0;

        while(running){
            long timeNow = System.nanoTime();
            delta += (timeNow - lastTime) /nanoSeconds; //~2610 /166,666,666
            lastTime = timeNow;
            while(delta >=1){
                update();   //will be restricted to 60FPS in future
                updates++;
                delta--;
            }

            render();
            frames++;

            //once per second
            if(System.currentTimeMillis() - timer > 1000){
                timer+= 1000;

                frame.setTitle( String.format("Updates Per Second: %d FPS: %d",updates,frames));
                updates =0;
                frames = 0;
            }
        }
        stop();
    }
    public void update(){

    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }


        Graphics g = bs.getDrawGraphics();
        //draw graphics
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight()); //component class

        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.dispose(); //release system resources
        bs.show();
    }
}
