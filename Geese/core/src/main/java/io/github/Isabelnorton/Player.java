package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.Isabelnorton.items.ItemUser;

public class Player implements ItemUser{
    
    private Sprite playerSprite;

    private float spriteSpeed = GameConfig.player.speed;
    private float xPos;
    private float yPos;

    public Player(Texture texture){
        playerSprite = new Sprite(texture);
    private Vector2 direction = new Vector2(0,0);

    @Override
    public Vector2 getPos(){
        return new Vector2(xPos,yPos);
    };
    @Override
    public Vector2 getDirection(){
        return direction;
    }

        xPos = GameConfig.player.StartPos.x;
        yPos = GameConfig.player.StartPos.y;

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