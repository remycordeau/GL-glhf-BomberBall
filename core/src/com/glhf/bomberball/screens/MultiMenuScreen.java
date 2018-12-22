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
    public static String[] selectPlayer= {"knight_m", "knight_f","elf_f", "elf_m","wizzard_m", "wizzard_f", "no_player"};

    private int maze_id = 0;
    private final int maze_count = 7;

    public static int p1_id=0;
    public static int p2_id=1;
    public static int p3_id=2;
    public static int p4_id=4;

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
        do {
            p1_id++;
            if (p1_id==6) { p1_id=0;}
        } while(p1_id==p2_id || p1_id==p3_id || p1_id==p4_id);
        System.out.println("Nouveau skin : "+ selectPlayer[p1_id]);
    }
    public void nextP2(){
        do {
            p2_id++;
            if (p2_id==6) { p2_id=0;}
        } while (p2_id==p1_id || p2_id==p3_id || p2_id==p4_id);
    }
    public void nextP3(){
        do {
            p3_id++;
            if (p3_id==7) { p3_id=0;}
        } while (p3_id==p2_id || p1_id==p3_id || p3_id==p4_id || p3_id!=selectPlayer.length);
    }
    public void nextP4(){
        do{
            p4_id++;
            if (p4_id==7) { p4_id=0;}
        } while (p4_id==p2_id || p4_id==p3_id || p3_id==p4_id || p4_id!=selectPlayer.length);
    }

    public int getMazeId() {
        return maze_id;
    }
}
