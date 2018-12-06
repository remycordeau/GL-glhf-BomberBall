package com.glhf.bomberball.menu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.interfaceMulti.PlayerInfo;
import com.glhf.bomberball.interfaces.InterfaceMulti;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.menu.InputHandler.Events;

import java.util.ArrayList;

public class StateGameMulti extends StateGame {

    //private final VerticalGroup info_player;
    //private final Group info_player;
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
        setSelectEffect();

        stage.addActor(new InterfaceMulti(stage, players));
//        // initiate info_player group
//        //info_player = new VerticalGroup();
//        info_player = new Group();
//        //info_player.space(10f); //ptere à retirer avec scaling
//        info_player.setSize(Constants.APP_WIDTH/3, Constants.APP_HEIGHT); // à ajuster
//        info_player.setDebug(true); //TODO remove when scaling is ok
//        for (int i=0; i<players.size(); i++) {
//            PlayerInfo pi= new PlayerInfo(players.get(i));
//            //pi.space(10f);
//            info_player.addActor(pi);
//            pi.setSize((Constants.APP_WIDTH/3)-20f, Constants.APP_HEIGHT/4-20f);
//            pi.setPosition(10f, (3-i)*Constants.APP_HEIGHT/4+10f);
//            pi.setDebug(true); //TODO remove when scaling is ok
//        }
//        this.stage.addActor(info_player);
//        //:TODO action player bar
//        /*action_player = new HorizontalGroup();
//        action_player.addActor(new TextButton("déplacement", new Skin()));
//        action_player.addActor(new TextButton("poser une bombe", new Skin()));
//        action_player.addActor(new TextButton("fin de tour", new Skin()));*/

        inputHandler.registerKey(Events.KEY_SPACE, new Runnable() {
            @Override
            public void run() {
                endTurn();
            }
        });
        inputHandler.registerKey(Events.KEY_UP, new Runnable() {
            @Override
            public void run() {
                moveCurrentPlayer(Directions.UP);
            }
        });
        inputHandler.registerKey(Events.KEY_DOWN, new Runnable() {
            @Override
            public void run() {
                moveCurrentPlayer(Directions.DOWN);
            }
        });
        inputHandler.registerKey(Events.KEY_LEFT, new Runnable() {
            @Override
            public void run() {
                moveCurrentPlayer(Directions.LEFT);
            }
        });
        inputHandler.registerKey(Events.KEY_RIGHT, new Runnable() {
            @Override
            public void run() {
                moveCurrentPlayer(Directions.RIGHT);
            }
        });

        inputHandler.registerKey(Events.MOUSE_LEFT, new Runnable() {
            @Override
            public void run() {
                Vector2 cell_pos = maze_drawer.screenPosToCell(inputHandler.getScreenX(), inputHandler.getScreenY());
                int cell_x = (int)cell_pos.x;
                int cell_y = (int)cell_pos.y;
                Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
                if (dir != null) {
                    current_player.dropBomb(dir);
                }
            }
        });

        inputHandler.registerKey(Events.MOUSE_RIGHT, new Runnable() {
            @Override
            public void run() {
                Vector2 cell_pos = maze_drawer.screenPosToCell(inputHandler.getScreenX(), inputHandler.getScreenY());
                int cell_x = (int)cell_pos.x;
                int cell_y = (int)cell_pos.y;
                Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
                if (dir != null) {
                    current_player.dropBomb(dir);
                }
            }
        });

    }

    private void clearSelectEffect() {
        for (Cell c : selected_cells) {
            c.removeEffect();
        }
        selected_cells.clear();
    }

    private void setSelectEffect() {
        ArrayList<Cell> cells_in_range = MazeTransversal.getCellsInRange(current_player.getCell(), current_player.getMovesRemaining());
        for (Cell c : cells_in_range) {
            c.setSelectEffect();
            selected_cells.add(c);
        }
    }


    private void moveCurrentPlayer(Directions dir) {
        current_player.move(dir);
        clearSelectEffect();
        setSelectEffect();
    }

    private void endTurn()
    {
        clearSelectEffect();
        maze.processEndTurn();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                nextPlayer();
            }
        }, 0.5f);
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
        setSelectEffect();
    }
}
