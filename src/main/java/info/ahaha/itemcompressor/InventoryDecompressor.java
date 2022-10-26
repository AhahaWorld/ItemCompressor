package info.ahaha.itemcompressor;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

public class InventoryDecompressor implements Serializable {

    private ItemStack[] items;
    private InventoryType type;
    private int slot;


    public InventoryDecompressor(InventoryType type, int slot, byte[]... data) throws IOException, ClassNotFoundException {
        items = new ItemStack[data.length];
        this.type = type;
        this.slot = slot;

        for (int i = 0; i < items.length; i++) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data[i]);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            ItemStack item = (ItemStack) dataInput.readObject();

            items[i] = item;
        }

    }

    public ItemStack[] getItems() {
        return items;
    }

    public int getSlot() {
        return slot;
    }

    public InventoryType getType() {
        return type;
    }

    public Inventory createInventory() {
        if (type == InventoryType.CHEST) {
            return Bukkit.createInventory(null, slot);
        } else {
            return Bukkit.createInventory(null, type);
        }
    }

    public Inventory createInventory(InventoryHolder holder, String title) {
        if (type == InventoryType.CHEST) {
            return Bukkit.createInventory(holder, slot, title);
        } else {
            return Bukkit.createInventory(holder, type, title);
        }
    }

    public InventoryCompressor compress() throws IOException {
        return new InventoryCompressor(type, slot, items);
    }
}
