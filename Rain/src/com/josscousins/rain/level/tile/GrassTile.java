package com.josscousins.rain.level.tile;

import com.josscousins.rain.Graphics.Screen;
import com.josscousins.rain.Graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4,y << 4,this);
    }
}
