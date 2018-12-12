package com.glhf.bomberball.config;

public class GameMultiConfig extends GameConfig {

    public GameMultiConfig() {}

    public int player_count = 4;

    public String[] player_skins = {"knight_m", "knight_f", "elf_m", "wizzard_f"};

    public static GameMultiConfig get(String config_name){
        return get(config_name, GameMultiConfig.class);
    }
}
