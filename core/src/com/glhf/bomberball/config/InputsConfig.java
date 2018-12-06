package com.glhf.bomberball.config;

import com.badlogic.gdx.Input;
import com.glhf.bomberball.menu.InputHandler;
import com.glhf.bomberball.menu.InputHandler.ButtonAction;
import com.glhf.bomberball.menu.InputHandler.KeyAction;

import java.util.HashMap;

public class InputsConfig extends Config {

    private HashMap<Integer, KeyAction> keycodes_actions;
    private HashMap<Integer, ButtonAction> buttoncodes_actions;

    public InputsConfig() {
        keycodes_actions = new HashMap<Integer, KeyAction>();
        buttoncodes_actions = new HashMap<Integer, ButtonAction>();
    }

    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.addKeyCodeAction(Input.Keys.DOWN, KeyAction.KEY_DOWN);
        c.addKeyCodeAction(Input.Keys.UP, KeyAction.KEY_UP);
        c.addKeyCodeAction(Input.Keys.LEFT, KeyAction.KEY_LEFT);
        c.addKeyCodeAction(Input.Keys.RIGHT, KeyAction.KEY_RIGHT);
        c.addKeyCodeAction(Input.Keys.SPACE, KeyAction.KEY_SPACE);

        c.addButtonCodeAction(Input.Buttons.LEFT, ButtonAction.BUTTON_LEFT);
        c.addButtonCodeAction(Input.Buttons.RIGHT, ButtonAction.BUTTON_RIGHT);

        return c;
    }

    public KeyAction getKeyActionCode(int code) {
        return keycodes_actions.get(code);
    }

    public ButtonAction getButtonActionCode(int code) {
        return buttoncodes_actions.get(code);
    }

    private void addKeyCodeAction(int code, KeyAction action) {
        keycodes_actions.put(code, action);
    }

    private void addButtonCodeAction(int code, ButtonAction action) {
        buttoncodes_actions.put(code, action);
    }
}
