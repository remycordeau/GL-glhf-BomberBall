package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.Node;

import java.util.ArrayList;

public class ActiveEnemy extends Enemy {

    public ActiveEnemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves, strength);
        this.way = this.longest_way_moves_sequence(new Node(null, this.getCell()));
    }
}
