package com.glhf.bomberball;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.config.InputsConfig;

/**
 * class InputHandler
 *
 * Class to handle inputs, use it to register input handlers on actions.
 *
 * Example :
 *
 * inputHandler.registerKeyAction(KeyAction.KEY_UP, () -> { keyUpHandler() })
 * to register keyUpHandler as an handler on KEY_UP action
 *
 * inputHandler.registerButtonAction(ButtonAction.BUTTON_LEFT, (x, y) -> { buttonLeftHandler(x, y) })
 * to register buttonLeftHandler as an handler on BUTTON_LEFT action
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
    public enum KeyAction {
        KEY_UP,
        KEY_DOWN,
        KEY_LEFT,
        KEY_RIGHT,
        KEY_SPACE
    }

    /**
     * Buttons
     * All registrable button actions
     */
    public enum ButtonAction {
        BUTTON_LEFT,
        BUTTON_RIGHT
    }

    private KeyActionHandler[] key_handlers = new KeyActionHandler[KeyAction.values().length];
    private ButtonActionHandler[] button_handlers = new ButtonActionHandler[KeyAction.values().length];
    private boolean locked;

    /**
     * Default constructor
     * Creates an InputHandler with default input codes configurations
     */
    public InputHandler() {
        // TODO : importer la bonne config d'inputs
        inputs_config = InputsConfig.defaultConfig();
    }

    /**
     * You shouln't use this method
     */
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (!locked && inputs_config.isKeyCodeAssigned(keycode)) {
            KeyActionHandler handler = key_handlers[inputs_config.getKeyActionCode(keycode).ordinal()];
            if (handler != null) {
                handler.handle();
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
            ButtonActionHandler handler = button_handlers[inputs_config.getButtonActionCode(button).ordinal()];
            if (handler != null) {
                handler.handle(x, y);
            }
        }
        return false;
    }

    /**
     * Registers a key action.
     * @param key_action action to register
     * @param key_handler handler to call when key_action is triggered
     */
    public void registerKeyAction(KeyAction key_action, KeyActionHandler key_handler) {
        key_handlers[key_action.ordinal()] = key_handler;
    }

    /**
     * Registers a button action.
     * @param button_action action to register
     * @param button_handler handler to call when button_action is triggered
     */
    public void registerButtonAction(ButtonAction button_action, ButtonActionHandler button_handler) {
        button_handlers[button_action.ordinal()] = button_handler;
    }

    /**
     * Interface to handle key actions.
     * Use lambda functions :
     * KeyActionHandler key_handler = () -> { handle_key_action(); };
     */
    public interface KeyActionHandler {
        void handle();
    }

    /**
     * Interface to handle button actions.
     * Use lambda functions :
     * KeyActionHandler button_handler = (x, y) -> { handle_button_action(x, y); };
     */
    public interface ButtonActionHandler {
        void handle(float x, float y);
    }

    public void lock(boolean locked) {
        this.locked = locked;
    }
}
