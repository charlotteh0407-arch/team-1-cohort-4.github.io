package io.github.Isabelnorton.GeeseSystem;

//import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class GooseManager {
    private Array<Goose> geese;
    private float gooseHeight;
    private float gooseWidth;

    //Constructor
    public GooseManager(Texture texture, Integer numberOfGeese, Float mapHeight, Float mapWidth) {
        geese = new Array<>();
        gooseHeight = texture.getHeight();
        gooseWidth = texture.getWidth();

        //Create geese
        for (Integer count = 0; count<numberOfGeese; count++) {
            float x = MathUtils.random(mapWidth - gooseWidth);
            float y = MathUtils.random(mapHeight - gooseHeight);
            geese.add(new Goose(texture,x,y));
        }
    }

    public void update(float dt) {
        for (Goose goose : geese) {
            goose.update(dt);
        }
    }

    public void render(SpriteBatch batch) {
        for (Goose goose : geese) {
            goose.render(batch);
        }
    }
}