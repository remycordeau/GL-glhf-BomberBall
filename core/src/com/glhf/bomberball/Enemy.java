package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    //attributes
    protected int strength;
    protected enum moves {UP, DOWN, RIGHT,LEFT}; // A mettre en global
    protected ArrayList<moves> way;
    protected int actualMove;

    // constructor
    protected Enemy(int position_x, int position_y, Texture appearance, int life) {
        super(position_x, position_y, appearance, life);
        this.actualMove = 0;
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
    }
}
