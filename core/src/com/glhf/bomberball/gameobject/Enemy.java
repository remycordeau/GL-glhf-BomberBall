package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.utils.Directions;

import java.util.ArrayList;

public abstract class Enemy extends Character {

    protected int strength = 1;

    //protected transient  int actual_move; //transcient to deserialize
    protected transient ArrayList<Directions> way;

    protected Enemy(String skin, int life, int initial_moves, int strength, ArrayList<Directions> way) {
        super(skin, life, initial_moves);
        this.strength = strength;
        this.way = way;

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
    public void setWay(ArrayList<Directions> way) {
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
