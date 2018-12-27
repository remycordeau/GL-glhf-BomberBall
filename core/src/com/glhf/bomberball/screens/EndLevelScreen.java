package com.glhf.bomberball.screens;

import com.glhf.bomberball.ui.EndLevelUI;

public class EndLevelScreen extends MenuScreen {

    public EndLevelScreen(StoryMenuScreen screen, int maze_id){
        super();
        addUI(new EndLevelUI(screen,maze_id));
    }
}
