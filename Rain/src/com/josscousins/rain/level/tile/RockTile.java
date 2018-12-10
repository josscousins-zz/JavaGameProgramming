package com.josscousins.rain.level.tile;

import com.josscousins.rain.Graphics.Screen;
import com.josscousins.rain.Graphics.Sprite;

public class RockTile extends Tile{

    public RockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4,y << 4,this);
    }
    public boolean solid(){
        return true;
    }
}
