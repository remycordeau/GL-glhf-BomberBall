package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.glhf.bomberball.gameobject.Player;

public class StateGameMulti extends StateGame{

    private Player[] players;
    private int current_player_index;
    private int turn_number;
    private Group info_player;
    private Group action_player;

    public StateGameMulti(String maze_filename) {
        super("GameMulti", maze_filename);
        current_player_index = 0;
        turn_number = 1;
        loadMaze(maze_filename);
        players = maze.spawnPlayers();
        players[0].initiateTurn();
        for (Player p : this.players) {
            info_player.addActor(new PlayerInfo(p));
        }
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
