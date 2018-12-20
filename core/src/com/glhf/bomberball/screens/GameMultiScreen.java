package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.InputsConfig.InputProfile;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.MultiUI;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;

import java.util.ArrayList;

public class GameMultiScreen extends GameScreen {

    private GameMultiConfig config;
    private ArrayList<Player> players;
    private Player current_player;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private int maze_id;

    public GameMultiScreen(Maze maze, int maze_id) {
        super(maze);
        this.maze_id = maze_id;

        config = GameMultiConfig.get("config_game_multi");
        //maze.applyConfig(config);
        players = maze.spawnPlayers(config);
        current_player = players.get(0);
        //setSelectEffect();

        addUI(new MultiUI(players, this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();
    }

    @Override
    public void registerActionsHandlers() {
        super.registerActionsHandlers();
        input_handler.registerActionHandler(Action.MODE_BOMB, this::setBombMode);
        input_handler.registerActionHandler(Action.MODE_MOVE, this::setMoveMode);
        input_handler.registerActionHandler(Action.ENDTURN, this::endTurn);
        input_handler.registerActionHandler(Action.DROP_BOMB, (x, y) -> dropBombAt(x, y));
        input_handler.registerActionHandler(Action.MOVE_DOWN, () -> moveCurrentPlayer(Directions.DOWN));
        input_handler.registerActionHandler(Action.MOVE_UP, () -> moveCurrentPlayer(Directions.UP));
        input_handler.registerActionHandler(Action.MOVE_LEFT, () -> moveCurrentPlayer(Directions.LEFT));
        input_handler.registerActionHandler(Action.MOVE_RIGHT, () -> moveCurrentPlayer(Directions.RIGHT));
        input_handler.registerActionHandler(Action.DROP_BOMB_DOWN, () -> dropBomb(Directions.DOWN));
        input_handler.registerActionHandler(Action.DROP_BOMB_UP, () -> dropBomb(Directions.UP));
        input_handler.registerActionHandler(Action.DROP_BOMB_LEFT, () -> dropBomb(Directions.LEFT));
        input_handler.registerActionHandler(Action.DROP_BOMB_RIGHT, () -> dropBomb(Directions.RIGHT));
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
        input_handler.setInputProfile(InputProfile.BOMB);
    }

    public void setMoveMode(){
        setMoveEffect();
        input_handler.setInputProfile(InputProfile.MOVE);
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
        Player winner = null;
        boolean is_last = true;
        for (Player p : players) {
            if (winner == null && p.isAlive()) {
                winner = p;
            } else if (p.isAlive()) {
                is_last = false;
            }
        }
        if (is_last) {
            Bomberball.changeScreen(new VictoryMenuScreen(winner, this.maze_id));
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
