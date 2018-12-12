package com.glhf.bomberball.config;

import com.badlogic.gdx.Input;
import com.glhf.bomberball.InputHandler.Action;

import java.util.HashMap;

/**
 * class InputsConfig
 *
 * Defines all inputs key codes and button codes
 * Used by InputHandler class
 *
 * @author nayala
 */
public class InputsConfig extends Config {

    /** Map to convert key codes to actions */
    public HashMap<Integer, Action> keycodes_actions;
    /** Map to convert button codes to actions */
    public HashMap<Integer, Action> buttoncodes_actions;

    /**
     * Default constructor
     */
    private InputsConfig() {
        keycodes_actions = new HashMap<>();
        buttoncodes_actions = new HashMap<>();
    }

    /**
     * Method to generate the default InputsConfig class
     * @return default inputs configuration class
     */
    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.addKeyCodeAction(Input.Keys.DOWN, Action.KEY_DOWN);
        c.addKeyCodeAction(Input.Keys.UP, Action.KEY_UP);
        c.addKeyCodeAction(Input.Keys.LEFT, Action.KEY_LEFT);
        c.addKeyCodeAction(Input.Keys.RIGHT, Action.KEY_RIGHT);
        c.addKeyCodeAction(Input.Keys.SPACE, Action.KEY_SPACE);
        c.addKeyCodeAction(Input.Keys.NUMPAD_4, Action.KEY_DROP_LEFT);
        c.addKeyCodeAction(Input.Keys.NUMPAD_8, Action.KEY_DROP_UP);
        c.addKeyCodeAction(Input.Keys.NUMPAD_6, Action.KEY_DROP_RIGHT);
        c.addKeyCodeAction(Input.Keys.NUMPAD_2, Action.KEY_DROP_DOWN);
        c.addKeyCodeAction(Input.Keys.D, Action.KEY_MOVE);
        c.addKeyCodeAction(Input.Keys.B, Action.KEY_BOMB);
        c.addKeyCodeAction(Input.Keys.F, Action.KEY_ENDTURN);


        c.addButtonCodeAction(Input.Buttons.LEFT, Action.MOUSE_LEFT);
        c.addButtonCodeAction(Input.Buttons.RIGHT, Action.MOUSE_RIGHT);

        return c;
    }

    public static InputsConfig get() {
        return get("default_inputs", InputsConfig.class);
    }

    /**
     * Links a key code with a Action
     * @param key_code
     * @param action
     */
    private void addKeyCodeAction(int key_code, Action action) {
        keycodes_actions.put(key_code, action);
    }

    /**
     * Links a button code with a Action
     * @param button_code
     * @param action
     */
    private void addButtonCodeAction(int button_code, Action action) {
        buttoncodes_actions.put(button_code, action);
    }

    /**
     * Converts key codes to InputHandler.KeyActions
     * @param key_code
     * @return Action corresponding to key_code
     */
    public Action getKeyActionCode(int key_code) {
        return keycodes_actions.get(key_code);
    }

    /**
     * Converts button codes to InputHandler.ButtonActions
     * @param button_code
     * @return Action corresponding to key_code
     */
    public Action getButtonActionCode(int button_code) {
        return buttoncodes_actions.get(button_code);
    }

    /**
     * @param key_code
     * @return key_code is assigned to a Action
     */
    public boolean isKeyCodeAssigned(int key_code) {
        return keycodes_actions.containsKey(key_code);
    }

    /**
     * @param button_code
     * @return button_code is assigned to a ButtonCode
     */
    public boolean isButtonCodeAssigned(int button_code) {
        return buttoncodes_actions.containsKey(button_code);
    }
}
