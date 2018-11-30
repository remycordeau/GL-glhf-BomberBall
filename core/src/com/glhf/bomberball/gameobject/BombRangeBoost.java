package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.Bonus;

public class BombRangeBoost extends Bonus {

    /**
     * constructor
     * @param position_x x axis position of the BombRangeBoost
     * @param position_y y axis position of the BombRangeBoost
     *
     */
    public BombRangeBoost(int position_x, int position_y) {
        super(position_x,position_y);
        //to get the bonus in player.bonus_owned
        name="BombRangeBoost";
        //appearance
    }
}
