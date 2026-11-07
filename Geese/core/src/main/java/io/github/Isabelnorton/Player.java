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
    private Vector2 pos = new Vector2();
    private Vector2 direction = new Vector2(0,0);
    private Vector2 move = new Vector2();

    @Override
    public Vector2 getPos(){
        return pos;
    };
    @Override
    public Vector2 getDirection(){
        return direction;
    }

    public Player(Texture texture){
        playerSprite = new Sprite(texture);
        pos.set(GameConfig.player.StartPos.x, GameConfig.player.StartPos.y);
        playerSprite.setSize(GameConfig.player.Size.width,GameConfig.player.Size.height);
        playerSprite.setPosition(pos.x, pos.y);
    }

    public void update(float deltaTime){
        move.setZero();
        //normalised directional input so now if 2 keys are pressed player still moves at base speed
        //also allows me to return direction smile
        if (Gdx.input.isKeyPressed(Input.Keys.A)) move.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) move.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) move.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) move.y -=1;
        //if a key is being pressed, update the
        if (move.len2()>0) {
            move.nor();
            direction.set(move);
            pos.mulAdd(move, spriteSpeed*deltaTime);
        }
        playerSprite.setPosition(pos.x, pos.y);
    }

     public void draw(SpriteBatch batch) {
        playerSprite.draw(batch);
    }
}