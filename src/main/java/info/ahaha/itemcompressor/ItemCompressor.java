package info.ahaha.itemcompressor;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ItemCompressor implements Serializable {

    private byte[][] compressed;

    public ItemCompressor(ItemStack... items) throws IOException {
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

    public ItemDecompressor decompress() throws IOException, ClassNotFoundException {
        return new ItemDecompressor(compressed);
    }
}
