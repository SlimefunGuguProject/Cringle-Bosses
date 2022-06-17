package me.crashcringle.cringlebosses;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.chaos.Chaos;
import me.crashcringle.cringlebosses.corrupt.Corrupt;
import me.crashcringle.cringlebosses.holy.Holy;
import me.crashcringle.cringlebosses.old.Old;
import me.crashcringle.cringlebosses.other.Souls;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import me.crashcringle.cringlebosses.other.SummoningAltarListener;
import me.crashcringle.cringlebosses.other.SummoningPedestal;
import me.crashcringle.cringlebosses.prime.Prime;
import me.crashcringle.cringlebosses.rogue.Rogue;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Setup {
    public static SlimefunItemStack SUMMONING_ALTAR = new SlimefunItemStack("CRINGLE_SUMMONING_ALTAR", Material.ENCHANTING_TABLE,
            "&4Summoning Altar",
            "",
            "&cMulti-Block Altar for",
            "&csummoning various creatures");

    public static final SlimefunItemStack SUMMONING_PEDESTAL = new SlimefunItemStack("CRINGLE_SUMMONING_PEDESTAL", Material.RESPAWN_ANCHOR,
            "&4Summoning Pedestal",
            "",
            "&cPart of the Summoning Altar");
    private NestedItemGroup nestedItemGroup;
    private ItemGroup resourcesItemGroup;
    private ItemGroup chaosItemGroup;
    private ItemGroup corruptItemGroup;
    private ItemGroup primeItemGroup;
    private ItemGroup oldItemGroup;
    private ItemGroup holyItemGroup;
    private ItemGroup rogueItemGroup;

    private Research chaosResearch;
    private Research holyResearch;
    private Research oldResearch;
    private Research corruptResearch;
    private Research rogueResearch;

    private Research primeResearch;
    private Research soulResearch;
    private Research chowResearch;

    public Setup() {

        ItemStack itemGroupItem = new CustomItemStack(Material.SOUL_CAMPFIRE, "&7Bosses", "", "&a> Click to open");

        nestedItemGroup = new NestedItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_bosses"), itemGroupItem);
        resourcesItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_resources"), nestedItemGroup, new CustomItemStack(Material.LANTERN, "&6Cringle Resources"));
        chaosItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "chaos"), nestedItemGroup, new CustomItemStack(Material.REDSTONE, "&4Chaos Realix"));
        primeItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "primordial"), nestedItemGroup, new CustomItemStack(Material.TRIDENT, "&bPrime Realix"));
        rogueItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "rogue"), nestedItemGroup, new CustomItemStack(Material.WITHER_ROSE, "&2Rogue Realix"));
        holyItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "holy"), nestedItemGroup, new CustomItemStack(Material.HONEYCOMB, "&eHoly Realix"));
        corruptItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "corrupt"), nestedItemGroup, new CustomItemStack(Material.FIRE_CHARGE, "&5Corrupt Realix"));
        oldItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "old"), nestedItemGroup, new CustomItemStack(Material.TOTEM_OF_UNDYING, "&8Old Realix"));



        soulResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Souls_research"), 12600001, "The souls of reality", 24);
        chaosResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Chaos_tier1_research"), 12600002, "The footholds of chaos", 75);
        primeResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Prime_tier1_research"), 12600003, "The manuscripts of the primordials", 75);
        holyResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Holy_tier1_research"), 12600004, "The word of the God?", 75);
        oldResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Old_tier1_bosses_research"), 12600005, "The tales of old", 75);
        rogueResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Rogue_tier1_research"), 12600006, "Tales long since forgotten", 75);
        corruptResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Corrupt_tier1_research"), 12600007, "The forbidden fruits of knowledge", 75);
        chowResearch = new Research(new NamespacedKey(CringleBosses.inst(), "Chow_research"), 12600008, "Dog Food...", 24);




        ItemStack[] altarRecipe = {
                null,                                       SlimefunItems.ANCIENT_ALTAR,                          null,
                SlimefunItems.ENDER_LUMP_2,                 SlimefunItems.GOLD_10K,                               SlimefunItems.ENDER_LUMP_2,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,        SlimefunItems.GOLD_10K,                               SlimefunItems.WITHER_PROOF_OBSIDIAN };

        SummoningAltar altar = new SummoningAltar(chaosItemGroup, SUMMONING_ALTAR, RecipeType.MAGIC_WORKBENCH, altarRecipe);
        altar.register(CringleBosses.inst());

        ItemStack[] pedestalRecipe = {
                new ItemStack(Material.CRYING_OBSIDIAN),    new ItemStack(Material.CRYING_OBSIDIAN),            new ItemStack(Material.CRYING_OBSIDIAN),
                new ItemStack(Material.GLOWSTONE),          SlimefunItems.MAGIC_LUMP_3,                  new ItemStack(Material.GLOWSTONE),
                new ItemStack(Material.CRYING_OBSIDIAN),    new ItemStack(Material.CRYING_OBSIDIAN),            new ItemStack(Material.CRYING_OBSIDIAN) };

        SummoningPedestal pedestal = new SummoningPedestal(resourcesItemGroup, SUMMONING_PEDESTAL, RecipeType.MAGIC_WORKBENCH, pedestalRecipe, new SlimefunItemStack(SUMMONING_PEDESTAL, 4));
        pedestal.register(CringleBosses.inst());
        new SummoningAltarListener(CringleBosses.inst(), altar, pedestal);

        Hounds.setup(CringleBosses.inst(), resourcesItemGroup, chowResearch);
        Souls.setup(CringleBosses.inst(), resourcesItemGroup, soulResearch);
        Chaos.setup(CringleBosses.inst(), chaosItemGroup, chaosResearch);
        Prime.setup(CringleBosses.inst(), primeItemGroup, primeResearch);
        Holy.setup(CringleBosses.inst(), holyItemGroup, holyResearch);
        Corrupt.setup(CringleBosses.inst(), corruptItemGroup, corruptResearch);
        Old.setup(CringleBosses.inst(), oldItemGroup, oldResearch);
        Rogue.setup(CringleBosses.inst(), rogueItemGroup, rogueResearch);

    }
}
