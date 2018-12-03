package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.maze.MazeTransversal;

import java.util.ArrayList;

public class StateGameMulti extends StateGame{

    private ArrayList<Player> players;
    private Player current_player;
    private GameConfig config;
    private ArrayList<Cell> selected_cells = new ArrayList<Cell>();

    public StateGameMulti(String maze_name) {
        super(maze_name);
        config = Config.importConfig("config_game", GameConfig.class);
        maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        current_player.initiateTurn();
        setRangeEffect();
    }

    private void setRangeEffect() {
        ArrayList<Cell> cells_in_range = MazeTransversal.getCellsInRange(current_player.getCell(), current_player.getMovesRemaining());
        for (Cell c : selected_cells) {
            c.removeEffect();
        }
        selected_cells.clear();
        for (Cell c : cells_in_range) {
            c.setSelectEffect();
            selected_cells.add(c);
        }
    }

    private void moveCurrentPlayer(Directions dir) {
        current_player.move(dir);
        setRangeEffect();
    }

    private void endTurn()
    {
        maze.processEndTurn();
        nextPlayer();
        setRangeEffect();
    }

    /**
     * gives the next player after a turn. If the next player is dead, choose the following player.
     */
    private void nextPlayer() {
        int i = players.indexOf(current_player);
        do {
            i = (i + 1) % players.size();
        } while (!players.get(i).isAlive());
        current_player = players.get(i);
        current_player.initiateTurn();
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
                endTurn();
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
        Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            current_player.dropBomb(dir);
        }
        return false;
    }
}
