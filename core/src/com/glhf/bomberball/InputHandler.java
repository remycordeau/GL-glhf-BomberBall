package com.glhf.bomberball;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.config.InputsConfig;

import java.util.ArrayList;

/**
 * class InputHandler
 *
 * Class to handle inputs, use it to register input handlersList on actions.
 *
 * Example :
 *
 * inputHandler.registerActionHandler(Action.KEY_UP, () -> { keyUpHandler() })
 * to register keyUpHandler as an handler on KEY_UP action
 *
 * inputHandler.registerButtonAction(Action.MOUSE_LEFT, (x, y) -> { buttonLeftHandler(x, y) })
 * to register buttonLeftHandler as an handler on MOUSE_LEFT action
 *
 * @author nayala
 */
public class InputHandler extends InputListener {

    /**
     * InputsConfig class to link actions to codes
     */
    private InputsConfig inputs_config;

    /**
     * KeyActions
     * All registrable key actions
     */
    public enum Action {
        KEY_UP,
        KEY_DOWN,
        KEY_LEFT,
        KEY_RIGHT,
        KEY_SPACE,
        KEY_DROP_RIGHT,
        KEY_DROP_UP,
        KEY_DROP_LEFT,
        KEY_DROP_DOWN,
        KEY_MOVE,
        KEY_ENDTURN,
        KEY_BOMB,
        MOUSE_LEFT,
        MOUSE_RIGHT
    }

    private ArrayList<ArrayList<ActionHandler>> handlersList;
    private boolean locked;

    /**
     * Default constructor
     * Creates an InputHandler with default input codes configurations
     */
    public InputHandler() {
        inputs_config = InputsConfig.defaultConfig();
        inputs_config.exportConfig("default_inputs");
        handlersList = new ArrayList<ArrayList<ActionHandler>>();
        for(int i=0; i<Action.values().length; i++){
            handlersList.add(new ArrayList<>());
        }
    }

    /**
     * You shouln't use this method
     */
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (!locked && inputs_config.isKeyCodeAssigned(keycode)) {
            ArrayList<ActionHandler> handlers = this.handlersList.get(inputs_config.getKeyActionCode(keycode).ordinal());
            for (ActionHandler handler : handlers){
                handler.handleKey();
            }
        }
        return false;
    }

    /**
     * You shouln't use this method
     */
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (!locked && inputs_config.isButtonCodeAssigned(button)) {
            ArrayList<ActionHandler> handlers = this.handlersList.get(inputs_config.getButtonActionCode(button).ordinal());
            for (ActionHandler handler : handlers){
                handler.handleClick(x,y);
            }
        }
        return false;
    }

    /**
     * Registers a key action.
     * @param key_action action to register
     * @param handler handler to call when key_action is triggered
     */
    public void registerActionHandler(Action key_action, KeyActionHandler handler) {
        handlersList.get(key_action.ordinal()).add((ActionHandler)handler);
    }
    public void registerActionHandler(Action key_action, ClickActionHandler handler) {
        handlersList.get(key_action.ordinal()).add((ActionHandler)handler);
    }

    /**
     * Interface to handle key actions.
     * Use lambda functions :
     * ActionHandler key_handler = () -> { handle_key_action(); };
     */
    public interface KeyActionHandler {
        void handleKey();
    }
    public interface ClickActionHandler {
        void handleClick(float x, float y);
    }

    public class ActionHandler {
        public void handle(InputEvent e){

        }
    }

    public void lock(boolean locked) {
        this.locked = locked;
    }
}
