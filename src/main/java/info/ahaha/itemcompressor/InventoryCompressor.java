package info.ahaha.itemcompressor;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class InventoryCompressor implements Serializable {

    private byte[][] compressed;
    private InventoryType type;
    private int slot;

    public InventoryCompressor(Inventory inv) throws IOException {
        ItemStack[] contents = inv.getContents();
        type = inv.getType();
        slot = inv.getSize();

        compressed = new byte[contents.length][];

        for (int i = 0; i < compressed.length; i++) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(contents[i]);
            dataOutput.close();

            compressed[i] = outputStream.toByteArray();

        }
    }

    public InventoryCompressor(InventoryType type, int slot, ItemStack... items) throws IOException {
        this.type = type;
        this.slot = slot;

        compressed = new byte[items.length][];

        for (int i = 0; i < compressed.length; i++) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(items[i]);
            dataOutput.close();

            compressed[i] = outputStream.toByteArray();

        }
    }

    public byte[][] getCompressed() {
        return compressed;
    }

    public InventoryType getType() {
        return type;
    }

    public int getSlot() {
        return slot;
    }

    public InventoryDecompressor decompress() throws IOException, ClassNotFoundException {
        return new InventoryDecompressor(type, slot, compressed);
    }
}
