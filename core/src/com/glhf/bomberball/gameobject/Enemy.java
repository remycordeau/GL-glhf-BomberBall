package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Character;
import com.glhf.bomberball.gameobject.Player;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    //attributes
    protected int strength;
    protected ArrayList<Constants.moves> way;
    protected int actual_move;

    // constructor
    protected Enemy(int position_x, int position_y, int life) {
        super(position_x, position_y, life);
        this.actual_move = 0;
        //this.strength=path
    }

    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    //set way
    public void setWay(ArrayList<Constants.moves> way) {
        this.way = way;
    }

    public void followWay() {
        switch (way.get(actual_move)) {
            case UP:
                this.moveUp();
            case DOWN:
                this.moveDown();
            case LEFT:
                this.moveLeft();
            case RIGHT:
                this.moveRight();
        }
        actual_move += 1;
    }
}
