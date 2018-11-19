package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {
    private int number_bomb_remaining;

    // player move
    @Override
    public void moveRight(){
        position_x+=1;
    }
    @Override
    public void moveLeft(){
        position_x-=1;
    }
    //
    @Override
    public void getDamage(int damage){
        life -= damage;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
