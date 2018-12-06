package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;

public class StateSettingsMenu extends StateMenu {

    //Constructor
    public StateSettingsMenu() {
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
            value = new SelectBox<String>(Graphics.GUI.getSkin());
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