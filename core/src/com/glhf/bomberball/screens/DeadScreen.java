package com.glhf.bomberball.screens;

import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.ui.AnimationActor;
import com.glhf.bomberball.ui.DeadUI;

public class DeadScreen extends MenuScreen {

    public DeadScreen(StoryMenuScreen screen, int maze_id){
        super();
        addUI(new DeadUI(screen,maze_id));
    }
}
