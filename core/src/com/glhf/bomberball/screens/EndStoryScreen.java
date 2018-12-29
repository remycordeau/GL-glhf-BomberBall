package com.glhf.bomberball.screens;

import com.badlogic.gdx.Screen;
import com.glhf.bomberball.ui.EndStoryUI;

public class EndStoryScreen extends MenuScreen {

    public EndStoryScreen(StoryMenuScreen screen, int maze_id){
        super();
        addUI(new EndStoryUI(screen,maze_id));
    }
}
