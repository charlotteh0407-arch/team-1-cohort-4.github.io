package io.github.Isabelnorton.items;

public interface Item {
    String getItemName();
    String getIconName();

    boolean use(ItemUser user);
}