package io.github.Isabelnorton.items;

public interface Item {
    String getItemName();
    boolean use(ItemUser user);
}