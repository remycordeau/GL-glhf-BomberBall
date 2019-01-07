package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.utils.Constants;

import java.util.ArrayList;

public class ActiveEnemy extends Enemy {

    public ActiveEnemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves, strength, null);
        this.setWay(this.find_way());
    }

    private ArrayList<Constants.moves> find_way(){
        this.getCell();

    }

}
