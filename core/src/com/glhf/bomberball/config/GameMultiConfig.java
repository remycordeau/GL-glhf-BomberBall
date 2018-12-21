package com.glhf.bomberball.config;

import com.glhf.bomberball.screens.MultiMenuScreen;
public class GameMultiConfig extends GameConfig {

    public GameMultiConfig() {
        // TODO : erase this line, it's for test purposes only
        System.out.println(MultiMenuScreen.selectPlayer[MultiMenuScreen.p1_id]);
    }

    public int player_count = 4;

    public String[] player_skins = {MultiMenuScreen.selectPlayer[MultiMenuScreen.p1_id], MultiMenuScreen.selectPlayer[MultiMenuScreen.p2_id],MultiMenuScreen.selectPlayer[MultiMenuScreen.p3_id], MultiMenuScreen.selectPlayer[MultiMenuScreen.p4_id]};

    public static GameMultiConfig get(String config_name){
        return get(config_name, GameMultiConfig.class);
    }
}
