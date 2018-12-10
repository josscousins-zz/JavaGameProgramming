package com.josscousins.rain.level.tile;

import com.josscousins.rain.Graphics.Screen;
import com.josscousins.rain.Graphics.Sprite;

public class Tile {
    public int x,y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile grass2 = new GrassTile(Sprite.grass2);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }
    public boolean solid(){
        return false;
    }
}
