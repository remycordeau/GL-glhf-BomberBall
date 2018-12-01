package com.glhf.bomberball.GUI;

import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.StateMainMenu;

public class ButtonUndo extends Button {
    //Attribute
    private State state;

    public ButtonUndo(int x, int y, int width, int height, State state){
        super(x, y, width, height, "Retour", false);
        this.state = state;
    }

    public State getState(){
        return state;
    }


}
