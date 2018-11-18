package com.josscousins.rain.entity;

import com.josscousins.rain.Graphics.Screen;
import com.josscousins.rain.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){

    }
    public void render(Screen sceen){

    }
    public void remove(){

        removed = true;
    }
    public boolean isRemoved(){
        return removed;
    }


}
