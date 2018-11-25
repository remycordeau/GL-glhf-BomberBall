package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class BombRangeBoost extends Bonus{

    // constructor
    public BombRangeBoost(int position_x, int position_y) {
        super(position_x,position_y);
        //to get the bonus in player.bonus_owned
        name="BombRangeBoost";
        //appearance
    }
}
