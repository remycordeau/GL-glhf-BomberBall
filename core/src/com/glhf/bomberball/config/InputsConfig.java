package com.glhf.bomberball.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.glhf.bomberball.menu.InputHandler;
import com.glhf.bomberball.menu.InputHandler.Events;

import java.util.HashMap;

public class InputsConfig extends Config {

    public int move_up = Input.Keys.UP;
    public int move_left = Input.Keys.LEFT;
    public int move_down = Input.Keys.DOWN;
    public int move_right = Input.Keys.RIGHT;

    private HashMap<Events, Integer> map;

    public InputsConfig() {
        map = new HashMap<Events, Integer>();
        map.put(Events.KEY_DOWN, Input.Keys.DOWN);
    }
}
