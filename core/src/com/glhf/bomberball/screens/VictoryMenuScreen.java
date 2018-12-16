package com.glhf.bomberball.screens;

import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.ui.VictoryMenuUI;

public class VictoryMenuScreen extends AbstractScreen {

    public VictoryMenuScreen(Player p, int maze_id) {
        super();
        addUI(new VictoryMenuUI(p, maze_id));
    }
}
