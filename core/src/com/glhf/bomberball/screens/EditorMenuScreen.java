package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.ui.EditorMenuUI;
import com.glhf.bomberball.ui.MultiMenuUI;

import java.io.File;

import static com.glhf.bomberball.utils.Constants.PATH_MAZE;

/**
 * Extends MenuScreen
 *
 * @author nayala
 */
public class EditorMenuScreen extends MenuScreen {

    public Maze maze;
    private int maze_id = 0;
    private int maze_count;

    public EditorMenuScreen() {
        maze_count = Gdx.files.internal(PATH_MAZE+"/multi/").file().listFiles().length;
        maze = Maze.importMazeMulti("maze_" + maze_id);
        this.addUI(new EditorMenuUI(this));
    }

    public void nextMaze() {
        maze_id = (maze_id + 1) % maze_count;
        maze = Maze.importMazeMulti("maze_" + maze_id);
    }

    public void previousMaze() {
        maze_id = (maze_id + maze_count - 1) % maze_count;
        maze = Maze.importMazeMulti("maze_" + maze_id);
    }

    public int getMazeId() {
        return maze_id;
    }
}
