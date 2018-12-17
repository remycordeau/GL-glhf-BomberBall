package com.glhf.bomberball.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.ui.AnimationActor;
import com.glhf.bomberball.ui.MultiMenuUI;

public class MultiMenuScreen extends AbstractScreen {

    public Maze maze;
    public AnimationActor[] selectPlayer=
            {
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("knight_m/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("knight_f/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("elf_f/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("elf_m/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("wizzard_m/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("wizzard_f/idle"), Animation.PlayMode.LOOP))
    };

    private int maze_id = 0;
    private final int maze_count = 7;

    public MultiMenuScreen() {
        maze = Maze.importMaze("maze_" + maze_id);
        this.addUI(new MultiMenuUI(this));
    }

    public void nextMaze() {
        maze_id = (maze_id + 1) % maze_count;
        maze = Maze.importMaze("maze_" + maze_id);
    }

    public void previousMaze() {
        maze_id = (maze_id + maze_count - 1) % maze_count;
        maze = Maze.importMaze("maze_" + maze_id);
    }
    public void nextP1(){

    }
    public void nextP2(){

    }
    public void nextP3(){

    }
    public void nextP4(){

    }


    public int getMazeId() {
        return maze_id;
    }
}
