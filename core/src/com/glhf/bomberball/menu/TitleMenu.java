package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Game;

public class TitleMenu extends StateMenu{

    //general attributes
    private Stage stage;
    private VerticalGroup verticalGroup;
    private  TextureAtlas button_atlas;
    private Skin skin;


    public TitleMenu()
    {
        super();
        initializeButtons();
    }

    public void initializeButtons(){
        TextButton textButton = new TextButton("Solo", textButton_style);
        textButton.addListener(new SetStateListener(new StateSoloMenu()));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Multiplayer", textButton_style);
        textButton.addListener(new SetStateListener(new StateGameMulti("maze_0.json")));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Map Editor", textButton_style);
        //textButton.addListener(new SetStateListener(new StateEditorMenu()));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Settings", textButton_style);
        textButton.addListener(new SetStateListener(new StateSettingsMenu()));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Quit", textButton_style);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        centerButtons.addActor(textButton);

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
