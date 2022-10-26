package info.ahaha.itemcompressor;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

public class ItemDecompressor implements Serializable {

    private ItemStack[] items;

    public ItemDecompressor(byte[]... data) throws IOException, ClassNotFoundException {
        items = new ItemStack[data.length];

        for (int i = 0; i < items.length; i++){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data[i]);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            ItemStack item = (ItemStack) dataInput.readObject();

            items[i] = item;
        }

    }

    public ItemStack[] getItems() {
        return items;
    }

    public ItemCompressor compress() throws IOException {
        return new ItemCompressor(items);
    }
}
