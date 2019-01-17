package com.glhf.bomberball.screens;

import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameInfiniteConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.maze.MazeBuilder;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.InfiniteUI;
import com.glhf.bomberball.utils.Directions;

import java.util.ArrayList;

public class GameInfiniteScreen extends GameScreen {

    private ArrayList<Enemy> enemies;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private InfiniteModeScreen screen;

    public GameInfiniteScreen(InfiniteModeScreen screen) {
        //super(maze);
        super(MazeBuilder.createInfinityMaze());
        GameInfiniteConfig config = GameInfiniteConfig.get();
        this.screen = screen;
        //TODO : factoriser le code avec GameStoryScreen
        current_player = this.maze.spawnPlayer(config);

        //enemies = this.maze.getEnemies();
        enemies = this.maze.getEnemies();
        enemies.forEach(Enemy::createAI);
        this.maze.export("testWithEnemies");

        addUI(new InfiniteUI(current_player, this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();

        Timer.schedule(new Timer.Task() {   //Verifying if an ennemy has killed the player
            @Override
            public void run() {
                if (!current_player.isAlive()) {
                    Bomberball.changeScreen(new MainMenuScreen()); //TODO: changer en DeadScreen
                    Timer.instance().clear();
                }
            }
        }, 1f, 1f);
    }

    @Override
    public void registerActionsHandlers() {
        super.registerActionsHandlers();
    }

    /**
     * gives the next current_player after a turn. If the next current_player is dead, choose the following current_player.
     */
    protected void nextPlayer() {
        //TODO: wait the execution of all tasks in the Timer
        if (!current_player.isAlive()) {
            Bomberball.changeScreen(new EndInfiniteScreen());
        } else {

            // test if the current_player reached the door
            boolean isIn = false;
            for (GameObject o : current_player.getCell().getGameObjects()) {
                if (o instanceof Door) {
                    isIn = true;
                }
            }
            if (isIn) {
                Bomberball.changeScreen(new GameInfiniteScreen(screen));
            }

            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.initiateTurn();
                    enemy.followWay();
                }
            }

            try {
                current_player.initiateTurn();
                setMoveEffect();
                setMoveMode();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        input_handler.lock(false);
                    }
                }, 0.1f*enemies.size());

            } catch (RuntimeException e) {
                System.out.println("The player probably died");
            }
        }
        /*if (!current_player.isAlive()) {
            Bomberball.changeScreen(new MainMenuScreen());
        } else {
            current_player.initiateTurn();
            setMoveEffect();
            setMoveMode();
            input_handler.lock(false);
        }*/
    }

    /**
     * enemies path is calculated everytime a bomb has been droped
     * @param dir
     */
    @Override
    protected void dropBomb(Directions dir) {
        super.dropBomb(dir);
        enemies.forEach(Enemy::updateAI);
    }
}