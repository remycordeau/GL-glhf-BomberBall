package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Graphics.GUI;
import com.glhf.bomberball.InputHandler;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.config.InputsConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.table;

public class SettingsMenuScreen extends AbstractScreen {

    //Constructor
    public SettingsMenuScreen() {
        super();
        Table table = new Table();
        addUI(table);
        table.setFillParent(true);
        Table inputsParams = new Table();
        ScrollPane inputsPane = new ScrollPane(inputsParams);
        Table appParams = new Table();
        ScrollPane appPane = new ScrollPane(appParams);
        TextButton labelGeneral = new TextButton("general", Graphics.GUI.getSkin());
        labelGeneral.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                table.removeActor(inputsPane);
                table.addActorAt(2, appPane);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        table.add(labelGeneral).growX();
        TextButton labelInputs = new TextButton("inputs", Graphics.GUI.getSkin());
        labelInputs.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                table.removeActor(appPane);
                table.addActorAt(2, inputsPane);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        table.add(labelInputs).growX().row();

        appParams.add(new ParameterScreenSize()).growX().row();
        appParams.add(new CheckBox("fullscreen", Graphics.GUI.getSkin())).growX().row();
        table.add(appPane).grow().colspan(2).row();

        //ajout de chaque paramètre pour inputs
        InputsConfig inputsConfig = InputsConfig.get();
        HashMap<Action,String[]> map = inputsConfig.getReversedInputMap();
        for(Action a : map.keySet()){
            new ParameterInput(inputsParams,a, map.get(a));
        }

        table.add(inputsPane).grow().colspan(2).row();
        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        table.add(cancelButton).colspan(2).growX();
    }

    public abstract class Parameter extends HorizontalGroup {
        protected Label label;
        public Parameter(String name) {
            super();
            left();

            label = new Label(name, Graphics.GUI.getSkin());
            addActor(label);
        }
    }

    private class ParameterScreenSize extends Parameter {
        public ParameterScreenSize() {
            super("screen size");

        }
    }

    private class ParameterInput {

        public ParameterInput(Table table, Action a, String[] codes) {
            Label label = new Label(a.toString(), Graphics.GUI.getSkin(), "small");
            table.add(label).growX();
            for(String code: codes) {
                TextButton textButton = new TextButton("code:" + code, Graphics.GUI.getSkin(), "small");
                textButton.addListener(new ChangeInputListener(a, code));
                table.add(textButton).growX();
            }
            table.row();
        }

        private class ChangeInputListener extends InputListener {
            private InputsConfig config = InputsConfig.get();
            private Object o;
            public ChangeInputListener(Object o, String f) {
                this.o = o;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO spash screen
                System.out.println("spash screen de gestion des inputs a faire");
                return super.touchDown(event, x, y, pointer, button);
            }
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