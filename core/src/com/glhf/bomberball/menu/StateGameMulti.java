package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.gameobject.Player;

public class StateGameMulti extends StateGame{

    private Player[] players;
    private int current_player_index;
    private int turn_number;

    public StateGameMulti(String maze_filename) {
        super(maze_filename);
        current_player_index = 0;
        turn_number = 1;
        loadMaze(maze_filename);
        players = maze.spawnPlayers();
        players[0].initiateTurn();
    }

    private void moveCurrentPlayer(Directions dir)
    {
        players[current_player_index].move(dir);
    }

    /**
     * gives the next player after a turn. If the next player is dead, choose the following player.
     */
    private void nextPlayer()
    {
        maze.processEndTurn();
        do {
            current_player_index = (current_player_index + 1) % maze.getNb_player_max();
        } while (!players[current_player_index].isAlive());
        players[current_player_index].initiateTurn();
    }
}


