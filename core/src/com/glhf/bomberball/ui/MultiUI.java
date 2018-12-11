package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

import java.util.ArrayList;

public class MultiUI extends Table {

    public MultiUI(ArrayList<Player> players) {
        this.setFillParent(true);

        PlayersInfoUI left_ui = new PlayersInfoUI(players);
        left_ui.padRight(Value.percentWidth(2/3f));
        left_ui.setFillParent(true);

        this.addActor(left_ui);
    }
}
