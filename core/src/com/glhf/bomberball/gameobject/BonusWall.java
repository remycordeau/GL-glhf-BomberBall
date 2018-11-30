package com.glhf.bomberball.gameobject;

public class BonusWall extends DestructibleWall {

    //attributes

    private Bonus bonus;

    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     * @param bonus which bonus is inside the wall
     */
    protected BonusWall(int position_x, int position_y,Bonus bonus) {
        super(position_x, position_y);
        this.bonus=bonus;
        //apperance
    }
}
