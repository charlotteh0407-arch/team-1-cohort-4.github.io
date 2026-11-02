package io.github.Isabelnorton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class GameConfig{
    public static boolean loaded = false;
    public static CameraConfig camera = new CameraConfig();
    public static PlayerConfig player = new PlayerConfig();
    public static ItemsConfig item = new ItemsConfig();

    public static class CameraConfig{
        public SizeConfig Viewport;
        public SizeConfig MapBounds;
        public float defaultZoom;
    }
    public static class PlayerConfig{
        public float speed;
        public PositionConfig StartPos;
        public SizeConfig Size;
    }

    //nested the sub-item config classes to avoid them popping up in autocorrect
    public static class ItemsConfig{
        public AirhornConfig Airhorn;
        public SeedPacketConfig SeedPacket;
        public FreezeConfig Freeze;
        public CostumeConfig Costume;

        public static class SeedPacketConfig{
            public float range;
            public float radius;
            public int maxStack;
        }
        public static class AirhornConfig{
            public float cooldownSeconds;
            public float range;
            public float angle;
        }
        public static class FreezeConfig{
            public float durationSeconds;
        }
        public static class CostumeConfig{
            public float durationSeconds;
        }
    }

    //reusable for future positions and sizes, just make sure to name them correctly in the json
    public static class SizeConfig{
        public float height;
        public float width;
        //requires an empty constructor for the deserialisation
        public SizeConfig(){}
        public SizeConfig(float h, float w){
            this.height = h;
            this.width = w;
        }
    }
    public static class PositionConfig{
        public float x;
        public float y;
        public PositionConfig(){};
        public PositionConfig(float x, float y){
            this.x = x;
            this.y = y;
        }
    }


    //IMPORTANT: field names must match the tags in game-config.json exactly (deserialisation is case-sensitive)
    private static class DataCatch{
        public CameraConfig Camera;
        public PlayerConfig Player;
        public ItemsConfig Items;
    }
    public static void load(){
        if (loaded) return;
        Json configJson = new Json();
        //if extra stuff is added to the json, it won't crash
        configJson.setIgnoreUnknownFields(true);
        DataCatch dump = configJson.fromJson(DataCatch.class, Gdx.files.internal("game-config.json"));
        if (dump != null) {
            if (dump.Camera != null) {
                camera = dump.Camera;
            }
            if (dump.Player != null) {
                player = dump.Player;
            }
            if (dump.Items != null) {
                item = dump.Items;
            }
        }
        loaded = true;
    }
}
