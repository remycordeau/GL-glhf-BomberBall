package com.glhf.bomberball.screens;

import com.glhf.bomberball.audio.Audio;
import com.glhf.bomberball.ui.EndLevelUI;

public class EndLevelScreen extends MenuScreen {

    public EndLevelScreen(StoryMenuScreen screen, int maze_id){
        super();
        Audio.VICTORY.play();
        addUI(new EndLevelUI(screen,maze_id));
    }
}
