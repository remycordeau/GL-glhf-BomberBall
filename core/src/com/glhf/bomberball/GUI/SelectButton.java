package com.glhf.bomberball.GUI;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameMultiConfig;

public class SelectButton extends Button{
    //Attributes
    private int nbPlayers;

    public SelectButton(int x, int y, int width, int height, String s){
        super(x, y, width, height, s);
        GameMultiConfig config = Config.importConfig("config_multi", GameMultiConfig.class);
        nbPlayers = config.player_count;
        setSprite(Graphics.GUI.get(nbPlayers + ""));

    }

    public void setNbPlayers(int newNumberOfPlayers){
        nbPlayers = newNumberOfPlayers;
        setSprite(Graphics.GUI.get(nbPlayers + ""));
    }


}
