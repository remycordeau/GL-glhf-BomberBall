package com.glhf.bomberball.config;

import com.glhf.bomberball.screens.MultiMenuScreen;
public class GameMultiConfig extends GameConfig {
    public int player_count;
    public String[] player_skins;
    public GameMultiConfig() {
        System.out.println("Loading config"+"" +
                "\nPlayer 1 is: " + MultiMenuScreen.playable[MultiMenuScreen.p1_id]
                +"\nPlayer 2 is:" + MultiMenuScreen.playable[MultiMenuScreen.p2_id]
                +"\nPlayer 3 is:" + MultiMenuScreen.playable[MultiMenuScreen.p3_id]
                +"\nPlayer 4 is:" + MultiMenuScreen.playable[MultiMenuScreen.p4_id]);
        // Initialization of the number of players
        player_count= 4;
        if (MultiMenuScreen.p3_id==MultiMenuScreen.nb_Playable-1){player_count--;}
        if (MultiMenuScreen.p4_id==MultiMenuScreen.nb_Playable-1){player_count--;}
        System.out.println("Number of players: "+ player_count);

        // Loading the players skins
        player_skins = new  String[player_count];
        int i=0;    //using a counter because the player 4 might be in position 3 for example if the player 3 isn't playing, can't make a loop because p1_id!=pi_id weird code but working
        player_skins[i]= MultiMenuScreen.playable[MultiMenuScreen.p1_id]; i++;
        player_skins[i]= MultiMenuScreen.playable[MultiMenuScreen.p2_id]; i++;
        if (MultiMenuScreen.p3_id!=MultiMenuScreen.nb_Playable-1)
        {
            player_skins[i]= MultiMenuScreen.playable[MultiMenuScreen.p3_id]; i++;
        }
        if (MultiMenuScreen.p4_id!=MultiMenuScreen.nb_Playable-1)
        {
            player_skins[i]= MultiMenuScreen.playable[MultiMenuScreen.p4_id]; i++;
        }

        System.out.println("End of config loading");
    }





    public static GameMultiConfig get(String config_name){
        return get(config_name, GameMultiConfig.class);
    }
}
