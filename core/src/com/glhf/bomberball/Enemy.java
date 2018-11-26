package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    //attributes
    protected int strength;
    protected ArrayList<Constants.moves> way;
    protected int actualMove;

    // constructor
    protected Enemy(int position_x, int position_y, int life) {
        super(position_x, position_y, life);
        this.actualMove = 0;
        //this.strength=path
    }

    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    public void followWay() {
        switch (way.get(actualMove)) {
            case UP:
                this.moveUp();
            case DOWN:
                this.moveDown();
            case LEFT:
                this.moveLeft();
            case RIGHT:
                this.moveRight();
        }
        actualMove += 1;
    }
}
