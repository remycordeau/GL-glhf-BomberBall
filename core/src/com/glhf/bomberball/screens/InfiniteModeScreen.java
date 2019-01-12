package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.Enemy;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeBuilder;
import com.glhf.bomberball.ui.InfiniteModeUI;
import com.glhf.bomberball.ui.SoloUI;
import com.glhf.bomberball.ui.StoryMenuUI;

import java.util.ArrayList;

public class InfiniteModeScreen extends MenuScreen {

    //attributes
    public Maze maze;
    private GameSoloConfig config;
    private ArrayList<Character> characters; // one player and some enemies

    public InfiniteModeScreen() {
        super();
        config = GameSoloConfig.get();
        maze = MazeBuilder.createInfinityMaze();
        maze.initialize();
        addUI(new InfiniteModeUI(this));
    }


}
