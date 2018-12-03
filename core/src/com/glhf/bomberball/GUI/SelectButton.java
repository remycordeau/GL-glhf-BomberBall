package com.glhf.bomberball.GUI;

import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;

public class SelectButton extends Button{
    //Attributes
    private int nbPlayers;

    public SelectButton(int x, int y, int width, int height, String s){
        super(x, y, width, height, s);
        GameConfig config = Config.importConfig("config_multi", GameConfig.class);
        nbPlayers = config.player_count;
        setSprite(Graphics.GUI.get(nbPlayers + ""));

    }

    public void setNbPlayers(int newNumberOfPlayers){
        nbPlayers = newNumberOfPlayers;
        setSprite(Graphics.GUI.get(nbPlayers + ""));
    }


}
