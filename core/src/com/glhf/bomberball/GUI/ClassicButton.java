package com.glhf.bomberball.GUI;

import com.glhf.bomberball.Constants;

import java.util.function.Function;

public class ClassicButton extends Button {

    public ClassicButton(int x, int y, int width, int height, String s, boolean isShiny, Function<Void, Void> action){
        super(x, y, width, height, s, isShiny, action);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int bouton){
        if(this.contains(x, y)) {
            action.apply(null);
        }
        return false;
    }
    @Override
    public boolean mouseMoved(int x, int y) {
        y = Constants.APP_HEIGHT - y;
        this.alternativeSprite(x, y);
        return false;
    }

}
