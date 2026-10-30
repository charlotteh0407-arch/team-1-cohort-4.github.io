package io.github.Isabelnorton.items;

import com.badlogic.gdx.math.Vector2;

//not entirely necessary to break it down this far (as the player is the only thing using items)
//but more modular = good
public interface ItemUser {
    Vector2 getPos();
    Vector2 getDirection();
}