package com.glhf.bomberball.menu;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.GUI.ButtonUndo;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

public class StateSettingsMenu extends State {
    //Attributes
    private Button tmp;
    private ButtonUndo undo;

    //Constructor
    public StateSettingsMenu(String name) {
        super(name);
        this.settings();
    }

    /*Loading textures*/
    public void settings() {
        tmp = new Button(60, 0, 640, 451, "ComingSoon", false);
        State s = new StateMainMenu("MainMenu");
        undo = new ButtonUndo(0, 0, 100, 100, s);
    }

    public void draw() {
        tmp.draw(batch);
        undo.draw(batch);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Constants.APP_HEIGHT - y;
        if(undo.contains(x, y)){
            Game.setState(undo.getState());
        }
        return false;
    }
}