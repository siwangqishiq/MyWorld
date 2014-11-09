package com.xinlan.myworld.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.xinlan.myworld.MyWorld;

public class GameScreen implements Screen
{
    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 320;

    public static final int WORLD_WIDTH = 9600;
    public static final int WORLD_HEIGHT = 320;

    public static final int CAMREA_SPEED = 10;

    private float cameraLeftSide = SCREEN_WIDTH / 2;
    private float cameraRightSide = WORLD_WIDTH - SCREEN_WIDTH / 2;

    protected MyWorld mContext;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRender;// 地图渲染器
    private TiledMap tiledMap;// 瓦片地图

    public GameScreen(MyWorld mMyWorld)
    {
        mContext = mMyWorld;
    }

    @Override
    public void show()
    {
        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        camera.update();

        tiledMap = new TmxMapLoader().load("asset/map/map.tmx");
        mapRender = new OrthogonalTiledMapRenderer(tiledMap, 1f);
    }

    @Override
    public void render(float deltaTime)
    {
        // System.out.println(deltaTime);
        Gdx.gl.glClearColor(0.45f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();

        // 地图渲染
        mapRender.setView(camera);
        mapRender.render();
    }

    /**
     * 镜头更新 保证镜头不移出屏幕
     */
    private void updateCamera()
    {
        int cameraDx = 0;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
        {
            cameraDx -= CAMREA_SPEED;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
        {
            cameraDx += CAMREA_SPEED;
        }

        float will = camera.position.x + cameraDx;
        if (will <= cameraLeftSide)
        {
            camera.position.x = cameraLeftSide;
        }
        else if (will >= cameraRightSide)
        {
            camera.position.x = cameraRightSide;
        }
        else
        {
            camera.position.x = will;
        }
        camera.update();
    }

    @Override
    public void dispose()
    {
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void resume()
    {

    }
}// end class
