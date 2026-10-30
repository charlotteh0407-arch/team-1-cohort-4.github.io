package io.github.Isabelnorton.inventory;

import io.github.Isabelnorton.items.InventoryItem;

public class Inventory {

    private final ItemStack[] slots;


    public Inventory(int size){
        slots = new ItemStack[size];
    }

    public int size(){
        return slots.length;
    }
    
    public ItemStack getSlot(int i){
        return slots[i];
    }

    //Used in the pickup system to add items picked from the map to the invntory
    public boolean addItem(InventoryItem item, int quan){
        for (ItemStack s : slots ){
            if (quan == 0) break;
            if (s != null && s.isSameType(item) && s.hasRoom()){
                int moved = Math.min(item.getMaxStack() - s.getQuantity(), quan);
                s.setQuantity(s.getQuantity() + moved);
                quan -=moved;
            }
        }
        for (int i = 0; i < slots.length && quan > 0; i++){
            if (slots[i] == null){
                int moved = Math.min(item.getMaxStack(), quan);
                slots[i] = new ItemStack(item, moved);
                quan -= moved;
            }
        }

        return quan == 0;
    }

    //Remove item from inventory when item is used
    public void removeItem(int slot){
        slots[slot] = null;
    }

    //Check if item already exists in the inventory
    public boolean hasItem(Class <? extends InventoryItem> type){
        for (ItemStack s: slots){
            if (s != null  && type.isInstance(s.getItem())) return true;
        }
        return false;
    }
}
