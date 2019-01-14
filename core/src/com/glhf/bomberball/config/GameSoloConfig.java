package com.glhf.bomberball.config;

public class GameSoloConfig extends GameConfig {

    public String player_skin = "knight_m";
    public int highscore = 145970;
    public int last_level_played = 0;
    public int last_level_unlocked = 0;
    public boolean bonus_activated = true;

    //Settings maze
    public double probFreeCase = 1.0/3;

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

    /**
     * Default constructor with the default configuration values
     */
    public GameSoloConfig(){
    }

    public static GameSoloConfig get(String config_name) {
        return get(config_name, GameSoloConfig.class);
    }

    public static GameSoloConfig get() {
        return get("config_game_solo");
    }
    public void exportConfig() {
        exportConfig("config_game_solo");
    }



    /**
     * resets all the levels to the initial state (only the first level is unlocked) and saves it in the config file.
     */
    public void resetLevels()
    {
        last_level_unlocked=0;
        exportConfig();
    }
}
