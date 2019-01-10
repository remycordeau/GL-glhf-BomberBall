package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class BonusWall extends DestructibleWall {

    public Bonus bonus;

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
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        //this.sprite = Graphics.Sprites.get("crate_speed");
        this.sprite = bonus.getSprite();
        bonus.initialize();
    }

    @Override
    public void dispose() {
        cell.addGameObject(bonus);
        super.dispose();
    }
}
