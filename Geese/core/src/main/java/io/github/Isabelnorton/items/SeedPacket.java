package io.github.Isabelnorton.items;

public class SeedPacket implements InventoryItem {
    private final float range;
    private final float radius;
    private final int maxStack;

    public SeedPacket() {
        range = ItemConfig.seedPacketRange;
        radius = ItemConfig.seedPacketRadius;
        maxStack = ItemConfig.seedPacketMaxStack;
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
