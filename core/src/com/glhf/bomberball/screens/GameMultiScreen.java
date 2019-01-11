package com.glhf.bomberball.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.audio.Audio;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.InputsConfig.KeyPriority;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.MultiUI;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.VectorInt2;

import java.util.ArrayList;

public class GameMultiScreen extends GameScreen {

    private GameMultiConfig config;
    private ArrayList<Player> players;
    private int maze_id;

    public GameMultiScreen(Maze maze, int maze_id) {
        super(maze);
        this.maze_id = maze_id;
        config = new GameMultiConfig(); //Previously : config=GameMultiConfig.get("config game multi");
        //maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        //setSelectEffect();

        addUI(new MultiUI(players, this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();
    }

    /**
     * gives the next player after a turn. If the next player is dead, choose the following player.
     */
    protected void nextPlayer() {
        Player winner = null;
        boolean is_last = true;
        for (Player p : players) {
            if (winner == null && p.isAlive()) {
                winner = p;
            } else if (p.isAlive()) {
                is_last = false;
            }
        }
        if (is_last) {
            Bomberball.changeScreen(new VictoryMenuScreen(winner, this.maze_id));
            return;
        }

        int i = players.indexOf(current_player);
        do {
            i = (i + 1) % players.size();
        } while (!players.get(i).isAlive());
        current_player = players.get(i);
        current_player.initiateTurn();
        setMoveEffect();
        setMoveMode();
        input_handler.lock(false);
    }
}
