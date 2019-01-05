package com.glhf.bomberball.config;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.glhf.bomberball.InputHandler.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * class InputsConfig
 *
 * Defines all inputs key codes and button codes
 * Used by InputHandler class
 *
 * @author nayala
 */
public class InputsConfig extends Config {

    private String[][] id_list;

    /** Map to convert key codes to actions */
    private transient HashMap<String, List<Action>> map_code_to_action;

    public enum KeyPriority {
        PRIMARY,
        SECONDARY
    }

    /**
     * Default constructor
     */
    private InputsConfig() {
        id_list = new String[Action.values().length][KeyPriority.values().length];
    }

    /**
     * Method to generate the default InputsConfig class
     * @return default inputs configuration class
     */
    public static InputsConfig defaultConfig() {
        InputsConfig c = new InputsConfig();

        c.setInputKeyCode(Action.MOVE_DOWN, Keys.DOWN);
        c.setInputKeyCode(Action.MOVE_UP, Keys.UP);
        c.setInputKeyCode(Action.MOVE_LEFT, Keys.LEFT);
        c.setInputKeyCode(Action.MOVE_RIGHT, Keys.RIGHT);
        c.setInputKeyCode(Action.DROP_BOMB_LEFT, Keys.NUMPAD_4);
        c.setInputKeyCode(Action.DROP_BOMB_UP, Keys.NUMPAD_8);
        c.setInputKeyCode(Action.DROP_BOMB_RIGHT, Keys.NUMPAD_6);
        c.setInputKeyCode(Action.DROP_BOMB_DOWN, Keys.NUMPAD_2);
        c.setInputKeyCode(Action.MODE_MOVE, Keys.D);
        c.setInputKeyCode(Action.MODE_BOMB, Keys.B);
        c.setInputKeyCode(Action.ENDTURN, Keys.F);
        c.setInputKeyCode(Action.ENDTURN, Keys.SPACE);
        c.setInputKeyCode(Action.DROP_BOMB, Keys.L);
        c.setInputKeyCode(Action.MENU_GO_BACK, Keys.DEL);


        c.setInputButtonCode(Action.DROP_BOMB, Buttons.LEFT);
        c.setInputButtonCode(Action.DROP_SELECTED_OBJECT, Buttons.LEFT);
        //c.setInputButtonCode(Buttons.RIGHT, Action.DROP_BOMB);

        return c;
    }

    public static InputsConfig get() {
        InputsConfig inputsConfig = get("default_inputs", InputsConfig.class);
        inputsConfig.updateReversedMap();
        return inputsConfig;
    }
    public void exportConfig() {
        exportConfig("default_inputs");
    }

    /**
     * Links a key code with a Action
     * @param action
     * @param key_code
     */
    public void setInputKeyCode(Action action, int key_code) {
        setInputKeyCode(action, key_code, KeyPriority.PRIMARY);
    }

    /**
     * Links a button code with a Action
     * @param action
     * @param mouse_button_code
     */
    public void setInputButtonCode(Action action, int mouse_button_code) {
        setInputButtonCode(action, mouse_button_code, KeyPriority.PRIMARY);
    }

    /**
     * Links a key code with a Action depending on KeyPriority
     * @param action
     * @param key_code
     */
    public void setInputKeyCode(Action action, int key_code, KeyPriority priority) {
        setInput(action, priority, getIDForKeyCode(key_code));
    }

    /**
     * Links a button code with a Action depending on KeyPriority
     * @param action
     * @param mouse_button_code
     */
    public void setInputButtonCode(Action action, int mouse_button_code, KeyPriority priority) {
        setInput(action, priority, getIDForMouseButtonCode(mouse_button_code));
    }


    /**
     * Converts key codes to Actions
     * @param key_code
     * @return Action corresponding to key_code
     */
    public List<Action> getKeyCodeActions(int key_code) {
        return getAction(getIDForKeyCode(key_code));
    }

    /**
     * Converts button codes to Actions
     * @param button_code
     * @return Action corresponding to key_code
     */
    public List<Action> getButtonCodeActions(int button_code) {
        return getAction(getIDForMouseButtonCode(button_code));
    }

    /**
     * @param key_code
     * @return key_code is assigned to a Action
     */
    public boolean isKeyCodeAssigned(int key_code) {
        return getKeyCodeActions(key_code).size() > 0;
    }

    /**
     * @param button_code
     * @return button_code is assigned to a ButtonCode
     */
    public boolean isButtonCodeAssigned(int button_code) {
        return getButtonCodeActions(button_code).size() > 0;
    }

    public String[][] getIdList(){
        return id_list;
    }

    public void setInput(Action action, KeyPriority priority, String code_id){
        id_list[action.ordinal()][priority.ordinal()] = code_id;
        updateReversedMap();
        exportConfig();
    }


    public void resetInput(String code_id, KeyPriority profile){
        setInput(null, profile, code_id);

//        //if there is no more action for a key, the key is removed
//        for(Action a : map_code_to_action.get(code_id))
//            if(a!=null)
//                return;
//        map_code_to_action.remove(code_id);
    }

    //private functions =============

    private List<Action> getAction(String key) {
        if(map_code_to_action.containsKey(key)) {
            return map_code_to_action.get(key);
        }
        return new ArrayList<>();
    }

    public static String getIDForKeyCode(int key_code){
        return "K"+key_code;
    }
    public static String getIDForMouseButtonCode(int mouse_button_code){
        return "M"+mouse_button_code;
    }

    private void updateReversedMap() {
        map_code_to_action = new HashMap<>();
        for(Action a : Action.values()){
            for(KeyPriority p : KeyPriority.values()){
                String id = id_list[a.ordinal()][p.ordinal()];
                if(id==null)continue;
                if(!map_code_to_action.containsKey(id)) {
                    map_code_to_action.put(id, new ArrayList<Action>());
                }
                map_code_to_action.get(id).add(a);
            }
        }
    }
}
