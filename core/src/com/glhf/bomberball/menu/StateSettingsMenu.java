package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;

public class StateSettingsMenu extends StateMenu {

    //Constructor
    public StateSettingsMenu() {
        super();
        centerButtons.addActor(new ParameterString("skin of player1"));
    }

    public abstract class Parameter extends HorizontalGroup {
        private Label label;
        public Parameter(String name) {
            super();
            left();

            LabelStyle style = new LabelStyle(); //TODO au lieu de faire des textButton_style pour chaque élément créer un skin général
            style.font = textButton_style.font;
            label = new Label(name, style);
            addActor(label);
        }
    }
    public class ParameterString extends Parameter {
        private TextField value;
        public ParameterString(String name) {
            super(name);
            TextFieldStyle style = new TextFieldStyle();
            style.font = textButton_style.font;
            style.fontColor = Color.WHITE;
            style.focusedFontColor = Color.RED;
            value = new TextField("", style);
            addActor(value);
        }
    }
    public class ParameterInt extends Parameter {
        private Slider value;
        public ParameterInt(String name, float min, float max, float step) {
            super(name);
            SliderStyle style = new SliderStyle(); //TODO au lieu de faire des textButton_style pour chaque élément créer un skin général
            style.knob = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));

            value = new Slider(min, max, step, false, style);
            addActor(value);
        }
    }
}