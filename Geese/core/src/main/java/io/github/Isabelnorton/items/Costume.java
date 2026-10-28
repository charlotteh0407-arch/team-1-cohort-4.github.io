package io.github.Isabelnorton.items;

public class Costume implements Item{
    private final long durationMillis;

    public Costume(){
        durationMillis = (long) (ItemConfig.costumeDuration*1000);
    }

    @Override
    public String getItemName(){
        return "Long Boi Costume";
    }
    @Override
    public String getIconName(){
        return "costume";
    }
    @Override
    public boolean use(ItemUser user){
        return true;
    }
}
