package com.glhf.bomberball.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.config.InputsConfig;
import com.glhf.bomberball.screens.GameScreen;

import static com.glhf.bomberball.utils.Constants.PATH_GRAPHICS;

public class ActionPlayerUI extends Table {
    //attributes
    TextButton reachable_squares_move;
    TextButton reachable_squares_bomb;
    TextButton endTurn;
    GameScreen screen;
    /**
     * constructor
     */
    public ActionPlayerUI(GameScreen screen){
        this.screen=screen;
        InputsConfig config = InputsConfig.get();
        this.reachable_squares_move = new AudioButton(Translator.translate("mode movement button", config.getInputName(Action.MODE_BOMB)), Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new AudioButton(Translator.translate("mode bombe button", config.getInputName(Action.MODE_MOVE)), Graphics.GUI.getSkin(),"small");
        this.endTurn= new AudioButton(Translator.translate("endTurn button", config.getInputName(Action.ENDTURN)), Graphics.GUI.getSkin(),"small");
        // TODO : ajout des listeners sur les différents boutons
        reachable_squares_move.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setMoveMode();
            }
        });
        reachable_squares_bomb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screen.setBombMode();
            }
        });
        endTurn.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event, float x, float y){
               screen.endTurn();
           }
        });
        this.add(reachable_squares_bomb).growX();
        this.add(reachable_squares_move).growX();
        this.add(endTurn).growX();
        this.pad(5);
    }
}
