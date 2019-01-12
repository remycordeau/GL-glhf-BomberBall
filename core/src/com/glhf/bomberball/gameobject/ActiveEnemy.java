package com.glhf.bomberball.gameobject;

public class ActiveEnemy extends Enemy {

    public ActiveEnemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves, strength);
    }

    @Override
    public void createAI() {
        this.way = this.longest_way_moves_sequence(Enemy.construct_ways(null, this.getCell()));
    }
}
