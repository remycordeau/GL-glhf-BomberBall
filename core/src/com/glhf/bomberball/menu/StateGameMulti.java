package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.glhf.bomberball.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.interfaceMulti.PlayerInfo;

public class StateGameMulti extends StateGame {

    private Player[] players;
    private int current_player_index;
    private int turn_number;
    //private VerticalGroup info_player;
    public VerticalGroup info_player;
    private VerticalGroup intern_info_player;
    private HorizontalGroup player_profil;
    private HorizontalGroup action_player;

    public StateGameMulti(String maze_filename) {
        super(maze_filename);
        current_player_index = 0;
        turn_number = 1;
        loadMaze(maze_filename,1/3f, 1,1/8f,1);
        players = maze.spawnPlayers();
        players[0].initiateTurn();
        // initiate info_player group
        info_player = new VerticalGroup();
        info_player.space(10f); //ptere à retirer avec scaling
        info_player.setSize(Constants.APP_WIDTH/3, Constants.APP_HEIGHT); // à ajuster
        for (Player p : this.players) {
            PlayerInfo pi= new PlayerInfo(p);
            pi.space(10f);
            info_player.addActor(pi);
        }
        this.stage.addActor(info_player);
        //:TODO action player bar
        /*action_player = new HorizontalGroup();
        action_player.addActor(new TextButton("déplacement", new Skin()));
        action_player.addActor(new TextButton("poser une bombe", new Skin()));
        action_player.addActor(new TextButton("fin de tour", new Skin()));*/
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
            current_player_index = (current_player_index + 1) % this.maze.getNb_player_max();
        } while (!players[current_player_index].isAlive());
        players[current_player_index].initiateTurn();
    }
}


