/**
 * @author : Rémy
 * creates the solo interface. Displays the choice between solo and infnite modes.
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.config.AppConfig;
import com.glhf.bomberball.screens.*;
import com.glhf.bomberball.utils.ScreenChangeListener;

import static com.glhf.bomberball.utils.Constants.PATH_GRAPHICS;


public class SoloMenuUI extends MenuUI {

    public SoloMenuUI(){
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.2f));
        this.padBottom(Value.percentHeight(0.2f));
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture(PATH_GRAPHICS+"background/MainMenu01.png")));
        this.setBackground(texture);
        addButtons();
    }

    /**
     * initializes and adds the buttons to the ui. Also adds listeners to these buttons if necessary.
     */
    private void addButtons() {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.1f);

        Table buttons = new Table();
        buttons.pad(Value.percentWidth(0.1f));
        TextureRegionDrawable background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(PATH_GRAPHICS+"background/scroll.png"))));
        buttons.setBackground(background);

        b = new AudioButton(Translator.translate("Story Mode"), skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(AppConfig.get().story_displayed){
                    Bomberball.changeScreen(new StoryMenuScreen());
                }else{
                    Bomberball.changeScreen(new StoryTellingScreen());
                }
            }
        });
        buttons.add(b).growX().space(spacing).row();

        b = new AudioButton(Translator.translate("Infinite Mode"), skin);
        b.addListener(new ScreenChangeListener(InfiniteModeScreen.class));
        buttons.add(b).growX().space(spacing).row();

        b = new AudioButton(Translator.translate("Back to main menu"), skin);
        b.addListener(new ScreenChangeListener(MainMenuScreen.class));
        buttons.add(b).growX().space(spacing).row();

        this.add(buttons).grow();
    }

}
