package io.github.Isabelnorton.GeeseSystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Goose {
    private Sprite sprite;
    private float x;
    private float y;
    private String state;
    private float targetX;
    private float targetY;
    private float speed;
    private float radius;
    
    //Constructor
    public Goose(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        this.x = x;
        this.y = y;

        //May be passed in in future
        state = "normal";
        speed = 20f;
        radius = 10f; //alterable with difficulty if required

        generateTarget(radius);
    }

    public void update(float dt) {
        x = getX();
        y = getY();

        if (state == "Normal") {
            //Radius for random movement -> target
            float dx = targetX - x;
            float dy = targetY - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance < 1f) {
                generateTarget(radius);
            }
            else {
                x += (dx / distance) * speed * dt;
                y += (dy / distance) * speed * dt;
            }

            sprite.setPosition(x, y);
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    private void generateTarget(Float r) {
        float angle = MathUtils.random(0f, MathUtils.PI2);
        float distance = MathUtils.random(0f, r);
        targetX = x + distance * MathUtils.cos(angle);
        targetY = y + distance * MathUtils.sin(angle);
    }

    private float getX() {
        return x;
    }

    private float getY() {
        return y;
    }
}