package com.xinlan.myworld.role;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xinlan.myworld.screen.GameScreen;

public class Robot
{
    private final float AnimationDuring = 0.2f;
    private GameScreen mContext;

    private final int DIR_LEFT = 1;
    private final int DIR_RIGHT = 2;

    private Animation animationRight;
    private Animation animationLeft;
    private Texture texture;
    private Texture waitTexture;
    private float timeDelta;
    private float width = 32;
    private float height = 32;
    public Vector2 pos = new Vector2();
    public Vector2 velocity = new Vector2();
    public int dir;
    private TextureRegion waitRegionLeft, waitRegionRight;
    public int state;

    public Robot(GameScreen mContext)
    {
        this.mContext = mContext;
        texture = new Texture("asset/robot.png");
        TextureRegion[] manRegions = TextureRegion.split(texture, 32, 32)[0];
        // manRegions[0].flip(true, false);
        animationRight = new Animation(AnimationDuring, manRegions[0],
                manRegions[1], manRegions[2], manRegions[3]);
        animationRight.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] manRegionsLeft = TextureRegion.split(texture, 32, 32)[0];
        manRegionsLeft[0].flip(true, false);
        manRegionsLeft[1].flip(true, false);
        manRegionsLeft[2].flip(true, false);
        manRegionsLeft[3].flip(true, false);
        animationLeft = new Animation(AnimationDuring, manRegionsLeft[0],
                manRegionsLeft[1], manRegionsLeft[2], manRegionsLeft[3]);
        animationLeft.setPlayMode(Animation.PlayMode.LOOP);

        dir = DIR_RIGHT;

        pos.x = 100;
        pos.y = 96;
    }

    public void update(float delta)
    {
        timeDelta += delta;
        velocity.x = 0;
        if (Gdx.input.isKeyPressed(Keys.LEFT) || isTouched(0, 0.25f))
        {
            dir = DIR_LEFT;
            velocity.x = -3;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || isTouched(0.25f, 0.5f))
        {
            dir = DIR_RIGHT;
            velocity.x = 3;
        }

        if (Gdx.input.isKeyPressed(Keys.X))
        {
            dir = DIR_RIGHT;
            velocity.x += 10;
        }

        pos.add(velocity);

        if (pos.x < 0)
        {
            pos.x = 0;
        }
        else if (pos.x > GameScreen.WORLD_WIDTH - width)
        {
            pos.x = GameScreen.WORLD_WIDTH - width;
        }
    }

    private boolean isTouched(float startX, float endX)
    {
        for (int i = 0; i < 2; i++)
        {
            float x = Gdx.input.getX() / (float) Gdx.graphics.getWidth();
            if (Gdx.input.isTouched(i) && (x >= startX && x <= endX))
            {
                return true;
            }
        }
        return false;
    }

    public void render(float delta, Batch batch)
    {
        Animation showAnimation = dir == DIR_LEFT ? animationLeft
                : animationRight;
        TextureRegion textureRegion = showAnimation.getKeyFrame(timeDelta);

        batch.begin();
        batch.draw(textureRegion, pos.x, pos.y, width, height);
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
