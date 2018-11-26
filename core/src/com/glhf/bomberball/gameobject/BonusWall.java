package com.glhf.bomberball.gameobject;

public class BonusWall extends DestructibleWall {

    //attributes

    private Bonus bonus;

    // constructor

    protected BonusWall(int position_x, int position_y,Bonus bonus, int life) {
        super(position_x, position_y, life);
        this.bonus=bonus;
        //apperance
    }
}
