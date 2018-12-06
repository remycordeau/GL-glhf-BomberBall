package com.glhf.bomberball.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.config.InputsConfig;

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

    private KeyActionHandler[] key_handlers = new KeyActionHandler[KeyAction.values().length];
    private ButtonActionHandler[] button_handlers = new ButtonActionHandler[KeyAction.values().length];

    public InputHandler() {
        // TODO : importer la bonne config d'inputs
        inputs_config = InputsConfig.defaultConfig();
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (inputs_config.isKeyCodeAssigned(keycode)) {
            KeyActionHandler handler = key_handlers[inputs_config.getKeyActionCode(keycode).ordinal()];
            if (handler != null) {
                handler.handle();
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (inputs_config.isButtonCodeAssigned(button)) {
            ButtonActionHandler handler = button_handlers[inputs_config.getButtonActionCode(button).ordinal()];
            if (handler != null) {
                handler.handle(x, y);
            }
        }
        return false;
    }

    public void registerKeyAction(KeyAction key_action, KeyActionHandler key_handler) {
        key_handlers[key_action.ordinal()] = key_handler;
    }
    public void registerButtonAction(ButtonAction button_action, ButtonActionHandler button_handler) {
        button_handlers[button_action.ordinal()] = button_handler;
    }

    public interface KeyActionHandler {
        void handle();
    }

    public interface ButtonActionHandler {
        void handle(float x, float y);
    }
}
