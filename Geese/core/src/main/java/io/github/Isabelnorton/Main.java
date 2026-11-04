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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;

import io.github.Isabelnorton.GeeseSystem.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private static final float speed = 200f;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera worldCamera;
    private OrthographicCamera screenCamera;
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

        //Get map dimensions
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);

        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);

        float worldWidth = mapWidth * tileWidth;
        float worldHeight = mapHeight * tileHeight;

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

        float move = speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) worldCamera.position.x -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) worldCamera.position.x += move;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) worldCamera.position.y += move;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) worldCamera.position.y -= move;
        float halfWidth = worldCamera.viewportWidth * 0.5f * worldCamera.zoom;
        float halfHeight = worldCamera.viewportHeight * 0.5f * worldCamera.zoom;
        worldCamera.position.x = MathUtils.clamp(worldCamera.position.x, halfWidth, 2560 - halfWidth);
        worldCamera.position.y = MathUtils.clamp(worldCamera.position.y, halfHeight, 2560 - halfHeight);

        gooseManager.update(dt);
        
        player.update(dt);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        worldCamera.update();
        renderer.setView(worldCamera);
        renderer.render();

        //Draw map objects
        spriteBatch.setProjectionMatrix(worldCamera.combined);
        spriteBatch.begin();
        player.draw(spriteBatch);
        gooseManager.render(spriteBatch);
        spriteBatch.end();

        //Draw fog relative to screen
        spriteBatch.setProjectionMatrix(screenCamera.combined);
        spriteBatch.begin();
        //fog.draw(spriteBatch); FOG RENDERING RENABLE IF NEEDED
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        spriteBatch.dispose();
    }
}