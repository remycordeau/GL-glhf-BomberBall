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
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("wizzard_f/idle"), Animation.PlayMode.LOOP)),
            new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("skelet/idle"), Animation.PlayMode.LOOP)) // Le personnage ayant cette animation ne sera pas inclus dans
    };

    private int maze_id = 0;
    private final int maze_count = 7;

    public int p1_id=0;
    public int p2_id=2;
    public int p3_id=4;
    public int p4_id=6;

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
        System.out.println("Clic sur P1: ancien p1_id ="+ p1_id);
        do {
            p1_id++;
            if (p1_id==6) { p1_id=0;}
        } while(p1_id==p2_id || p1_id==p3_id || p1_id==p4_id);
        System.out.println("Clic sur P1: nouveau p1_id ="+ p1_id);

    }
    public void nextP2(){
        p2_id++;
        while (p2_id==p1_id || p2_id==p3_id || p2_id==p4_id)
        {
            p2_id++;
            if (p2_id==6) { p2_id=0;}
        }
    }
    public void nextP3(){
        p3_id++;
        while (p3_id==p2_id || p1_id==p3_id || p3_id==p4_id)
        {
            p3_id++;
            if (p3_id==7) { p3_id=0;}
        }
    }
    public void nextP4(){
        p4_id++;
        while (p4_id==p2_id || p4_id==p3_id || p3_id==p4_id)
        {
            p4_id++;
            if (p4_id==7) { p4_id=0;}
        }
    }

    public int getMazeId() {
        return maze_id;
    }
}
