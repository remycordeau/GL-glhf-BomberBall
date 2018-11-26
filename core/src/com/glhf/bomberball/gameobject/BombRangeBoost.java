package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.Bonus;

public class BombRangeBoost extends Bonus {

    // constructor
    public BombRangeBoost(int position_x, int position_y) {
        super(position_x,position_y);
        //to get the bonus in player.bonus_owned
        name="BombRangeBoost";
        //appearance
    }
}
