package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.MultiUI;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.Directions;
import com.glhf.bomberball.InputHandler.KeyAction;
import com.glhf.bomberball.InputHandler.ButtonAction;

import java.util.ArrayList;

public class GameMultiScreen extends GameScreen {

    private GameMultiConfig config;
    private ArrayList<Player> players;
    private Player current_player;
    private ArrayList<Cell> selected_cells = new ArrayList<>();

    public GameMultiScreen(Maze maze) {
        super(maze);

        config = Config.importConfig("config_game", GameMultiConfig.class);
        maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        setSelectEffect();

        registerActionsHandlers();

        addUI(new MultiUI(players));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
    }

    @Override
    public void registerActionsHandlers() {
        input_handler.registerKeyAction(KeyAction.KEY_SPACE, () -> endTurn());
        input_handler.registerKeyAction(KeyAction.KEY_DOWN, () -> moveCurrentPlayer(Directions.DOWN));
        input_handler.registerKeyAction(KeyAction.KEY_UP, () -> moveCurrentPlayer(Directions.UP));
        input_handler.registerKeyAction(KeyAction.KEY_LEFT, () -> moveCurrentPlayer(Directions.LEFT));
        input_handler.registerKeyAction(KeyAction.KEY_RIGHT, () -> moveCurrentPlayer(Directions.RIGHT));
        input_handler.registerButtonAction(ButtonAction.BUTTON_LEFT, (x, y) -> dropBombAt(x, y));
    }

    public void dropBombAt(float x, float y) {
        y = Gdx.graphics.getHeight() - y;
        Vector2 cell_pos = maze_drawer.screenPosToCell((int)x, (int)y);
        int cell_x = (int)cell_pos.x;
        int cell_y = (int)cell_pos.y;
        Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            current_player.dropBomb(dir);
        }
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
        input_handler.lock(true);
        clearSelectEffect();
        maze.processEndTurn();
        current_player.setActive(false);
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
        int dead_players_count = 0;
        for (Player p : players) {
            if (!p.isAlive()) {
                dead_players_count++;
            }
        }
        System.err.println(dead_players_count);
        if (dead_players_count >= players.size() - 1) {
            Bomberball.changeScreen(new MainMenuScreen());
            return;
        }

        int i = players.indexOf(current_player);
        do {
            i = (i + 1) % players.size();
        } while (!players.get(i).isAlive());
        current_player = players.get(i);
        current_player.initiateTurn();
        setSelectEffect();
        input_handler.lock(false);
    }
}
