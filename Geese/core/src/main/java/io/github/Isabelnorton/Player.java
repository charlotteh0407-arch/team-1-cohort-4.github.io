package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    
    private Sprite playerSprite;

    private float spriteSpeed = 200f;
    private float xPos;
    private float yPos;

    public Player(Texture texture){
        playerSprite = new Sprite(texture);

        xPos = 1280;
        yPos = 1280;

        playerSprite.setSize(60,60);
        playerSprite.setPosition(xPos, yPos);
    }

    public float getX() {
        return xPos;
    }   

    public float getY() {
        return yPos;
    }

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) xPos -= spriteSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) xPos += spriteSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) yPos += spriteSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) yPos -= spriteSpeed * deltaTime;

        playerSprite.setPosition(xPos, yPos);
    }

     public void draw(SpriteBatch batch) {
        playerSprite.draw(batch);
    }
}