package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;

public class TitleMenu extends State{

    //general attributes
    private Stage stage;
    private Table table;
    private  TextureAtlas button_atlas;
    private Skin skin;
    private BitmapFont font;

    //buttons
    private  TextButton solo_button, multi_button, map_editor_button, settings_button, quit_button;
    private TextButton.TextButtonStyle style;


    public TitleMenu(String name)
    {

        super(name);
        this.create();
    }


    public void create() {
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        font = new BitmapFont();
        skin = new Skin();
        button_atlas = new TextureAtlas(Gdx.files.internal(Constants.PATH_ATLAS_GUI));
        skin.addRegions(button_atlas);
        style = new TextButton.TextButtonStyle();
        style.font = font;
        //textButtonStyle.up = skin.getDrawable("up-button");
        //textButtonStyle.down = skin.getDrawable("down-button");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
        solo_button = new TextButton("Solo", style);
        solo_button.addListener(new SetStateListener(new StateSoloMenu("Menu solo")));
        multi_button = new TextButton("Multi", style);
        multi_button.addListener(new SetStateListener(new StateGameMulti("maze_0.json")));
        map_editor_button = new TextButton("Map Editor", style);
        //map_editor_button.addListener(new SetStateListener(new StateEditor())));
        settings_button = new TextButton("Settings", style);
        settings_button.addListener(new SetStateListener(new StateSettingsMenu("Menu paramètres")));
        quit_button = new TextButton("Quit", style);
        quit_button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) { Gdx.app.exit(); }
        });
        table.add(solo_button);
        table.row();
        table.add(multi_button);
        table.row();
        table.add(map_editor_button);
        table.row();
        table.add(settings_button);
        table.row();
        table.add(quit_button);
        stage.addActor(table);
    }

    @Override
    public void setInputProcessor() {
        Gdx.input.setInputProcessor(stage); //ne pas enlevé
    }

    public void draw()
    {
        stage.draw();

    }

    public void initialize(){


    }

    public void resize(int width, int height){
        stage.getViewport().update(width,height,true);
    }

    public class SetStateListener extends ChangeListener {
        private State state;
        public SetStateListener(State state){
            this.state = state;
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            Game.setState(state);
        }
    }
}
