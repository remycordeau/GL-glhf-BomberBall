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
        switch (this.bonus.getType()) {
            case SPEED:
                this.sprite = Graphics.Sprites.get("speedWall");
                break;
            case BOMB_RANGE:
                this.sprite = Graphics.Sprites.get("rangeWall");
                break;
            case BOMB_NUMBER:
                this.sprite = Graphics.Sprites.get("bombWall");
                break;
        }
        bonus.initialize();
    }

    @Override
    public void dispose() {
        cell.addGameObject(bonus);
        super.dispose();
    }
}
