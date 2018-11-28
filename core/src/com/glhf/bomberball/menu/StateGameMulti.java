package com.glhf.bomberball.menu;

import com.glhf.bomberball.Config;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class StateGameMulti extends StateGame{

    private Player[] players = new Player[4];
    private int current_player;
    private int turn_number;

    public StateGameMulti(String maze_filename) {
        super("GameMulti");
        current_player = 0;
        turn_number = 0;
        loadMaze(maze_filename);
    }

    private void start() {
        players = maze.spawnPlayers(1);
    }

    private void moveCurrentPlayer(int dx, int dy)
    {
        Player p = players[current_player];
        if (maze.isWalkable(p.getPositionX() + dx, p.getPositionY() + dy))
        {
            maze.setGameObjectAt(p, p.getPositionX() + dx, p.getPositionY() + dy);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        HashMap<Integer, String> inputs = Config.getInputs();
        for(int key : inputs.keySet()){
            if(key==keycode){
                try {
                    Player.class.getMethod(inputs.get(key)).invoke(players[turn_number % Constants.NB_PLAYER_MAX]);
                } catch (IllegalAccessException e) { e.printStackTrace(); }
                  catch (InvocationTargetException e) { e.printStackTrace(); }
                  catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return false;
    }
}
