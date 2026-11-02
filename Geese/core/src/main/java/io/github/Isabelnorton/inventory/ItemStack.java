package io.github.Isabelnorton.inventory;
import io.github.Isabelnorton.items.InventoryItem;

import java.util.Objects;

public class ItemStack{
    private final InventoryItem item;
    private int quant;
    public ItemStack(InventoryItem item, int quant){
        this.item = item;
        this.quant = quant;
    }
    public InventoryItem getItem(){
        return item;
    }
    public int getQuantity(){
        return quant;
    }

    public boolean setQuantity(int newQuant){
        if (newQuant > item.getMaxStack()||newQuant<0){
            return false;
        }
        quant = newQuant;
        return true;
    }
    public boolean hasRoom(){
        return quant < item.getMaxStack();
    }
    public boolean isSameType(InventoryItem toAdd){
        return Objects.equals(item.getClass(), toAdd.getClass());
    }
}