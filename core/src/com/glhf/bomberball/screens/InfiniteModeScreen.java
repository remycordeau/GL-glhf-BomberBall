package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.Enemy;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.InfiniteModeUI;

import java.util.ArrayList;

public class InfiniteModeScreen extends MenuScreen {
    //attributes
    private GameMultiConfig config;
    private ArrayList<Character> characters; // one player and some enemies
    private int maze_id;

    public InfiniteModeScreen() {
        super();
        addUI(new InfiniteModeUI());
    }


}
