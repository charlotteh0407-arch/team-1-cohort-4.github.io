package io.github.Isabelnorton;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Fog implements Disposable {
    private final Texture texture;
    private float drift;

    public Fog(Texture texture) {
        this.texture = texture;
        texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    }

    public void draw(SpriteBatch batch, float width, float height, float deltaTime) {
        drift = (drift + 0.02f * deltaTime) % 1f;
        batch.setColor(1f, 1f, 1f, 0.35f);
        batch.draw(texture, 0f, 0f, width, height,
            drift, drift, width / texture.getWidth() + drift,
            height / texture.getHeight() + drift);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void dispose() {
        // The texture is owned by AssetManager.
    }
}