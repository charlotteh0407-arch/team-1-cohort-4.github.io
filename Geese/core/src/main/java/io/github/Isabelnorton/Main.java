package io.github.Isabelnorton;

import io.github.Isabelnorton.items.ItemConfig;
// import io.github.Isabelnorton.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final float speed = 200f;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Fog fog;
    private Player player;
    private SpriteBatch spriteBatch;

    @Override
    public void create() {
        ItemConfig.load();
        map = new TmxMapLoader().load("goosemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        camera.position.set(1280, 1280, 0);
        camera.zoom = 0.75f;
        spriteBatch = new SpriteBatch();
        fog = new Fog();
        player = new Player("player.png");
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        float move = speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.position.x -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.position.x += move;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.position.y += move;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.position.y -= move;
        float halfWidth = camera.viewportWidth * 0.5f * camera.zoom;
        float halfHeight = camera.viewportHeight * 0.5f * camera.zoom;
        camera.position.x = MathUtils.clamp(camera.position.x, halfWidth, 2560 - halfWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, halfHeight, 2560 - halfHeight);

        player.update(dt);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        spriteBatch.begin();
        player.draw(spriteBatch, player.getX(), player.getY());
        fog.draw(spriteBatch);
        spriteBatch.end();
        
    }

    @Override
    public void dispose() {
        renderer.dispose();
        map.dispose();
        fog.dispose();
        spriteBatch.dispose();
    }

}
