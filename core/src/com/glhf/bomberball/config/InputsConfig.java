package com.glhf.bomberball.config;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.glhf.bomberball.InputHandler.Action;

import java.util.ArrayList;
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
    public HashMap<String, Action> input_map;
    public ArrayList<HashMap<String, Action>> profiles;

    private InputProfile inputProfile;

    public enum InputProfile {
        MOVE,
        BOMB
    }

    /**
     * Default constructor
     */
    private InputsConfig() {
        input_map = new HashMap<>();
        profiles = new ArrayList<>();
        for(InputProfile profile : InputProfile.values())
            profiles.add(new HashMap<>());
    }

    /**
     * Method to generate the default InputsConfig class
     * @return default inputs configuration class
     */
    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.addKeyCodeAction(Keys.DOWN, Action.MOVE_DOWN);
        c.addKeyCodeAction(Keys.UP, Action.MOVE_UP);
        c.addKeyCodeAction(Keys.LEFT, Action.MOVE_LEFT);
        c.addKeyCodeAction(Keys.RIGHT, Action.MOVE_RIGHT);
        c.addKeyCodeAction(Keys.LEFT, Action.DROP_BOMB_LEFT, InputProfile.BOMB);
        c.addKeyCodeAction(Keys.UP, Action.DROP_BOMB_UP, InputProfile.BOMB);
        c.addKeyCodeAction(Keys.RIGHT, Action.DROP_BOMB_RIGHT, InputProfile.BOMB);
        c.addKeyCodeAction(Keys.DOWN, Action.DROP_BOMB_DOWN, InputProfile.BOMB);
        c.addKeyCodeAction(Keys.NUMPAD_4, Action.DROP_BOMB_LEFT);
        c.addKeyCodeAction(Keys.NUMPAD_8, Action.DROP_BOMB_UP);
        c.addKeyCodeAction(Keys.NUMPAD_6, Action.DROP_BOMB_RIGHT);
        c.addKeyCodeAction(Keys.NUMPAD_2, Action.DROP_BOMB_DOWN);
        c.addKeyCodeAction(Keys.D, Action.MODE_MOVE);
        c.addKeyCodeAction(Keys.B, Action.MODE_BOMB);
        c.addKeyCodeAction(Keys.F, Action.ENDTURN);
        c.addKeyCodeAction(Keys.SPACE, Action.ENDTURN);
        c.addKeyCodeAction(Keys.L, Action.DROP_BOMB);
        c.addKeyCodeAction(Keys.DEL, Action.MENU_GO_BACK);


        c.addButtonCodeAction(Buttons.LEFT, Action.DROP_BOMB);
        //c.addButtonCodeAction(Buttons.RIGHT, Action.DROP_BOMB);

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
        input_map.put("K"+key_code, action);
    }

    /**
     * Links a button code with a Action
     * @param mouse_button_code
     * @param action
     */
    private void addButtonCodeAction(int mouse_button_code, Action action) {
        input_map.put("M"+mouse_button_code, action);
    }

    /**
     * Links a key code with a Action depending on an InputProfile
     * @param key_code
     * @param action
     */
    private void addKeyCodeAction(int key_code, Action action, InputProfile profile) {
        profiles.get(profile.ordinal()).put("K"+key_code, action);
    }

    /**
     * Links a button code with a Action depending on an InputProfile
     * @param mouse_button_code
     * @param action
     */
    private void addButtonCodeAction(int mouse_button_code, Action action, InputProfile profile) {
        profiles.get(profile.ordinal()).put("M"+mouse_button_code, action);
    }

    /**
     * Converts key codes to Actions
     * @param key_code
     * @return Action corresponding to key_code
     */
    public Action getKeyActionCode(int key_code) {
        String key = "K" + key_code;
        if(inputProfile!=null && profiles.get(inputProfile.ordinal()).containsKey(key))
            return profiles.get(inputProfile.ordinal()).get(key);
        return input_map.get(key);
    }

    /**
     * Converts button codes to Actions
     * @param button_code
     * @return Action corresponding to key_code
     */
    public Action getButtonActionCode(int button_code) {
        String key = "M" + button_code;
        if(inputProfile!=null && profiles.get(inputProfile.ordinal()).containsKey(key)) {
            return profiles.get(inputProfile.ordinal()).get(key);
        }
        return input_map.get(key);
    }

    /**
     * @param key_code
     * @return key_code is assigned to a Action
     */
    public boolean isKeyCodeAssigned(int key_code) {
        String key = "K" + key_code;
        return (inputProfile!=null && profiles.get(inputProfile.ordinal()).containsKey(key)) || input_map.containsKey(key);
    }

    /**
     * @param button_code
     * @return button_code is assigned to a ButtonCode
     */
    public boolean isButtonCodeAssigned(int button_code) {
        String key = "M" + button_code;
        return (inputProfile!=null && profiles.get(inputProfile.ordinal()).containsKey(key)) || input_map.containsKey(key);
    }

    public void setInputProfile(InputProfile inputProfile) {
        this.inputProfile = inputProfile;
    }
}
