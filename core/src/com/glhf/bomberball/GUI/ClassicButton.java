package com.glhf.bomberball.GUI;

import com.glhf.bomberball.Constants;

public class ClassicButton extends Button {

    public ClassicButton(int x, int y, int width, int height, String s, boolean isShiny){
        super(x, y, width, height, s, isShiny);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int bouton){
        //if(this.contains(x, y)) {
            action.run();
        //}
        return false;
    }
    @Override
    public boolean mouseMoved(int x, int y) {
        y = Constants.APP_HEIGHT - y;
        this.alternativeSprite(x, y);
        return false;
    }

}
