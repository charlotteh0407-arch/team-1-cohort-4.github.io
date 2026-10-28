package io.github.Isabelnorton.items;

public class Freeze implements Item{

    private final long durationMillis;

    public Freeze(){
        durationMillis = (long) (ItemConfig.freezeDuration*1000);
    }

    @Override
    public String getItemName(){
        return "Freeze";
    }
    @Override
    public String getIconName(){
        return "freeze";
    }
    @Override
    public boolean use(ItemUser user){
        return true;
    }
}
