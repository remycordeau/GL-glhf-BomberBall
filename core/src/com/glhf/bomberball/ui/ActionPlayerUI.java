package com.glhf.bomberball.ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.config.InputsConfig;
import com.glhf.bomberball.screens.GameScreen;

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
        this.reachable_squares_move = new TextButton(Translator.translate("mode movement button", config.getInputName(Action.MODE_BOMB)), Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new TextButton(Translator.translate("mode bombe button", config.getInputName(Action.MODE_MOVE)), Graphics.GUI.getSkin(),"small");
        this.endTurn= new TextButton(Translator.translate("endTurn button", config.getInputName(Action.ENDTURN)), Graphics.GUI.getSkin(),"small");
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
