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

    public Setup() {

        ItemStack itemGroupItem = new CustomItemStack(Material.SOUL_CAMPFIRE, "&7Bosses", "", "&a> Click to open");

        nestedItemGroup = new NestedItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_bosses"), itemGroupItem);
        resourcesItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_resources"), nestedItemGroup, new CustomItemStack(Material.LANTERN, "&6Crafting Recipes"));
        chaosItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "chaos"), nestedItemGroup, new CustomItemStack(Material.REDSTONE, "&4Chaos Realix"));
        primeItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "primordial"), nestedItemGroup, new CustomItemStack(Material.TRIDENT, "&bPrime Realix"));
        rogueItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "rogue"), nestedItemGroup, new CustomItemStack(Material.WITHER_ROSE, "&2Rogue Realix"));
        holyItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "holy"), nestedItemGroup, new CustomItemStack(Material.HONEYCOMB, "&eHoly Realix"));
        corruptItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "corrupt"), nestedItemGroup, new CustomItemStack(Material.FIRE_CHARGE, "&5Corrupt Realix"));
        oldItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "old"), nestedItemGroup, new CustomItemStack(Material.TOTEM_OF_UNDYING, "&8Old Realix"));


        NamespacedKey researchKey = new NamespacedKey(CringleBosses.inst(), "Chaos_bosses_research");
        soulResearch = new Research(researchKey, 12600001, "The souls of reality", 24);
        chaosResearch = new Research(researchKey, 12600002, "The footholds of chaos", 30);
        primeResearch = new Research(researchKey, 12600003, "The manuscripts of the primordials", 30);
        holyResearch = new Research(researchKey, 12600004, "The word of the God?", 30);
        oldResearch = new Research(researchKey, 12600005, "The tales of old", 30);
        rogueResearch = new Research(researchKey, 12600006, "Tales long since forgotten", 30);
        corruptResearch = new Research(researchKey, 12600007, "The forbidden fruits of knowledge", 30);

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

        SummoningPedestal pedestal = new SummoningPedestal(chaosItemGroup, SUMMONING_PEDESTAL, RecipeType.MAGIC_WORKBENCH, pedestalRecipe, new SlimefunItemStack(SUMMONING_PEDESTAL, 4));
        pedestal.register(CringleBosses.inst());

        new SummoningAltarListener(CringleBosses.inst(), altar, pedestal);

        Souls.setup(CringleBosses.inst(), resourcesItemGroup, soulResearch);
        Chaos.setup(CringleBosses.inst(), chaosItemGroup, chaosResearch);
        Prime.setup(CringleBosses.inst(), primeItemGroup, primeResearch);
        Holy.setup(CringleBosses.inst(), holyItemGroup, holyResearch);
        Corrupt.setup(CringleBosses.inst(), corruptItemGroup, corruptResearch);
        Old.setup(CringleBosses.inst(), oldItemGroup, oldResearch);
        Rogue.setup(CringleBosses.inst(), rogueItemGroup, rogueResearch);


    }
}