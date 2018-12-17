package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Graphics.GUI;
import com.glhf.bomberball.InputHandler;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.config.AppConfig;
import com.glhf.bomberball.config.InputsConfig;
import com.glhf.bomberball.utils.Resolutions;

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
        table.setFillParent(true);
        addUI(table);


        final int NB_TABS = 2;
        TextButton[] labels = new TextButton[NB_TABS];
        Stack stack = new Stack();
        Table[] contents = new Table[NB_TABS];

        final ClickListener listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                for(int i=0; i<NB_TABS; i++)
                    contents[i].setVisible(labels[i].isChecked());
                stack.swapActor(0,1);
            }
        };

        labels[0] = new TextButton("general", Graphics.GUI.getSkin());
        labels[0].addListener(listener);

        labels[1] = new TextButton("inputs", Graphics.GUI.getSkin());
        labels[1].addListener(listener);

        contents[0] = new Table();
        contents[0].add(new ParameterScreenSize()).growX().row();
        CheckBox fullscreen = new CheckBox("fullscreen", GUI.getSkin());
        fullscreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                final AppConfig config = AppConfig.get();
                if(((CheckBox)actor).isChecked())
                    Gdx.graphics.setFullscreenMode(displayMode);
                else
                    Bomberball.resizeWindow(config.resolution);
                config.fullscreen = ((CheckBox)actor).isChecked();
                config.exportConfig();
            }
        });
        contents[0].add(fullscreen).growX().row();

        //ajout de chaque paramètre pour inputs
        InputsConfig inputsConfig = InputsConfig.get();
        HashMap<Action,String[]> map = inputsConfig.getReversedInputMap();
        contents[1] = new Table();
        for(Action a : map.keySet()){
            new ParameterInput(contents[1],a, map.get(a));
        }

        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<>();
        for(int i=0; i<NB_TABS; i++) {
            table.add(labels[i]).growX();
            buttonGroup.add(labels[i]);
        }
        table.row();
        for(int i=0; i<NB_TABS; i++)
            stack.add(new ScrollPane(contents[i]));
        table.add(stack).colspan(NB_TABS).growX().row();

        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        table.add(cancelButton).colspan(NB_TABS).growX();

        stack.swapActor(0,1);
        labels[0].setChecked(true);
        contents[1].setVisible(false);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
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
            SelectBox<Resolutions> value = new SelectBox<Resolutions>(Graphics.GUI.getSkin());
            value.setItems(Resolutions.values());
            value.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Bomberball.resizeWindow(value.getSelected());
                    final AppConfig config = AppConfig.get();
                    config.resolution = value.getSelected();
                    config.exportConfig();
                }
            });
            this.addActor(value);
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