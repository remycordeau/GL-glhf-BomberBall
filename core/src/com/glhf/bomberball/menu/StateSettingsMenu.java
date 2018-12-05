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

            LabelStyle style = new LabelStyle(); //TODO au lieu de faire des textButton_style pour chaque élément créer un skin général
            style.font = textButton_style.font;
            label = new Label(name, style);
            addActor(label);
        }
    }
    public class ParameterString extends Parameter {
        private SelectBox<String> value;
        public ParameterString(String name) {
            super(name);
            SelectBoxStyle style = new SelectBoxStyle();
            style.font = textButton_style.font;
            style.fontColor = Color.BLUE;
            style.background = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.listStyle = new ListStyle();
            style.listStyle.font = textButton_style.font;
            style.listStyle.fontColorSelected = Color.WHITE;
            style.listStyle.fontColorUnselected = Color.RED;
            style.listStyle.selection = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.listStyle.background = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.scrollStyle = new ScrollPaneStyle();
            style.scrollStyle.background = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.scrollStyle.hScroll = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.scrollStyle.hScrollKnob = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.scrollStyle.vScroll = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.scrollStyle.vScrollKnob = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            value = new SelectBox<String>(style);
            value.setItems("test1","test2","test3","test4");
            addActor(value);
        }
    }
    public class ParameterInt extends Parameter {
        private Slider value;
        public ParameterInt(String name, float min, float max, float step) {
            super(name);
            SliderStyle style = new SliderStyle(); //TODO au lieu de faire des textButton_style pour chaque élément créer un skin général
            style.knob = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));
            style.background = new TextureRegionDrawable(Graphics.Sprites.get("bomb"));

            value = new Slider(min, max, step, false, style);
            addActor(value);
        }
    }
}