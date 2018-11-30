package com.glhf.bomberball.GUI;

import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.StateMainMenu;

public class ButtonUndo extends Button {
    //Attribute
    State state;

    public ButtonUndo(int x, int y, int width, int height){
        super(x, y, width, height, "Retour");
        State mstate = new StateMainMenu("main");
        this.state = mstate;
    }

    public State getState(){
        return state;
    }


}
