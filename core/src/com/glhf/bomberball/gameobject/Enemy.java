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


    protected Enemy() {
        super();
        this.actual_move = 0;
    }

    /**
     * set damage to a player that the enemy touch
     * @param player the player touched by the ennemy
     */
    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    /**
     * give a way to follow to the enemy
     * @param way
     */
    public void setWay(ArrayList<Constants.moves> way) {
        this.way = way;
    }

    /**
     * the enemy has to follow the way he receveid
     */
    // TODO : réecrire followWay
    public void followWay() {
        /*switch (way.get(actual_move)) {
            case UP:
                this.moveUp();
            case DOWN:
                this.moveDown();
            case LEFT:
                this.moveLeft();
            case RIGHT:
                this.moveRight();
        }
        actual_move += 1;*/
    }
}
