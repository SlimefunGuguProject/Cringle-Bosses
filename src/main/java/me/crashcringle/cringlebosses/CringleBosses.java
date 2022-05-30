package me.crashcringle.cringlebosses;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.chaos.Chaos;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import me.crashcringle.cringlebosses.other.SummoningAltarListener;
import me.crashcringle.cringlebosses.other.SummoningPedestal;
import me.crashcringle.cringlebosses.prime.Prime;
import me.crashcringle.cringlebosses.prime.PrimordialBell;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import java.util.logging.Level;

public class CringleBosses extends JavaPlugin implements SlimefunAddon {

    private static CringleBosses instance;

    private NestedItemGroup nestedItemGroup;
    private ItemGroup chaosItemGroup;
    private ItemGroup corruptItemGroup;
    private ItemGroup primeItemGroup;
    private ItemGroup oldItemGroup;
    private ItemGroup holyItemGroup;
    private ItemGroup rogueItemGroup;

    private Research chaosResearch;

    private Research primeResearch;

    public static SlimefunItemStack SUMMONING_ALTAR = new SlimefunItemStack("CRINGLE_SUMMONING_ALTAR", Material.ENCHANTING_TABLE,
            "&4Summoning Altar",
            "",
            "&cMulti-Block Altar for",
            "&csummoning various creatures");

    public static final SlimefunItemStack SUMMONING_PEDESTAL = new SlimefunItemStack("CRINGLE_SUMMONING_PEDESTAL", Material.RESPAWN_ANCHOR,
            "&4Summoning Pedestal",
            "",
            "&cPart of the Summoning Altar");

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

        ItemStack itemGroupItem = new CustomItemStack(Material.SOUL_CAMPFIRE, "&7Bosses", "", "&a> Click to open");

        nestedItemGroup = new NestedItemGroup(new NamespacedKey(this, "cringle_bosses"), itemGroupItem);
        chaosItemGroup = new SubItemGroup(new NamespacedKey(this, "chaos"), nestedItemGroup, new CustomItemStack(Material.REDSTONE, "&4Chaos Realix"));
        primeItemGroup = new SubItemGroup(new NamespacedKey(this, "primordial"), nestedItemGroup, new CustomItemStack(Material.DIAMOND, "&bPrime Realix"));
        rogueItemGroup = new SubItemGroup(new NamespacedKey(this, "rogue"), nestedItemGroup, new CustomItemStack(Material.BOW, "&2Rogue Realix"));
        holyItemGroup = new SubItemGroup(new NamespacedKey(this, "holy"), nestedItemGroup, new CustomItemStack(Material.HONEYCOMB, "&eHoly Realix"));
        corruptItemGroup = new SubItemGroup(new NamespacedKey(this, "corrupt"), nestedItemGroup, new CustomItemStack(Material.FIRE_CHARGE, "&5Corrupt Realix"));
        oldItemGroup = new SubItemGroup(new NamespacedKey(this, "old"), nestedItemGroup, new CustomItemStack(Material.TOTEM_OF_UNDYING, "&8Old Realix"));


        NamespacedKey researchKey = new NamespacedKey(this, "Chaos_bosses_research");
        chaosResearch = new Research(researchKey, 12600001, "The footholds of chaos", 2);
        primeResearch = new Research(researchKey, 12600002, "The manuscripts of the primordials", 1);

        ItemStack[] altarRecipe = {
                null,                                       SlimefunItems.ANCIENT_ALTAR,                          null,
                SlimefunItems.ENDER_LUMP_2,                 SlimefunItems.GOLD_10K,                               SlimefunItems.ENDER_LUMP_2,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,        SlimefunItems.GOLD_10K,                               SlimefunItems.WITHER_PROOF_OBSIDIAN };

        SummoningAltar altar = new SummoningAltar(chaosItemGroup, SUMMONING_ALTAR, RecipeType.MAGIC_WORKBENCH, altarRecipe);
        altar.register(this);



        ItemStack[] pedestalRecipe = {
                new ItemStack(Material.CRYING_OBSIDIAN),    new ItemStack(Material.CRYING_OBSIDIAN),            new ItemStack(Material.CRYING_OBSIDIAN),
                new ItemStack(Material.GLOWSTONE),          SlimefunItems.MAGIC_LUMP_3,                  new ItemStack(Material.GLOWSTONE),
                new ItemStack(Material.CRYING_OBSIDIAN),    new ItemStack(Material.CRYING_OBSIDIAN),            new ItemStack(Material.CRYING_OBSIDIAN) };

        SummoningPedestal pedestal = new SummoningPedestal(chaosItemGroup, SUMMONING_PEDESTAL, RecipeType.MAGIC_WORKBENCH, pedestalRecipe, new SlimefunItemStack(SUMMONING_PEDESTAL, 4));
        pedestal.register(this);

        new SummoningAltarListener(this, altar, pedestal);

        Chaos.setup(this, chaosItemGroup, chaosResearch);
        Prime.setup(this, primeItemGroup, primeResearch);

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
