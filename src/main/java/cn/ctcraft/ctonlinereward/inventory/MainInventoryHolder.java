package cn.ctcraft.ctonlinereward.inventory;

import cn.ctcraft.ctonlinereward.RewardEntity;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class MainInventoryHolder implements InventoryHolder {
    public Map<Integer, String> modeMap = new HashMap<>();
    public Map<Integer, RewardEntity> statusMap = new HashMap<>();
    public Map<Integer, ConfigurationSection> commandMap = new HashMap<>();
    public Map<Integer, String> guiMap = new HashMap<>();
    public String inventoryID;

    @Override
    public Inventory getInventory() {
        return null;
    }
}
