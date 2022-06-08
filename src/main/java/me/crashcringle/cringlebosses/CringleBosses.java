package me.crashcringle.cringlebosses;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.scheduler.BukkitTask;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.logging.Level;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getMinecraftVersion;

public class CringleBosses extends JavaPlugin implements SlimefunAddon {

    private static CringleBosses instance;

    public static CringleBosses inst() {
        return instance;
    }
    @Override
    public void onEnable() {

        instance = this;
        MagicAPI api = getMagicAPI();
        CringleBosses.inst().getLogger().log(Level.INFO, "########################################");
        CringleBosses.inst().getLogger().log(Level.INFO, "            Cringle Bosses              ");
        CringleBosses.inst().getLogger().log(Level.INFO, "########################################");
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }
        new Setup();

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }
    public static @Nullable BukkitTask runSync(@Nonnull Runnable runnable, long delay) {
        Validate.notNull(runnable, "Cannot run null");
        Validate.isTrue(delay >= 0, "The delay cannot be negative");

        // Run the task instantly within a Unit Test
        if (getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {
            runnable.run();
            return null;
        }

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTaskLater(instance, runnable, delay);
    }
    public MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
        if (magicPlugin == null || !(magicPlugin instanceof MagicAPI)) {
            return null;
        }
        return (MagicAPI)magicPlugin;
    }
    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
