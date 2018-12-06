package com.glhf.bomberball.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


public class InputHandler extends InputListener {
    public enum Events{
        KEY_UP,KEY_DOWN,KEY_LEFT,KEY_RIGHT,KEY_SPACE,MOUSE_LEFT,MOUSE_RIGHT
    }

    private int screenX;
    private int screenY;

    private Runnable[] runnables = new Runnable[Events.values().length];

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.err.println("Click ! --> Gestion des inputs à faire"); //TODO Gestion des inputs à faire
        setScreenX((int) x);
        setScreenY((int) y);
        runnables[Events.MOUSE_LEFT.ordinal()].run();
        runnables[Events.MOUSE_RIGHT.ordinal()].run();

        return false; //super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                runnables[Events.KEY_UP.ordinal()].run();
                break;
            case Input.Keys.RIGHT:
                runnables[Events.KEY_RIGHT.ordinal()].run();
                break;
            case Input.Keys.DOWN:
                runnables[Events.KEY_DOWN.ordinal()].run();
                break;
            case Input.Keys.LEFT:
                runnables[Events.KEY_LEFT.ordinal()].run();
                break;
            case Input.Keys.SPACE:
                runnables[Events.KEY_SPACE.ordinal()].run();
                break;
        }

        return false;
    }

    //inputHandler.register(() -> {moveCurrentPlayer(Direction.UP)})
    public void register(Events e, Runnable r){
        runnables[e.ordinal()] = r;
    }
    public void registerMouse(Events e, int x, int y, Runnable r){
        runnables[e.ordinal()] = r;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenX(int x){
        this.screenX = x;
    }

    public void setScreenY(int y){
        this.screenX = y;
    }
}
