package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
import com.glhf.bomberball.utils.VectorInt2;

import javax.swing.*;
import java.util.ArrayList;

public class MapEditorUI extends Table {
    private MapEditorScreen screen;
    private Maze maze;
    private MazeDrawer maze_preview;
    // selected cell to put an object on it
    private Cell selected_cell;


    public MapEditorUI(MapEditorScreen screen, Maze maze)
    {
        this.screen = screen;

        this.maze=maze;
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

    public VectorInt2 screenPosToCell(float x, float y) {
        return maze_preview.screenPosToCell(x ,y);
    }


    class ObjectsWidget extends ScrollPane {

        private ArrayList<GameObject> selectableObjects = new ArrayList<>();
        private Table content;

        public ObjectsWidget() {
            super(null);
            content = new Table();
            selectableObjects.add(new DestructibleWall());
            selectableObjects.add(new IndestructibleWall());
            this.setActor(content);
            for (GameObject o : selectableObjects) {
                ImageButton button = new ImageButton(new TextureRegionDrawable(o.getSprite()));
                button.getImageCell().expand().fill();
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screen.select(o.getClass());
                    }
                });
                content.add(button).height(75).growX().row();
            }
        }
    }
}
