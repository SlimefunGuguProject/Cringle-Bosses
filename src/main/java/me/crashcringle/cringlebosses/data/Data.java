/*
 * Created By ForbiddenSoul
 */

package me.crashcringle.cringlebosses.data;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Hounds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class Data implements Serializable {
    private static transient final long serialVersionUID = -1681012206529286330L;

    public final Map<String,ArrayList<Integer>> playerHounds;

    public final List<Integer> hounds;

    // Can be used for saving
    public Data(Map<String, ArrayList<Integer>> playerHounds, List<Integer> hounds) {
        this.playerHounds = playerHounds;
        this.hounds = hounds;
    }
    // Can be used for loading
    public Data(Data loadedData) {
        this.playerHounds = loadedData.playerHounds;
        this.hounds = loadedData.hounds;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(CringleBosses.inst().getDataFolder().getPath() + filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(CringleBosses.inst().getDataFolder().getPath() +filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static void getPlayerHoundsAndSave() {
        new Data(Hounds.getPlayerHounds(), Hounds.getHounds()).saveData("/Hounds.data");
        CringleBosses.inst().getLogger().log(Level.INFO, "Data Saved");
    }
    public static void loadPlayerHounds() {
        // Load the data from disc using our loadData method.
        File file = new File(CringleBosses.inst().getDataFolder().getPath() +"/Hounds.data");
        if (file.exists()) {
            Data data = new Data(Data.loadData("/Hounds.data"));

            // For each player that is current online send them a message
            Hounds.setHounds(data.hounds);
            Hounds.setPlayerHounds(data.playerHounds);
        }
        // Change all of the blocks around the spawn to what we have saved in our file.
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}