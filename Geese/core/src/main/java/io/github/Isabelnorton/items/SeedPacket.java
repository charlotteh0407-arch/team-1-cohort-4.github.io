package io.github.Isabelnorton.items;

import com.badlogic.gdx.Game;
import io.github.Isabelnorton.GameConfig;

public class SeedPacket implements InventoryItem {
    private final float range;
    private final float radius;
    private final int maxStack;

    public SeedPacket() {
        range = GameConfig.item.SeedPacket.range;
        radius = GameConfig.item.SeedPacket.radius;
        maxStack = GameConfig.item.SeedPacket.maxStack;
    }

    @Override
    public String getItemName() {
        return "Seed Packet";
    }

    @Override
    public String getIconName() {
        return "seedpacket";
    }

    @Override
    public int getMaxStack() {
        return maxStack;
    }

    @Override
    public boolean use(ItemUser user) {
        return true;
    }

    public float getRange() {
        return range;
    }

    public float getRadius() {
        return radius;
    }

}
