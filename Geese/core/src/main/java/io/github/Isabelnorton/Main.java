package io.github.Isabelnorton;

import io.github.Isabelnorton.items.ItemConfig;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;

import io.github.Isabelnorton.GeeseSystem.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera worldCamera;
    private OrthographicCamera screenCamera;

    private float worldWidth;
    private float worldHeight;

    private AssetManager assetManager;
    private Fog fog;
    private Player player;
    private SpriteBatch spriteBatch;

    private GooseManager gooseManager;
    private Integer numberOfGeese;


    @Override
    public void create() {
        assetManager = new AssetManager();
        ItemConfig.load();

        //Setup the map loader
        assetManager.setLoader(
            TiledMap.class,
            new TmxMapLoader()
        );

        //Load assets
        assetManager.load("goosemap.tmx", TiledMap.class);
        assetManager.load("fog.png", Texture.class);
        assetManager.load("goose.png",Texture.class);
        assetManager.load("player.png",Texture.class);

        //Could implement a loading screen; this will wait for everything to load before continuing for now
        assetManager.finishLoading();
        spriteBatch = new SpriteBatch();

        //Map setup
        map = assetManager.get("goosemap.tmx", TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(map);

        //worldCamera (follows the game world and is used for the map, player and geese)
        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(false, 800, 600);
        worldCamera.position.set(1280, 1280, 0);
        worldCamera.zoom = 0.75f;

        //screenCamera (stays fixed to the screen and is used for UI and screen-covering effects like fog.)
        screenCamera = new OrthographicCamera();
        screenCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Get world dimensions (actual dimensions of the simulated world)
        float mapWidth = map.getProperties().get("width", Integer.class);
        float mapHeight = map.getProperties().get("height", Integer.class);

        float tileWidth = map.getProperties().get("tilewidth", Integer.class);
        float tileHeight = map.getProperties().get("tileheight", Integer.class);

        worldWidth = mapWidth * tileWidth;
        worldHeight = mapHeight * tileHeight;

        //Initialise assets
        Texture playerTexture = assetManager.get("player.png", Texture.class);
        player = new Player(playerTexture);

        Texture fogTexture = assetManager.get("fog.png", Texture.class);
        fog = new Fog(fogTexture);

        Texture gooseTexture = assetManager.get("goose.png",Texture.class);
        numberOfGeese = 25; //change this?
        gooseManager = new GooseManager(gooseTexture, numberOfGeese, worldHeight, worldWidth);
    }


    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        // Update game objects
        gooseManager.update(dt);
        player.update(dt);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Centre camera on player
        worldCamera.position.set(
            player.getX() + 30,
            player.getY() + 30,
            0
        );

        // Keep camera within map boundaries
        float halfWidth = worldCamera.viewportWidth * 0.5f * worldCamera.zoom;
        float halfHeight = worldCamera.viewportHeight * 0.5f * worldCamera.zoom;

        worldCamera.position.x = MathUtils.clamp(
            worldCamera.position.x, halfWidth, worldWidth - halfWidth
        );

        worldCamera.position.y = MathUtils.clamp(
            worldCamera.position.y, halfHeight, worldHeight - halfHeight
        );

        // Update camera and render map
        worldCamera.update();
        renderer.setView(worldCamera);
        renderer.render();

        // Draw world objects
        spriteBatch.setProjectionMatrix(worldCamera.combined);
        spriteBatch.begin();
        player.draw(spriteBatch);
        gooseManager.render(spriteBatch);
        spriteBatch.end();

        // Draw screen-space objects
        spriteBatch.setProjectionMatrix(screenCamera.combined);
        spriteBatch.begin();
        // fog.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        spriteBatch.dispose();
    }
}