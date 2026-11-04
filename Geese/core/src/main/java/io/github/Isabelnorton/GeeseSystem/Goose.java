package io.github.Isabelnorton.GeeseSystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Goose {
    private Sprite sprite;

    private float x;
    private float y;
    
    //Constructor
    public Goose(Texture texture, float x, float y) {
        this.x = x;
        this.y = y;

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public void update(float dt) {
        //Goose behaviour
        //Radius for random movement
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}