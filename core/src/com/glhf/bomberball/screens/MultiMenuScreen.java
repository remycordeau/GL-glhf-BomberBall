package com.glhf.bomberball.screens;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.ui.MultiMenuUI;

public class MultiMenuScreen extends AbstractScreen {

    public Maze maze;

    private int maze_id = 0;
    private final int maze_count = 6;
    private MazeDrawer maze_preview;

    public MultiMenuScreen() {
        setPreviewMaze();
        this.addUI(new MultiMenuUI(this));
        this.addUI(maze_preview);
    }

    public void nextMaze() {
        maze_id = (maze_id + 1) % maze_count;
        setPreviewMaze();
    }

    public void previousMaze() {
        maze_id = (maze_id + maze_count - 1) % maze_count;
        setPreviewMaze();
    }

    private void setPreviewMaze() {
        maze = Maze.importMaze("maze_" + maze_id);
        maze_preview = new MazeDrawer(maze, 0.25f, 0.75f, 1/3f, 1f, MazeDrawer.Fit.BEST);
    }
}
