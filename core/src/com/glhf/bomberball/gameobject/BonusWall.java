package com.glhf.bomberball.gameobject;

public class BonusWall extends DestructibleWall {

    //attributes
    private Bonus bonus;

    /**
     * constructor
     * @param bonus which bonus is inside the wall
     */
    protected BonusWall(Bonus bonus) {
        super();
        this.bonus = bonus;
        //TODO set the BonusWall sprite
    }
}
