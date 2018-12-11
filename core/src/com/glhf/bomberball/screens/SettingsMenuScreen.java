package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.glhf.bomberball.Graphics;

public class SettingsMenuScreen extends MenuScreen {

    //Constructor
    public SettingsMenuScreen() {
        super();
        centerButtons.addActor(new ParameterString("skin of player1"));
        centerButtons.addActor(new ParameterInt("number of players", 1, 4, 1));
    }

    public abstract class Parameter extends HorizontalGroup {
        private Label label;
        public Parameter(String name) {
            super();
            left();

            label = new Label(name, Graphics.GUI.getSkin());
            addActor(label);
        }
    }
    public class ParameterString extends Parameter {
        private SelectBox<String> value;
        public ParameterString(String name) {
            super(name);
            value = new SelectBox<>(Graphics.GUI.getSkin());
            value.setItems("test1","test2","test3","test4");
            addActor(value);
        }
    }
    public class ParameterInt extends Parameter {
        private Slider value;
        public ParameterInt(String name, float min, float max, float step) {
            super(name);
            value = new Slider(min, max, step, false, Graphics.GUI.getSkin());
            addActor(value);
        }
    }
}