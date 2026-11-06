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
        return i < 0 || i >= slots.length ? null : slots[i];
    }

    public boolean addItem(InventoryItem item, int quan){
        if (item == null || quan <= 0) return false;
        int capacity = 0;
        for (ItemStack stack : slots) {
            if (stack == null) capacity += item.getMaxStack();
            else if (stack.isSameType(item)) capacity += item.getMaxStack() - stack.getQuantity();
        }
        if (capacity < quan) return false;

        for (ItemStack s : slots ){
            if (quan == 0) return true;
            if (s != null && s.isSameType(item) && s.hasRoom()){
                int moved = Math.min(item.getMaxStack() - s.getQuantity(), quan);
                s.setQuantity(s.getQuantity() + moved);
                quan -= moved;
            }
        }
        for (int i = 0; i < slots.length && quan > 0; i++){
            if (slots[i] == null){
                int moved = Math.min(item.getMaxStack(), quan);
                slots[i] = new ItemStack(item, moved);
                quan -= moved;
            }
        }

        return true;
    }

    public void consumeItem(int slot){
        if (slot < 0 || slot >= slots.length || slots[slot] == null) return;
        int remaining = slots[slot].getQuantity() - 1;
        if (remaining == 0) slots[slot] = null;
        else slots[slot].setQuantity(remaining);
    }

    //Check if item already exists in the inventory
    public boolean hasItem(Class <? extends InventoryItem> type){
        for (ItemStack s: slots){
            if (s != null  && type.isInstance(s.getItem())) return true;
        }
        return false;
    }
}
