package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.config.InputsConfig.InputProfile;
import com.glhf.bomberball.gameobject.Bomb;
import com.glhf.bomberball.gameobject.Enemy;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.MultiUI;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.SoloUI;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.VectorInt2;

import java.util.ArrayList;

public class GameStoryScreen extends GameScreen {

    private GameSoloConfig config;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private int maze_id;
    private StoryMenuScreen screen;

    public GameStoryScreen(StoryMenuScreen screen, Maze maze, int maze_id) {
        super(maze);
        this.maze_id = maze_id;
        this.screen = screen;

        config = new GameSoloConfig();
        //maze.applyConfig(config);
        player = maze.spawnPlayer(config);
        //setSelectEffect();

        addUI(new SoloUI(player,this));
        addUI(maze_drawer);

        player.initiateTurn();      //after the UI because initiateTurn notify the ui
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
        VectorInt2 cell_pos = maze_drawer.screenPosToCell(x, y);
        Directions dir = player.getCell().getCellDir(maze.getCellAt(cell_pos.x, cell_pos.y));
        if (dir != null) {
            dropBomb(dir);
            clearCellsEffect();
            setMoveEffect();
        }
    }

    private void dropBomb(Directions dir) {
        if (player.dropBomb(dir)) {
            this.setMoveMode();
        }
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
        ArrayList<Cell> cells_in_range = MazeTransversal.getReacheableCellsInRange(player.getCell(), 1);
        cells_in_range.remove(player.getCell());
        for (Cell c : cells_in_range) {
            c.setSelectEffect(Color.RED);
            selected_cells.add(c);
        }
    }

    private void setMoveEffect() {
        clearCellsEffect();
        ArrayList<Cell> cells_in_range = MazeTransversal.getReacheableCellsInRange(player.getCell(), player.getNumberMoveRemaining());
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
        player.move(dir);
        clearCellsEffect();
        setMoveEffect();
    }

    public void endTurn()
    {
        input_handler.lock(true);
        clearCellsEffect();
        maze.processEndTurn();
        player.endTurn();
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
        if(!player.isAlive()){
            Bomberball.changeScreen(new DeadScreen(screen,maze_id));
        }
        else{
            if(maze_id + 1 < screen.getMazeCount()){
                Bomberball.changeScreen(new EndLevelScreen(screen,this.maze_id));
                return;
            }
            if(maze_id + 1 == screen.getMazeCount()){ // if the player has completed the last level
                Bomberball.changeScreen(new EndStoryScreen(screen,this.maze_id));
                return;
            }
        }

        /*current_player.initiateTurn();
        setMoveEffect();
        setMoveMode();
        input_handler.lock(false);*/
    }
}
