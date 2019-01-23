package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.ui.StoryTellingUI;

public class StoryTellingScreen extends AbstractScreen {
    public final int nb_chapters = 4;
    public int chapter;
    private StoryTellingUI ui;

    public StoryTellingScreen(){
        ui = new StoryTellingUI(this);
        addUI(ui);
        chapter=1;
    }

    @Override
    protected void registerActionsHandlers() {
        super.registerActionsHandlers();
        //input_handler.registerActionHandler(Action.NEXT_SCREEN, this::continueStory);
    }

    public void continueStory() {
        if(chapter<nb_chapters){
            chapter++;
            ui.continueStory();
        }else {
            Bomberball.changeScreen(new StoryMenuScreen());
        }
    }
}
