package com.xinlan.myworld;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = false;
        config.width=1280;
        config.height =640;
        config.resizable = false;
        new LwjglApplication(new MyWorld(),config);
    }
}// end class
