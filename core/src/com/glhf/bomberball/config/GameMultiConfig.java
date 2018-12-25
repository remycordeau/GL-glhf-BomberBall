package com.glhf.bomberball.config;

import com.glhf.bomberball.screens.MultiMenuScreen;
public class GameMultiConfig extends GameConfig {

    public GameMultiConfig() {
        // TODO : erase this line, it's for test purposes only
        System.out.println("Loading config\nPlayer 1 is: " + MultiMenuScreen.Playable[MultiMenuScreen.p1_id]);
    }

    public int player_count = 4;

    public String[] player_skins = {MultiMenuScreen.Playable[MultiMenuScreen.p1_id], MultiMenuScreen.Playable[MultiMenuScreen.p2_id],MultiMenuScreen.Playable[MultiMenuScreen.p3_id], MultiMenuScreen.Playable[MultiMenuScreen.p4_id]};

    public static GameMultiConfig get(String config_name){
        return get(config_name, GameMultiConfig.class);
    }
}
