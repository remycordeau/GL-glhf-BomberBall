package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends GameObject {
    // attributes
    private int damage;
    private int range;
    //constructor
    public Bomb(int position_x, int position_y, int range) {
        super(position_x, position_y, 1);
        // initially, bomb inflict 1 damage
        this.damage=1;
        this.range=range;
        //appearance
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    //method explode
    public void explode(Maze map){
        int i=1;
        GameObject object;
        //UP
        while(i<=range){
            object=map.getGameObjectAt(position_x,position_y-i);
            if(object instanceof Wall){
               i=range;
            }
            object.getDamage(damage);
            i++;
        }
        i=1;
        //DOWN
        while(i<=range){
            object=map.getGameObjectAt(position_x,position_y+i);
            if(object instanceof Wall){
                i=range;
            }
            object.getDamage(damage);
            i++;
        }
        i=1;
        //RIGHT
        while(i<=range){
            object=map.getGameObjectAt(position_x+i,position_y);
            if(object instanceof Wall){
                i=range;
            }
            object.getDamage(damage);
            i++;
        }
        i=1;
        //LEFT
        while(i<=range){
            object=map.getGameObjectAt(position_x-i,position_y);
            if(object instanceof Wall){
                i=range;
            }
            object.getDamage(damage);
            i++;
        }
        map.handleDestruction();
    }
}
