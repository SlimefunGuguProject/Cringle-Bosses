package me.crashcringle.cringlebosses;

import org.bukkit.plugin.java.JavaPlugin;


import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;



import java.util.logging.Level;

public class CringleBosses extends JavaPlugin implements SlimefunAddon {

    private static CringleBosses instance;

    public static CringleBosses inst() {
        return instance;
    }
    @Override
    public void onEnable() {

        instance = this;

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
