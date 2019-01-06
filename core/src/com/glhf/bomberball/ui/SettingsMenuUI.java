package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Graphics.GUI;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.config.AppConfig;
import com.glhf.bomberball.config.InputsConfig;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.SettingsMenuScreen;
import com.glhf.bomberball.utils.Resolutions;

public class SettingsMenuUI extends Table {

    private final EventListener button_listener;
    private final Table[] contents;
    private final TextButton[] labels;
    private final ButtonGroup<InputButton> inputsButtonGroup;
    private final ClickListener labels_listener;
    private SettingsMenuScreen screen;

    //Constructor
    public SettingsMenuUI(SettingsMenuScreen screen) {
        super();
        this.screen = screen;
        AppConfig appConfig = AppConfig.get();
        InputsConfig inputsConfig = InputsConfig.get();
        this.setFillParent(true);

//        input_handler.setSettingsMenuScreen(this);

        final int NB_TABS = 2;
        labels = new TextButton[NB_TABS];
        Stack stack = new Stack();
        contents = new Table[NB_TABS];

        labels_listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int selected_index=0;
                for(int i=0; i<NB_TABS; i++) {
                    contents[i].setVisible(labels[i].isChecked());
                    if(labels[i].isChecked())
                        selected_index=i;
                }
                stack.swapActor(stack.getChildren().get(NB_TABS-1),stack.findActor(""+selected_index));
            }
        };

        button_listener = new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int b) {
                final InputButton button = (InputButton) event.getListenerActor();
                button.setText("?");
                button.setChecked(true);
                screen.listenInputs(button);
                return true;
            }
        };

        labels[0] = new TextButton("general", Graphics.GUI.getSkin());
        labels[0].addListener(labels_listener);

        labels[1] = new TextButton("inputs", Graphics.GUI.getSkin());
        labels[1].addListener(labels_listener);

        contents[0] = new Table();
        contents[0].add(new ParameterScreenSize()).growX().row();
        CheckBox fullscreen = new CheckBox("fullscreen", GUI.getSkin());
        fullscreen.setChecked(appConfig.fullscreen);
        fullscreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                AppConfig config = AppConfig.get();
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
        String[][] id_list = inputsConfig.getIdList();
        contents[1] = new Table();
        inputsButtonGroup = new ButtonGroup<>();
        inputsButtonGroup.setMaxCheckCount(1);
        inputsButtonGroup.setMinCheckCount(1);
        contents[1].add(new Label("Action", Graphics.GUI.getSkin())).growX();
        contents[1].add(new Label("Primary", Graphics.GUI.getSkin())).growX();
        contents[1].add(new Label("Secondary", Graphics.GUI.getSkin())).growX().row();
        for(int i=0; i<Action.values().length; i++){
            Action a = Action.values()[i];
            Label label = new Label(a.toString(), Graphics.GUI.getSkin(), "very_small");
            contents[1].add(label).growX();
            for(int j=0; j<id_list[i].length; j++) {
                String id = id_list[i][j];
                InputButton textButton = new InputButton(id, a, j);
                textButton.addListener(button_listener);
                contents[1].add(textButton).growX();
                inputsButtonGroup.add(textButton);
            }
            contents[1].row();
        }

        ButtonGroup<TextButton> labelsButtonGroup = new ButtonGroup<>();
        for(int i=0; i<NB_TABS; i++) {
            this.add(labels[i]).growX();
            labelsButtonGroup.add(labels[i]);
        }
        this.row();
        for(int i=0; i<NB_TABS; i++) {
            ScrollPane actor = new ScrollPane(contents[i]);
            actor.setName(""+i);
            stack.add(actor);
        }
        this.add(stack).colspan(NB_TABS).grow().row();

        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(cancelButton).colspan(NB_TABS).growX();

        stack.swapActor(0,1);
        labels[0].setChecked(true);
        contents[1].setVisible(false);
        labelsButtonGroup.setMaxCheckCount(1);
        labelsButtonGroup.setMinCheckCount(1);
    }

    public void uncheckAllInputButtons() {
        inputsButtonGroup.uncheckAll();
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
        AppConfig config = AppConfig.get();
        public ParameterScreenSize() {
            super("screen size");
            SelectBox<Resolutions> value = new SelectBox<Resolutions>(Graphics.GUI.getSkin());
            value.setItems(Resolutions.values());
            value.setSelected(config.resolution);
            value.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Bomberball.resizeWindow(value.getSelected());
                    config.resolution = value.getSelected();
                    config.exportConfig();
                }
            });
            this.addActor(value);
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

    public class InputButton extends TextButton {
        public Action action;
        public int priority;

        public InputButton(String id, Action action, int priority) {
            super(id, Graphics.GUI.getSkin(), "input_select");
            this.action = action;
            this.priority = priority;
        }
    }
}