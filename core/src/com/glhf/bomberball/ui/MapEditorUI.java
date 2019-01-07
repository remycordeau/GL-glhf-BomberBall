package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.DestructibleWall;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.gameobject.IndestructibleWall;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MapEditorScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;

import javax.swing.*;

public class MapEditorUI extends Table {
    private Screen screen;
    private Maze maze;
    private MazeDrawer maze_preview;
    // selected cell to put an object on it
    private Cell selected_cell;


    public MapEditorUI(MapEditorScreen screen)
    {
        this.screen = screen;

        this.maze = new Maze(11, 13);
        maze_preview = new MazeDrawer(maze, 0.0f, 0.9f, 0.0f, 1.0f, MazeDrawer.Fit.BEST);

        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.85f));

        initializeButtons();
        this.addActor(maze_preview);
    }

    public void initializeButtons() {
        TextButton bouton_retour = new TextButton("Retour", Graphics.GUI.getSkin());
        bouton_retour.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(new ObjectsWidget()).grow();
        this.row();
        this.add(bouton_retour).grow();
    }


    class ObjectsWidget extends ScrollPane {

        private Table content;

        public ObjectsWidget() {
            super(null);
            content = new Table();
            this.setWidget(content);
            ImageButton destructible_wall = new ImageButton(new TextureRegionDrawable(Graphics.Sprites.get("crate")));
            content.add(destructible_wall).height(75).growX();
            content.row();
            ImageButton indestructible_wall = new ImageButton(new TextureRegionDrawable(Graphics.Sprites.get("wall")));
            content.add(indestructible_wall).height(75).growX();
            content.row();
            //TODO: add listeners on the buttons
            destructible_wall.addListener(new ClickListener(){
                public void clicked(){
                    //maze.getCellAt(x,y).addGameObject(new DestructibleWall());
                }
            });
            indestructible_wall.addListener(new ClickListener(){
                public void clicked(){
                    //maze.getCellAt(x,y).addGameObject(new IndestructibleWall());
                }
            });
            /*for (int i = 0; i < 30; i++) {
                Image image = new Image(Graphics.Sprites.get("crate"));
                image.setScaling(Scaling.fit);
                content.add(image).height(75).growX();
                content.row();
            }*/
        }
    }
}
