package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    
    private Texture Player_texture;

    private float spriteSpeed = 200f;
    private float xPos;
    private float yPos;
    private float playerWidth;
    private float playerHeight;

    public Player(String filePath){
        this.Player_texture = new Texture(Gdx.files.internal(filePath));
        this.xPos = 20;
        this.yPos = 20;
        this.playerWidth = 60;
        this.playerHeight = 60;
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
    }

    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(Player_texture, x, y, playerWidth, playerHeight);
    }

    // @Override 
    public void dispose(){
        Player_texture.dispose();
        
    }
}
