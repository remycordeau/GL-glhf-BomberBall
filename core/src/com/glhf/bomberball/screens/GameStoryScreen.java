package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.Character;
import com.glhf.bomberball.gameobject.Enemy;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.SoloUI;

import java.util.ArrayList;

public class GameStoryScreen extends GameScreen {

    private GameSoloConfig config;
    private ArrayList<Character> characters;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private int maze_id;
    private StoryMenuScreen screen;

    public GameStoryScreen(StoryMenuScreen screen, Maze maze, int maze_id) {
        //super(maze);
        super(new Maze(11,13));
        this.maze_id = maze_id;
        this.screen = screen;

        characters = new ArrayList<Character>();
        characters.add(current_player);
        ArrayList<Enemy> enemies = maze.getEnemies();
        enemies.forEach(Enemy::createAI);
        characters.addAll(enemies);
        this.maze.export("testWithEnemies");

        config = new GameSoloConfig();
        current_player = maze.spawnPlayer(config);


        addUI(new SoloUI(current_player,this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();
    }

    @Override
    public void registerActionsHandlers() {
        super.registerActionsHandlers();
    }

    /**
     * gives the next current_player after a turn. If the next current_player is dead, choose the following current_player.
     */
    protected void nextPlayer() {
        if(!current_player.isAlive()) {
            Bomberball.changeScreen(new DeadScreen(screen,maze_id));
        } else if(maze_id + 1 < screen.getMazeCount()) { //TODO: change condition to character.size()==1 and character.get(0) instanceof Player
            Bomberball.changeScreen(new EndLevelScreen(screen,this.maze_id));
            return;
        } else if(maze_id + 1 == screen.getMazeCount()) { // if the current_player has completed the last level
            Bomberball.changeScreen(new EndStoryScreen(screen,this.maze_id));
            return;
        }

        int i = characters.indexOf(current_character);
        do {
            i = (i + 1) % characters.size();
        } while (!characters.get(i).isAlive());
        current_character = characters.get(i);
        current_character.initiateTurn();
        setMoveEffect();
        setMoveMode();
        input_handler.lock(false);  //TODO: voir ce que font les inputs lors du tour des ennemis
    }
}
