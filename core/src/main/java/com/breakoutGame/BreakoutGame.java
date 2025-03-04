package com.breakoutGame;

import com.badlogic.gdx.Game;

public class BreakoutGame extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
