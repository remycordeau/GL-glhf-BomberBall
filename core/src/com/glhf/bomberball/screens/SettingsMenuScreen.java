package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Graphics.GUI;
import com.glhf.bomberball.config.GameConfig;

import java.lang.reflect.Field;

public class SettingsMenuScreen extends MenuScreen {

    //Constructor
    public SettingsMenuScreen() {
        super();
        centerButtons.addActor(new ParameterInt("number of players", 1, 4, 1));
        for (Field field : GameConfig.class.getDeclaredFields()) {
            if(field.getType().equals(String.class)){
                centerButtons.addActor(new ParameterString(field.getName()));
            }else if(field.getType().equals(int.class)){
                centerButtons.addActor(new ParameterInt(field.getName(),1,10,1));
            }else {

            }
        }
        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        centerButtons.addActor(cancelButton);

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
        private HorizontalGroup value;
        public ParameterInt(String name, float min, float max, float step) {
            super(name);
            value = new HorizontalGroup();
            Slider slider = new Slider(min, max, step, false, GUI.getSkin());
            Label label = new Label("0", GUI.getSkin());
            slider.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    label.setText(""+(int)((Slider)actor).getValue());
                }
            });
            value.addActor(label);
            value.addActor(slider);
            addActor(value);
        }
    }
}