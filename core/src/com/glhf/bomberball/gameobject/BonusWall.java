package com.glhf.bomberball.gameobject;

public class BonusWall extends DestructibleWall {

    //attributes

    private Bonus bonus;

    protected BonusWall(int position_x, int position_y,Bonus bonus) {
        super(position_x, position_y);
        this.bonus=bonus;
        //apperance
    }
}
