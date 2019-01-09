/**
 * @author : Rémy
 * creates the solo interface. Displays the choice between solo and infnite modes.
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
        initializeButtons();
    }

    /**
     * initializes and adds the buttons to the ui. Also adds listeners to these buttons if necessary.
     */
    private void initializeButtons() {

        story_button = new TextButton(Translator.translate("Story Mode"), Graphics.GUI.getSkin());
        story_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Bomberball.changeScreen(new StoryMenuScreen());
                }
            });

        this.add(story_button).expandX().row();

        infinite_button = new TextButton(Translator.translate("Infinite Mode"), Graphics.GUI.getSkin());
        infinite_button.addListener(new ScreenChangeListener(InfiniteModeScreen.class));
        this.add(infinite_button).spaceTop(Value.percentHeight(1f)).row();

        back_button = new TextButton(Translator.translate("Back to main menu"),Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(back_button).spaceTop(Value.percentHeight(3f)).row();
    }

}
