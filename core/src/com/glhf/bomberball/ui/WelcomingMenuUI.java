package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.utils.Constants;

public class WelcomingMenuUI extends Table {

    public WelcomingMenuUI(){
        super();
        this.setFillParent(true);
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture(Constants.PATH_GRAPHICS+"/gui/glhf.png")));
        this.setBackground(texture);
        addButtons();
    }

    private void addButtons() {
        TextButton play = new TextButton("Play", Graphics.GUI.getSkin());
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new MainMenuScreen());
            }
        });
        this.add(play).spaceTop(Value.percentHeight(2f)).row();
    }


}
