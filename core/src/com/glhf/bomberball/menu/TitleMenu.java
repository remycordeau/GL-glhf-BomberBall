package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.glhf.bomberball.Constants;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

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

        initialize();
        table.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                System.out.println("Button clicked");
            }
        });



    }


    public void draw()
    {
        stage.draw();

    }

    public void initialize(){

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
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
        multi_button = new TextButton("Multi", style);
        map_editor_button = new TextButton("Map Editor", style);
        settings_button = new TextButton("Settings", style);
        quit_button = new TextButton("Quit", style);
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

    public void resize(int width, int height){
        stage.getViewport().update(width,height,true);
    }


}
