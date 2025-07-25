package com.jodexindustries.donatecase.spigot.api.platform;

import com.jodexindustries.donatecase.api.data.casedata.gui.CaseInventory;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BukkitInventory implements CaseInventory {

    private final Inventory inventory;

    public BukkitInventory(int size, String title) {
        String safeTitle = title != null ? title : "";
        this.inventory = Bukkit.createInventory(null, size, safeTitle);
    }

    @Override
    public Inventory getHandle() {
        return inventory;
    }

    @Override
    public void setItem(int index, @Nullable Object item) {
        inventory.setItem(index, (ItemStack) item);
    }
}
