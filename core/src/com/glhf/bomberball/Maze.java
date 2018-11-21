package com.glhf.bomberball;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Maze {

    private String title;
    private int height;
    private int width;
    private Vector2[] positionStart;
    private Vector2 positionEnd;
    private GameObject[][] tab;

    public Maze() {
        title = "Classic";
        height = 11;
        width = 13;
        positionStart = new Vector2[4];
        positionStart[0]= new Vector2(1,1);
        positionStart[1]= new Vector2(1,10);
        positionStart[2]= new Vector2(12,1);
        positionStart[3]= new Vector2(12,10);
        positionEnd = new Vector2(6,5);
        tab = new GameObject[width][height];
    }

    public static Maze read(String filename) {
        Maze m = null;
        Gson g = new Gson();
        try {
            m = g.fromJson(new FileReader(new File(filename)), Maze.class);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return m;
    }

    public String toJson(){
        Gson g = new Gson();
        return g.toJson(this);
    }

    public void draw(SpriteBatch batch){

    }
}
