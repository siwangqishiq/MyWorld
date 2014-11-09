package com.xinlan.myworld;

import com.badlogic.gdx.Game;
import com.xinlan.myworld.screen.GameScreen;

public class MyWorld extends Game
{
    @Override
    public void create()
    {
        setScreen(new GameScreen(this));
    }
}//end class
