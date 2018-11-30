package com.glhf.bomberball.GUI;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;

public class SelectButton extends Button{
    //Attributes
    private int nbPlayers;

    public SelectButton(int x, int y, int width, int height){
        super(x, y, width, height, "1");
        nbPlayers = Constants.config_file.getIntAttribute("nb_player_max");
        setSprite(Graphics.GUI.get(nbPlayers + ""));

    }

    public void setNbPlayers(int newNumberOfPlayers){
        nbPlayers = newNumberOfPlayers;
    }


}
