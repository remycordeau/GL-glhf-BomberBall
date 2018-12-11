package com.glhf.bomberball.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.MultiUI;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.menu.Directions;
import com.glhf.bomberball.menu.InputHandler.KeyAction;
import com.glhf.bomberball.menu.InputHandler.ButtonAction;

import java.util.ArrayList;

public class GameMultiScreen extends AbstractScreen {

    private Maze maze;
    private MazeDrawer maze_drawer;
    private GameConfig config;
    private ArrayList<Player> players;
    private Player current_player;
    private ArrayList<Cell> selected_cells = new ArrayList<Cell>();

    public GameMultiScreen(Maze maze) {
        super();
        this.maze = maze;
        maze_drawer = new MazeDrawer(maze, 1/3f, 1f, 0f, 1f, MazeDrawer.Fit.BEST);

        config = Config.importConfig("config_game", GameConfig.class);
        maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        current_player.initiateTurn();
        setSelectEffect();

        registerActionsHandlers();

        addUI(new MultiUI(players));
    }

    @Override
    public void registerActionsHandlers() {
        inputHandler.registerKeyAction(KeyAction.KEY_SPACE, () -> endTurn());
        inputHandler.registerKeyAction(KeyAction.KEY_DOWN, () -> moveCurrentPlayer(Directions.DOWN));
        inputHandler.registerKeyAction(KeyAction.KEY_UP, () -> moveCurrentPlayer(Directions.UP));
        inputHandler.registerKeyAction(KeyAction.KEY_LEFT, () -> moveCurrentPlayer(Directions.LEFT));
        inputHandler.registerKeyAction(KeyAction.KEY_RIGHT, () -> moveCurrentPlayer(Directions.RIGHT));
        inputHandler.registerButtonAction(ButtonAction.BUTTON_LEFT, (x, y) -> { dropBombAt(x, y);});
    }

    public void dropBombAt(float x, float y) {
        y = Constants.APP_HEIGHT - y;
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

    @Override
    public void render(float delta) {
        super.render(delta);
        maze_drawer.drawMaze();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        maze_drawer.updateView(width, height);
    }
}
