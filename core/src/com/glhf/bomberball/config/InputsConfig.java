package com.glhf.bomberball.config;

import com.badlogic.gdx.Input;
import com.glhf.bomberball.menu.InputHandler;
import com.glhf.bomberball.menu.InputHandler.KeyAction;

import java.util.HashMap;

public class InputsConfig extends Config {

    private HashMap<Integer, KeyAction> keycodes_actions;

    public InputsConfig() {
        keycodes_actions = new HashMap<Integer, KeyAction>();
    }

    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.addCodeAction(Input.Keys.DOWN, KeyAction.KEY_DOWN);
        c.addCodeAction(Input.Keys.UP, KeyAction.KEY_UP);
        c.addCodeAction(Input.Keys.LEFT, KeyAction.KEY_LEFT);
        c.addCodeAction(Input.Keys.RIGHT, KeyAction.KEY_RIGHT);
        c.addCodeAction(Input.Keys.SPACE, KeyAction.KEY_SPACE);

        return c;
    }

    public KeyAction getKeyActionCode(int code) {
        return keycodes_actions.get(code);
    }

    private void addCodeAction(int code, KeyAction action) {
        keycodes_actions.put(code, action);
    }
}
