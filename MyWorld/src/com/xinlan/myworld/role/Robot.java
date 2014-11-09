package com.xinlan.myworld.role;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xinlan.myworld.screen.GameScreen;

public class Robot
{
    private GameScreen mContext;
    
    private Animation animation;
    private Texture texture;
    private float timeDelta;
    private float width = 32;
    private float height = 32;
    public Vector2 pos = new Vector2();
    
    public Robot(GameScreen mContext)
    {
        this.mContext = mContext;
        texture = new Texture("asset/robot.png");
        TextureRegion[] manRegions = TextureRegion.split(texture, 32, 32)[0];
        animation = new Animation(0.15f, manRegions[0], manRegions[1]);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void update(float delta)
    {
        timeDelta += delta;
    }

    public void render(float delta, Batch batch)
    {
        TextureRegion textureRegion = animation.getKeyFrame(timeDelta);
        batch.begin();
        batch.draw(textureRegion, 0, 96, width, height);
        batch.end();
    }

    public boolean dispose()
    {
        if (texture != null)
        {
            texture.dispose();
        }
        return false;
    }
}// end class
