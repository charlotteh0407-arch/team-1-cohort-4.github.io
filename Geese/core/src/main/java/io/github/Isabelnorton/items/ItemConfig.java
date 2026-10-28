package io.github.Isabelnorton.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

//Loads values from items.json, allows us to easily change variables for difficulty
public class ItemConfig {
    private static final String configFile = "items.json";
    private static boolean loaded = false;

    public static float airhornCooldown;
    public static float airhornRange;
    public static float airhornAngle;
    public static float seedPacketRange;
    public static float seedPacketRadius;
    public static int seedPacketMaxStack;
    public static float costumeDuration;
    public static float freezeDuration;

    public static void load(){
        if (loaded) return;

        JsonValue root = new JsonReader().parse(Gdx.files.internal(configFile));

        JsonValue airhorn = root.get("Airhorn");

        airhornCooldown = airhorn.getFloat("cooldownSeconds", airhornCooldown);
        airhornRange = airhorn.getFloat("range", airhornRange);
        airhornAngle = airhorn.getFloat("angle", airhornAngle);

        JsonValue seedPacket = root.get("SeedPacket");
        seedPacketRange = seedPacket.getFloat("range", seedPacketRange);
        seedPacketRadius = seedPacket.getFloat("radius", seedPacketRadius);
        seedPacketMaxStack = seedPacket.getInt("maxStack", seedPacketMaxStack);

        JsonValue costume = root.get("costume");
        costumeDuration = costume.getFloat("durationSeconds", costumeDuration);

        JsonValue freeze = root.get("freeze");
        freezeDuration = freeze.getFloat("durationSeconds", freezeDuration);
        loaded = true;
        System.out.println("ItemConfig loaded successfully");
    }
}