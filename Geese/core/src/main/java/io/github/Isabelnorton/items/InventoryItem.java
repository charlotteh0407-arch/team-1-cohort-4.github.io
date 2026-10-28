package io.github.Isabelnorton.items;

//extends Item as only the inventory items need a max stack size
public interface InventoryItem extends Item{
    int getMaxStack();
}