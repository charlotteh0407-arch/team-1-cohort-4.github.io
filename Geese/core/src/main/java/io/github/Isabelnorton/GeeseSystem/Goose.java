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

    private float waitTimer;
    private float waitDuration;
    private float waitMax;
    
    //Constructor
    public Goose(Texture texture, float scale, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setScale(scale);
        this.x = x;
        this.y = y;

        //May be passed from Main in the future
        state = "normal";
        speed = 20f;
        radius = 250f; //alterable with difficulty if required
        waitMax = 3f;

        generateTarget(radius);
    }

    public void update(float dt) {
        x = getX();
        y = getY();

        if (state.equals("normal")) {
            //Radius for random movement -> target
            float dx = targetX - x;
            float dy = targetY - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance < 1f) {
                state = "waiting";
                waitDuration = MathUtils.random(0, waitMax);
                waitTimer = 0f;
            }
            else {
                x += (dx / distance) * speed * dt;
                y += (dy / distance) * speed * dt;
            }

            sprite.setPosition(x, y);
        }
        else if (state.equals("waiting")) {
            waitTimer += dt;

            if (waitTimer >= waitDuration) {
                generateTarget(radius);
                state = "normal";
            }
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