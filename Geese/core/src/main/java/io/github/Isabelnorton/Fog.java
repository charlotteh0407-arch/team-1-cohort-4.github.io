package io.github.Isabelnorton;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Fog implements Disposable {

    private final SpriteBatch batch = new SpriteBatch();
    private final Texture texture = new Texture(Gdx.files.internal("fog.png"));

    public void draw() {
        batch.begin();
        batch.draw(texture, 0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}