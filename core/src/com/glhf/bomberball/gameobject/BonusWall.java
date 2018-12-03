package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class BonusWall extends DestructibleWall {

    private Bonus bonus;

    public BonusWall() {
        super();
    }

    /**
     * constructor
     * @param bonus which bonus is inside the wall
     */
    public BonusWall(Bonus bonus) {
        super();
        this.bonus = bonus;
    }
}
