/**
 * @author : Rémy
 * creates and displays the user interface when a player dies
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.audio.Audio;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.GameStoryScreen;
import com.glhf.bomberball.screens.StoryMenuScreen;

public class DeadUI extends Table {

    private Label dead;
    private TextButton replay, back_story_menu,ragequit;
    private StoryMenuScreen screen;
    private int maze_id;

    public DeadUI(StoryMenuScreen screen, int maze_id) {

        this.setFillParent(true);
        this.screen = screen;
        this.maze_id = maze_id;
        addButtons();
    }

    /**
     * initializes and adds all the buttons to the ui. Also adds listeners to these buttons if necessary.
     */
    private void addButtons() {

        AnimationActor player_animation = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("mort/idle"), Animation.PlayMode.LOOP));
        player_animation.mustMove(true);
        this.add(player_animation).grow().row();

        // Buttons
        dead = new Label(Translator.translate("game lost"), Graphics.GUI.getSkin());
        dead.setFontScale(2f,2f);
        dead.setColor(Color.RED);
        this .add(dead).spaceBottom(Value.percentHeight(0.9f)).row();

        replay = new AudioButton(Translator.translate("Relancer"),Graphics.GUI.getSkin());
        replay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameStoryScreen(screen, Maze.importMaze("maze_" + maze_id),screen.getMazeId()));
                Audio.CLICK_BUTTON.play();
            }
        });
        this.add(replay).spaceTop(Value.percentHeight(0.9f)).row();

        back_story_menu = new AudioButton(Translator.translate("Retour à le selection niveau"),Graphics.GUI.getSkin());
        back_story_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(screen);
            }
        });
        this.add(back_story_menu).spaceTop(Value.percentHeight(0.9f)).row();

        ragequit = new AudioButton(Translator.translate("Retour menu"),Graphics.GUI.getSkin());
        ragequit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                Audio.CLICK_BUTTON.play();
            }
        });
        this.add(ragequit).spaceTop(Value.percentHeight(0.9f)).row();

    }
}
