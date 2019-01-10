/**
 * @author : Rémy
 * creates the solo interface. Displays the choice between solo and infnite modes.
 */
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
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.screens.InfiniteModeScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.StoryMenuScreen;


public class SoloMenuUI extends Table {

    private TextButton infinite_button, story_button, back_button;

    public SoloMenuUI(){
        super();
        // GameSoloConfig.get("config_game_solo").resetLevels();
        this.setFillParent(true);
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/graphics/background/StoryMenu.png")));
        this.setBackground(texture);
        initializeButtons();
    }

    /**
     * initializes and adds the buttons to the ui. Also adds listeners to these buttons if necessary.
     */
    private void initializeButtons() {
        Table buttons = new Table();
        story_button = new TextButton(Translator.translate("Story Mode"), Graphics.GUI.getSkin());
        story_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Bomberball.changeScreen(new StoryMenuScreen());
                }
            });
        Value spacing = Value.percentHeight(1.2f);

        buttons.add(story_button).space(spacing).growX().row();

        infinite_button = new TextButton(Translator.translate("Infinite Mode"), Graphics.GUI.getSkin());
        infinite_button.addListener(new ScreenChangeListener(InfiniteModeScreen.class));
        buttons.add(infinite_button).space(spacing).growX().row();

        back_button = new TextButton(Translator.translate("Back to main menu"),Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        buttons.add(back_button).space(spacing).growX();
        this.add(buttons);
    }

}
