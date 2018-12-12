package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.GameMultiScreen;

import java.util.ArrayList;

public class MultiUI extends Table {

    public MultiUI(ArrayList<Player> players, GameMultiScreen screen) {
        this.setFillParent(true);

        PlayersInfoUI left_ui = new PlayersInfoUI(players);
        left_ui.padRight(Value.percentWidth(2/3f));
        left_ui.setFillParent(true);
        ActionPlayerUI bottom_ui = new ActionPlayerUI(screen);
        bottom_ui.padLeft(Value.percentWidth(1/3f));
        bottom_ui.setFillParent(true);
        bottom_ui.align(Align.bottom);
        this.addActor(left_ui);
        this.addActor(bottom_ui);
    }
}
