/*
    BITWISE OPERATORS: useful when performing calculations with powers of 2
                       Better for performance
    x >> y: right shift - shifts the base2(x) right by y positions
    x << y: left shift  - shifts the base2(x) left by y positions
    example; calculate 64 / 16
            64 / 16 = 4
            64 = 1000000 (base 2)
            16 =   10000 (base 2)
             4 =     100 (base 2)
       64 >> 4 = 0000100 // shift all 1's 4 places right
    x & y : copies only were a and b have 1's
            12 & 48 = 1100 & 101100 -> 001100
 */
package com.josscousins.rain.Graphics;

import com.josscousins.rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    private final int MAP_SIZE = 8;
   // private final int TILE_SIZE = 16;
    public int width;
    public int height;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; //could also do MAP_SIZE << 6 since (<<6) == (*64)

    public int xOffset, yOffset;

    private Random r = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = r.nextInt(0xffffff);
        }
    }
    

    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
/*    public void render(int xOffset, int yOffset){
        for (int y = 0; y < height ; y++) {
            int yPixel = y + yOffset;
            if(yPixel < 0 || yPixel >= height) continue;
            for (int x = 0; x < width; x++) {
                int xPixel = x + xOffset;
                if(xPixel < 0 || xPixel >= width) continue;
                pixels[(x + xOffset) + (y + yOffset) * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.SIZE];
            }
        }
    }*/
    public void renderTile(int xp, int yp, Tile tile){
        xp -=xOffset;
        yp -=yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            //ya == absolute Y
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                //xa == absolute X
                int xa = x + xp;
                if(xa < 0 || xa >= width ||ya < 0 || ya >=height)break;
                pixels[xa+ya*width] = tile.sprite.pixels[x+y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


}
