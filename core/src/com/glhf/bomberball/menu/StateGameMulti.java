package com.glhf.bomberball.menu;

import com.glhf.bomberball.Config;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class StateGameMulti extends StateGame{

    private Player[] players = new Player[4];
    private int turnNumber;

    public StateGameMulti() {
        super("GameMulti");
        turnNumber=0;
    }

    private void start(){
        players = maze.spawnPlayers(1);
    }

    @Override
    public boolean keyDown(int keycode) {
        HashMap<Integer, String> inputs = Config.getInputs();
        for(int key : inputs.keySet()){
            if(key==keycode){
                try {
                    Player.class.getMethod(inputs.get(key)).invoke(players[turnNumber%Constants.NB_PLAYER_MAX]);
                } catch (IllegalAccessException e) { e.printStackTrace(); }
                  catch (InvocationTargetException e) { e.printStackTrace(); }
                  catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return false;
    }
}
