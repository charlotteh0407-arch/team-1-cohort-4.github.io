package io.github.Isabelnorton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final float speed = 200f;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        map = new TmxMapLoader().load("goosemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        camera.position.set(1280, 1280, 0);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        float move = speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))  camera.position.x -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += move;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))    camera.position.y += move;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))  camera.position.y -= move;
        float halfWidth = camera.viewportWidth * 0.5f * camera.zoom;
        float halfHeight = camera.viewportHeight * 0.5f * camera.zoom;
        camera.position.x = MathUtils.clamp(camera.position.x, halfWidth, 2560 - halfWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, halfHeight, 2560 - halfHeight);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        
    }

    @Override
    public void dispose() {
        renderer.dispose();
        map.dispose();
    }

}
