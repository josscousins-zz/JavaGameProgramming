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

import java.util.Random;

public class Screen {
    private final int MAP_SIZE = 8;
   // private final int TILE_SIZE = 16;
    private int width;
    private int height;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; //could also do MAP_SIZE << 6 since (<<6) == (*64)

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
    public void render(int xOffset, int yOffset){

        for (int y = 0; y < height ; y++) {
            int yy = y +yOffset;
            //if(yy < 0 || yy >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x +xOffset;
               // if(xx < 0 || xx >= width) break;

                /*NO WRAP AROUND int tileIndex = (x >> 4) + (y >> 4) * MAP_SIZE ;   //16 = tile size 64 = map width */
                /*WRAP AROUND */ int tileIndex = ((xx >> 4) & (MAP_SIZE -1)) + ((yy >> 4)  & (MAP_SIZE -1)) * MAP_SIZE ;   //16 = tile size 64 = map width */

                //pixels[x + y * width] = tiles[tileIndex];
                pixels[x + y * width] = Sprite.grass1.pixels[(x&15) + (y&15) * Sprite.grass1.SIZE];
            }
        }
    }

}
