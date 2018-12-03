package com.glhf.bomberball.config;

public class GameConfig extends Config {

    // Walls
    public int wall_life = 1;

    // Bombs
    public int initial_bomb_number = 1;
    public int initial_bomb_range = 3;
    public int bomb_damage = 1;

    // Player
    public int player_life = 1;
    public int initial_player_moves = 5;
    public int player_count = 4;
    public String[] player_skins = {"knight_m", "knight_f", "elf_m", "wizzard_f"};

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
}
