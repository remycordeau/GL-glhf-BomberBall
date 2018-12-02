package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.gameobject.Player;

public class StateGameMulti extends StateGame{

    private Player[] players;
    private int current_player_index;
    private int turn_number;
    private GameMultiConfig config;

    public StateGameMulti(String maze_filename) {
        super(maze_filename);
        config = Config.importConfig("config_multi", GameMultiConfig.class);
        current_player_index = 0;
        turn_number = 1;
        players = maze.spawnPlayers(config);
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
            current_player_index = (current_player_index + 1) % config.player_count;
        } while (!players[current_player_index].isAlive());
        players[current_player_index].initiateTurn();
    }

    @Override
    public boolean keyDown(int keycode) {
        //HashMap<Integer, String> inputs = Config.getInputs();
        //System.out.println("keyDown"+keycode);
        switch (keycode){
            case Input.Keys.UP:
                moveCurrentPlayer(Directions.UP);
                break;
            case Input.Keys.RIGHT:
                moveCurrentPlayer(Directions.RIGHT);
                break;
            case Input.Keys.DOWN:
                moveCurrentPlayer(Directions.DOWN);
                break;
            case Input.Keys.LEFT:
                moveCurrentPlayer(Directions.LEFT);
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
        Vector2 cell_pos = mazeDrawer.screenPosToCell(screenX, screenY);
        int cell_x = (int)cell_pos.x;
        int cell_y = (int)cell_pos.y;
        Player player = players[current_player_index];
        Directions dir = player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            players[current_player_index].dropBomb(dir);
        }
        return false;
    }
}
