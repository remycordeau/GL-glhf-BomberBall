package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.interfaceMulti.PlayerInfo;
import com.glhf.bomberball.maze.MazeDrawer;

public class StateGameMulti extends StateGame {

    private Player[] players;
    private int current_player_index;
    private int turn_number;
    private VerticalGroup info_player;
    private HorizontalGroup action_player;

    public StateGameMulti(String maze_filename) {
        super("GameMulti", maze_filename);
        current_player_index = 0;
        turn_number = 1;
        loadMaze(maze_filename,1/3f, 1,1/8f,1);
        players = maze.spawnPlayers();
        players[0].initiateTurn();
        // initiate info_player group
        info_player = new VerticalGroup();
        this.stage.addActor(info_player);
        info_player.setSize(Constants.APP_WIDTH/3, Constants.APP_HEIGHT); // à ajuster
        for (Player p : this.players) {
            info_player.addActor(new PlayerInfo(p));
        }
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
        Vector2 cell_pos = this.maze_drawer.screenPosToCell(screenX, screenY);
        int cell_x = (int)cell_pos.x;
        int cell_y = (int)cell_pos.y;
        Player player = players[current_player_index];
        Directions dir = player.getCell().getCellDir(this.maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            players[current_player_index].dropBomb(dir);
        }
        return false;
    }
}
