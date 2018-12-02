package com.glhf.bomberball.config;

public abstract class GameConfig extends Config {

    public GameConfig() {}

    public int wall_life = 1;

    public int bomb_inital_number = 1;
    public int bomb_inital_range = 3;
    public int bomb_damage = 1;

    public int player_life = 1;
    public int player_inital_moves = 5;
}
