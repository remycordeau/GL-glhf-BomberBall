package com.glhf.bomberball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Maze {
    private String title;
    private int height;
    private int width;
    private Vector2[] positionStart;
    private Vector2 positionEnd;

    public Maze(String title, int height, int width, Vector2[] positionStart, Vector2 positionEnd) {
        this.title = title;
        this.height = height;
        this.width = width;
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
    }

    public void draw(SpriteBatch batch){

    }
}
