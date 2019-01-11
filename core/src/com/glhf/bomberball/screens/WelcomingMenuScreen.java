package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.ui.WelcomingMenuUI;

public class WelcomingMenuScreen extends MenuScreen {

    public WelcomingMenuScreen(){
        super();
        addUI(new WelcomingMenuUI());
    }

    @Override
    protected void registerActionsHandlers() {
        super.registerActionsHandlers();
        input_handler.registerActionHandler(Action.NEXT_SCREEN, ()-> Bomberball.changeScreen(new MainMenuScreen()));
    }
}
