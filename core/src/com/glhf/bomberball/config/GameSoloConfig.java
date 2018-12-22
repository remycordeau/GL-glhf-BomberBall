package com.glhf.bomberball.config;

public class GameSoloConfig extends GameConfig {

    public String player_skin = "knight_m";

    // Enemies
    public int activeEnemy_life = 1;
    public int activeEnemy_strength = 1;
    public int activeEnemy_moves = 5;

    public int passiveEnemy_life = 1;
    public int passiveEnemy_strength = 1;
    public int passiveEnemy_moves = 5;

    public int aggressiveEnemy_life = 1;
    public int aggressiveEnemy_strength = 1;
    public int aggressiveEnemy_moves = 5;

    public static GameSoloConfig get(String config_name){
        return get(config_name, GameSoloConfig.class);
    }
}
