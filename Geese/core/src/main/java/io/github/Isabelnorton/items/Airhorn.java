package io.github.Isabelnorton.items;
import com.badlogic.gdx.utils.TimeUtils;


//Airhorn functions on a cooldown - blasts a cone area in front of the player to deter gooses
public class Airhorn implements InventoryItem{
    private final long cooldownMillis;
    private final float rangeSquared;
    private final float angle;
    private long lastUsedTime;


    public Airhorn(){
        //converts cooldown in seconds to milliseconds, calculates as a long (airhorn cooldown is a float), this because Time.Utils.millis() returns a long
        cooldownMillis = (long) (ItemConfig.airhornCooldown * 1000);

        lastUsedTime = TimeUtils.millis() - cooldownMillis;
        //calculates sqaure of range for easier hit detection
        float range = ItemConfig.airhornRange;
        rangeSquared = range*range;
        angle = ItemConfig.airhornAngle;
    }

    @Override
    public String getItemName(){
        return "Airhorn";
    }

    @Override
    public String getIconName() {
        return "airhorn";
    }

    @Override
    public int getMaxStack() {
        return 1;
    }

    @Override
    public boolean use(ItemUser user){
        long currentTime = TimeUtils.millis();
        //if airhorn has not been used it is not on cooldown
        if(currentTime - lastUsedTime >= cooldownMillis){
            lastUsedTime = currentTime;
            return true;
        }
        return false;

    }

}
//if within cooldown, trigger blast in front of player else return false

//store cooldown, blast range and angle in a config
