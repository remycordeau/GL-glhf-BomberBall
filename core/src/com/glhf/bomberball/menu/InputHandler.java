package com.glhf.bomberball.menu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.config.InputsConfig;

import javax.swing.*;


public class InputHandler extends InputListener {

    private InputsConfig inputs_config;

    public enum KeyAction {
        KEY_UP,
        KEY_DOWN,
        KEY_LEFT,
        KEY_RIGHT,
        KEY_SPACE
    }

    public enum ButtonAction {
        BUTTON_LEFT,
        BUTTON_RIGHT
    }

    private Runnable[] key_actions = new Runnable[KeyAction.values().length];

    public InputHandler() {
        // TODO : importer la bonne config d'inputs
        inputs_config = InputsConfig.defaultConfig();
    }

//    @Override
//    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//        System.err.println("Click ! --> Gestion des inputs à faire"); //TODO Gestion des inputs à faire
//        setScreenX((int) x);
//        setScreenY((int) y);
//        runnables[Action.BUTTON_LEFT.ordinal()].run();
//        runnables[Action.BUTTON_RIGHT.ordinal()].run();
//
//        return false; //super.touchDown(event, x, y, pointer, button);
//    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        key_actions[inputs_config.getKeyActionCode(keycode).ordinal()].run();
        return false;
    }

    public void registerKeyAction(KeyAction key_action, Runnable runnable) {
        key_actions[key_action.ordinal()] = runnable;
    }
}
