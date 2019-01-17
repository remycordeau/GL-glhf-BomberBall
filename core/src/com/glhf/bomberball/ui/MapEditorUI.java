package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MapEditorScreen;
import com.glhf.bomberball.utils.ScreenChangeListener;
import com.glhf.bomberball.utils.VectorInt2;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static com.glhf.bomberball.utils.Constants.PATH_MAZE;

public class MapEditorUI extends MenuUI {

    private MapEditorScreen screen;
    private Maze maze;
    private MazeDrawer maze_preview;

    public MapEditorUI(MapEditorScreen screen, Maze maze)
    {
        this.screen = screen;

        this.maze = maze;
        maze_preview = new MazeDrawer(maze, 0.0f, 0.9f, 0.0f, 1.0f, MazeDrawer.Fit.BEST);

        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.85f));

        initializeButtons();
        this.addActor(maze_preview);
    }

    public void initializeButtons() {
        TextButton bouton_retour = new AudioButton(Translator.translate("Back"), Graphics.GUI.getSkin());
        bouton_retour.addListener(new ScreenChangeListener(MainMenuScreen.class));
        TextButton button_save = new AudioButton(Translator.translate("Save"), Graphics.GUI.getSkin());
        button_save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String output = JOptionPane.showInputDialog("Choisir le nom du Maze");
                File dir = new File(PATH_MAZE);
                if(!dir.exists()) dir.mkdirs();
                maze.export(output);
            }
        });
        this.add(new ObjectsWidget()).grow();
        this.row();
        this.add(bouton_retour).grow();
        this.row();
        this.add(button_save).grow();
    }

    public VectorInt2 screenPosToCell(float x, float y) {
        return maze_preview.screenPosToCell(x ,y);
    }

    class ObjectsWidget extends ScrollPane {

        private ArrayList<GameObject> presets = new ArrayList<>();
        private Table content;

        public ObjectsWidget() {
            super(null);
            content = new Table();
            presets.add(new DestructibleWall());
            presets.add(new BonusWall(new Bonus(Bonus.Type.SPEED)));
            presets.add(new BonusWall(new Bonus(Bonus.Type.BOMB_NUMBER)));
            presets.add(new BonusWall(new Bonus(Bonus.Type.BOMB_RANGE)));
            presets.add(new IndestructibleWall());
            this.setActor(content);
            for (GameObject o : presets) {
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
