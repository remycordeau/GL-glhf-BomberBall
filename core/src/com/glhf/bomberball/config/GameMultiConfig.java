package com.glhf.bomberball.config;

import com.glhf.bomberball.screens.MultiMenuScreen;
public class GameMultiConfig extends GameConfig {
    public int player_count;
    public String[] player_skins;
    public GameMultiConfig() {
        System.out.println("Loading config"+"" +
                "\nPlayer 1 is: " + MultiMenuScreen.Playable[MultiMenuScreen.p1_id]
                +"\nPlayer 2 is:" + MultiMenuScreen.Playable[MultiMenuScreen.p2_id]
                +"\nPlayer 3 is:" + MultiMenuScreen.Playable[MultiMenuScreen.p3_id]
                +"\nPlayer 4 is:" + MultiMenuScreen.Playable[MultiMenuScreen.p4_id]);
        player_count= 4;
        if (MultiMenuScreen.p3_id==6){player_count--;}
        if (MultiMenuScreen.p4_id==6){player_count--;}
        System.out.println("Number of players: "+ player_count);
        player_skins = new  String[4];
        player_skins[0]= MultiMenuScreen.Playable[MultiMenuScreen.p1_id];
        player_skins[1]= MultiMenuScreen.Playable[MultiMenuScreen.p2_id];
        player_skins[2]= MultiMenuScreen.Playable[MultiMenuScreen.p3_id];
        player_skins[3]= MultiMenuScreen.Playable[MultiMenuScreen.p4_id];
        System.out.println("End of config loading");
    }





    public static GameMultiConfig get(String config_name){
        return get(config_name, GameMultiConfig.class);
    }
}
