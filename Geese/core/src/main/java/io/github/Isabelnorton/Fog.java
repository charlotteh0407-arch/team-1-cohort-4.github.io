package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Fog implements Disposable {

    // private final SpriteBatch batch = new SpriteBatch();
    private final Texture texture = new Texture(Gdx.files.internal("fog.png"));
    //private boolean active = false;
    private float drift;

    public Fog() {
        texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    }

    public void draw(SpriteBatch batch) {
        drift += 0.02f * Gdx.graphics.getDeltaTime();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch.draw(texture, 0f, 0f, w, h,
                drift,
                drift,
                w / texture.getWidth() + drift,
                h / texture.getHeight() + drift);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}