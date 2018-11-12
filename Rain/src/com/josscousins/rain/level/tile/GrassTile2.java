package com.josscousins.rain.level.tile;

import com.josscousins.rain.Graphics.Screen;
import com.josscousins.rain.Graphics.Sprite;

public class GrassTile2 extends Tile {

    public GrassTile2(Sprite sprite) {
        super(sprite);
    }
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4,y << 4,this);
    }
}
