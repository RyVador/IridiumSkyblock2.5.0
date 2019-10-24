package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class GUI {

    private Inventory inventory;
    public int islandID;
    public int scheduler;

    public GUI(Island island, int size, String name) {
        islandID = island.getId();
        this.inventory = Bukkit.createInventory(null, size, Utils.color(name));
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), this::addContent, 0, 5);
    }

    public GUI(int size, String name) {
        this.inventory = Bukkit.createInventory(null, size, Utils.color(name));
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), this::addContent, 0, 5);
    }

    public void addContent() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
        }
    }

    public abstract void onInventoryClick(InventoryClickEvent e);

    public Inventory getInventory() {
        return inventory;
    }

    public Island getIsland() {
        return IridiumSkyblock.getIslandManager().getIslandViaId(islandID);
    }
}