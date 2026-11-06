package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Fog {

    private final Texture texture;
    private float drift;

    public Fog(Texture texture) {
        this.texture = texture;
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
}