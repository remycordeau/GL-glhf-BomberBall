package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MapEditorScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;

public class MapEditorUI extends Table {
    private Screen screen;
    private Maze maze;
    private MazeDrawer maze_preview;

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
            Image destructible_wall = new Image(Graphics.Sprites.get("crate"));
            Image wall = new Image(Graphics.Sprites.get("wall"));
            destructible_wall.setScaling(Scaling.fit);
            content.add(destructible_wall).height(75).growX();
            content.row();
            wall.setScaling(Scaling.fit);
            content.add(wall).height(75).growX();
            content.row();
            /*for (int i = 0; i < 30; i++) {
                Image image = new Image(Graphics.Sprites.get("crate"));
                image.setScaling(Scaling.fit);
                content.add(image).height(75).growX();
                content.row();
            }*/
        }
    }
}
