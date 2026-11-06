package io.github.Isabelnorton.GeeseSystem;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class GooseGenerator {
    public List<Goose> createGeese(int count, float mapWidth, float mapHeight, Vector2 avoid) {
        List<Goose> geese = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float x;
            float y;
            int attempts = 0;
            do {
                x = MathUtils.random(48f, mapWidth - 48f);
                y = MathUtils.random(48f, mapHeight - 48f);
                attempts++;
            } while (attempts < 20 && Vector2.dst2(x, y, avoid.x, avoid.y) < 400f * 400f);
            geese.add(new Goose(x, y));
        }
        return geese;
    }
}