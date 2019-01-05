package com.glhf.bomberball.config;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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
    private HashMap<String, Action[]> map_code_to_action;

    private transient String[][] id_list;
    private transient boolean has_id_list_changed;

    private transient InputProfile input_profile;

    public enum InputProfile {
        DEFAULT,
        MOVE,
        BOMB
    }

    /**
     * Default constructor
     */
    private InputsConfig() {
        map_code_to_action = new HashMap<>();
        input_profile = InputProfile.DEFAULT;
        has_id_list_changed =true;
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
        c.addButtonCodeAction(Buttons.LEFT, Action.DROP_SELECTED_OBJECT);
        //c.addButtonCodeAction(Buttons.RIGHT, Action.DROP_BOMB);

        return c;
    }

    public static InputsConfig get() {
        return get("default_inputs", InputsConfig.class);
    }
    public void exportConfig() {
        exportConfig("default_inputs");
    }

    /**
     * Links a key code with a Action
     * @param key_code
     * @param action
     */
    public void addKeyCodeAction(int key_code, Action action) {
        addKeyCodeAction(key_code, action, InputProfile.DEFAULT );
    }

    /**
     * Links a button code with a Action
     * @param mouse_button_code
     * @param action
     */
    public void addButtonCodeAction(int mouse_button_code, Action action) {
        addButtonCodeAction(mouse_button_code, action, InputProfile.DEFAULT );
    }


    /**
     * Links a key code with a Action depending on an InputProfile
     * @param key_code
     * @param action
     */
    public void addKeyCodeAction(int key_code, Action action, InputProfile profile) {
        addAction(getIDForKeyCode(key_code), action, profile );
    }

    /**
     * Links a button code with a Action depending on an InputProfile
     * @param mouse_button_code
     * @param action
     */
    public void addButtonCodeAction(int mouse_button_code, Action action, InputProfile profile) {
        addAction(getIDForMouseButtonCode(mouse_button_code), action, profile );
    }

    /**
     * Converts key codes to Actions
     * @param key_code
     * @return Action corresponding to key_code
     */
    public Action getKeyCodeAction(int key_code) {
        return getAction(getIDForKeyCode(key_code));
    }

    /**
     * Converts button codes to Actions
     * @param button_code
     * @return Action corresponding to key_code
     */
    public Action getButtonCodeAction(int button_code) {
        return getAction(getIDForMouseButtonCode(button_code));
    }

    /**
     * @param key_code
     * @return key_code is assigned to a Action
     */
    public boolean isKeyCodeAssigned(int key_code) {
        return getKeyCodeAction(key_code) != null;
    }

    /**
     * @param button_code
     * @return button_code is assigned to a ButtonCode
     */
    public boolean isButtonCodeAssigned(int button_code) {
        return getButtonCodeAction(button_code) != null;
    }

    public void setInputProfile(InputProfile input_profile) {
        this.input_profile = input_profile;
    }

    public String[][] getIdList(){
        if(has_id_list_changed)
            reverseInputMap();
        return id_list;
    }

    public void addAction(String code_id, Action action, InputProfile profile){
        has_id_list_changed =true;
        if(!map_code_to_action.containsKey(code_id)) {
            map_code_to_action.put(code_id, new Action[InputProfile.values().length]);
        }
        map_code_to_action.get(code_id)[profile.ordinal()] = action;
    }


    public void delAction(String code_id, InputProfile profile){
        addAction(code_id, null,  profile);

        //if there is no more action for a key, the key is removed
        for(Action a : map_code_to_action.get(code_id))
            if(a!=null)
                return;
        map_code_to_action.remove(code_id);
    }

    //private functions =============

    private Action getAction(String key) {
        if(map_code_to_action.containsKey(key)){
            Action[] actions = map_code_to_action.get(key);
            if(actions[input_profile.ordinal()] != null)
                return actions[input_profile.ordinal()];
            return actions[InputProfile.DEFAULT.ordinal()];
        }
        return null;
    }

    public static String getIDForKeyCode(int key_code){
        return "K"+key_code;
    }
    public static String getIDForMouseButtonCode(int mouse_button_code){
        return "M"+mouse_button_code;
    }

    private void reverseInputMap() {
        id_list = new String[Action.values().length][InputProfile.values().length];
        for(String code : map_code_to_action.keySet()){
            Action[] actions = map_code_to_action.get(code);
            for(int i=0; i<InputProfile.values().length; i++){
                if(actions[i]==null)continue;
                id_list[actions[i].ordinal()][i] = code;
            }
        }
        has_id_list_changed = false;
    }
}
