package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
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

        config = GameMultiConfig.get("config_game_multi");
        //maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        //setSelectEffect();

        registerActionsHandlers();

        addUI(new MultiUI(players, this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();
    }

    @Override
    public void registerActionsHandlers() {
        input_handler.registerKeyAction(KeyAction.KEY_BOMB, () -> this.setBombMode());
        input_handler.registerKeyAction(KeyAction.KEY_MOVE, () -> this.setMoveMode());
        input_handler.registerKeyAction(KeyAction.KEY_ENDTURN, () -> endTurn());
        input_handler.registerKeyAction(KeyAction.KEY_SPACE, () -> endTurn());
        input_handler.registerButtonAction(ButtonAction.BUTTON_LEFT, (x, y) -> dropBombAt(x, y));
    }

    public void dropBombAt(float x, float y) {
        y = Gdx.graphics.getHeight() - y;
        Vector2 cell_pos = maze_drawer.screenPosToCell((int)x, (int)y);
        int cell_x = (int)cell_pos.x;
        int cell_y = (int)cell_pos.y;
        Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            dropBomb(dir);
            clearCellsEffect();
            setMoveEffect();
        }
    }

    private void dropBomb(Directions dir) {
        current_player.dropBomb(dir);
        setBombEffect();
    }

    private void clearCellsEffect() {
        for (Cell c : selected_cells) {
            c.removeEffect();
        }
        selected_cells.clear();
    }

    private void setBombEffect() {
        clearCellsEffect();
        ArrayList<Cell> cells_in_range = MazeTransversal.getReacheableCellsInRange(current_player.getCell(), 1);
        cells_in_range.remove(current_player.getCell());
        for (Cell c : cells_in_range) {
            c.setSelectEffect(Color.RED);
            selected_cells.add(c);
        }
    }

    private void setMoveEffect() {
        clearCellsEffect();
        ArrayList<Cell> cells_in_range = MazeTransversal.getReacheableCellsInRange(current_player.getCell(), current_player.getNumberMoveRemaining());
        for (Cell c : cells_in_range) {
            c.setSelectEffect(Color.WHITE);
            selected_cells.add(c);
        }
    }

    // Methods to change the mod when click on a button in ActionPlayer bar
    public void setBombMode(){
        setBombEffect();
        input_handler.registerKeyAction(KeyAction.KEY_DOWN, () -> dropBomb(Directions.DOWN));
        input_handler.registerKeyAction(KeyAction.KEY_UP, () -> dropBomb(Directions.UP));
        input_handler.registerKeyAction(KeyAction.KEY_LEFT, () -> dropBomb(Directions.LEFT));
        input_handler.registerKeyAction(KeyAction.KEY_RIGHT, () -> dropBomb(Directions.RIGHT));
    }

    public void setMoveMode(){
        setMoveEffect();
        input_handler.registerKeyAction(KeyAction.KEY_DOWN, () -> moveCurrentPlayer(Directions.DOWN));
        input_handler.registerKeyAction(KeyAction.KEY_UP, () -> moveCurrentPlayer(Directions.UP));
        input_handler.registerKeyAction(KeyAction.KEY_LEFT, () -> moveCurrentPlayer(Directions.LEFT));
        input_handler.registerKeyAction(KeyAction.KEY_RIGHT, () -> moveCurrentPlayer(Directions.RIGHT));
    }


    ////////////////////////////////////////////////////////////////////////////

    private void moveCurrentPlayer(Directions dir) {
        current_player.move(dir);
        clearCellsEffect();
        setMoveEffect();
    }

    public void endTurn()
    {
        input_handler.lock(true);
        clearCellsEffect();
        maze.processEndTurn();
        current_player.endTurn();
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
        setMoveEffect();
        setMoveMode();
        input_handler.lock(false);
    }
}
