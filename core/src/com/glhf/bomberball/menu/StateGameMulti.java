package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Config;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Player;

import java.util.HashMap;

public class StateGameMulti extends StateGame{

    private Player[] players;
    private int current_player_index;
    private int turn_number;

    public StateGameMulti(String maze_filename) {
        super("GameMulti", maze_filename);
        current_player_index = 0;
        turn_number = 1;
        loadMaze(maze_filename);
        players = maze.spawnPlayers();
        players[0].initiateTurn();
    }

    private void moveCurrentPlayer(DIRECTIONS dir)
    {
        players[current_player_index].moveDir(dir);
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

    @Override
    public boolean keyDown(int keycode) {
        //HashMap<Integer, String> inputs = Config.getInputs();
        //System.out.println("keyDown"+keycode);
        switch (keycode){
            case Input.Keys.UP:
                moveCurrentPlayer(DIRECTIONS.UP);
                break;
            case Input.Keys.RIGHT:
                moveCurrentPlayer(DIRECTIONS.RIGHT);
                break;
            case Input.Keys.DOWN:
                moveCurrentPlayer(DIRECTIONS.DOWN);
                break;
            case Input.Keys.LEFT:
                moveCurrentPlayer(DIRECTIONS.LEFT);
                break;
            case Input.Keys.SPACE:
                nextPlayer();
                break;
        }
//        if(inputs.keySet().contains(keycode)) {
//            try {
//                Player.class.getMethod(inputs.get(keycode)).invoke(players[turn_number % Constants.NB_PLAYER_MAX]);
//            } catch (IllegalAccessException e) { e.printStackTrace(); }
//              catch (InvocationTargetException e) { e.printStackTrace(); }
//              catch (NoSuchMethodException e) { e.printStackTrace(); }
//        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 cell = mazeDrawer.screenPosToCell(screenX, screenY);
        int cell_x = (int)cell.x;
        int cell_y = (int)cell.y;
        if (maze.isWalkable(cell_x, cell_y)) {
            maze.addBomb(players[current_player_index].dropBomb(cell_x, cell_y));
        }
        return false;
    }
}
