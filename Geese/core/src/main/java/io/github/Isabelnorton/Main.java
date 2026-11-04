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
    private OrthographicCamera camera;
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

        //Could implement a loading screen; this will wait for everything to load before continuing for now
        assetManager.finishLoading();

        //Map setup
        map = assetManager.get("goosemap.tmx", TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        camera.position.set(1280, 1280, 0);
        camera.zoom = 0.75f;
        spriteBatch = new SpriteBatch();
        player = new Player("player.png");

        //Get map dimensions
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);

        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);

        float worldWidth = mapWidth * tileWidth;
        float worldHeight = mapHeight * tileHeight;

        //Initialise assets
        Texture fogTexture = assetManager.get("fog.png", Texture.class);
        fog = new Fog(fogTexture);

        Texture gooseTexture = assetManager.get("goose.png",Texture.class);
        numberOfGeese = 25; //change this?
        gooseManager = new GooseManager(gooseTexture, numberOfGeese, worldHeight, worldWidth);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        //This conflicts with player movement?
        /* 
        float move = speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.position.x -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.position.x += move;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.position.y += move;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.position.y -= move; */
        float halfWidth = camera.viewportWidth * 0.5f * camera.zoom;
        float halfHeight = camera.viewportHeight * 0.5f * camera.zoom;
        camera.position.x = MathUtils.clamp(camera.position.x, halfWidth, 2560 - halfWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, halfHeight, 2560 - halfHeight);
        
        player.update(dt);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        //Spritebatch
        spriteBatch.begin();
        player.draw(spriteBatch, player.getX(), player.getY());
        fog.draw(spriteBatch);
        spriteBatch.end();
        
        //Goose render
        gooseManager.update(dt);
        gooseManager.render(spriteBatch);
    }

    @Override
    public void dispose() {
        player.dispose();
        assetManager.dispose();
    }
}