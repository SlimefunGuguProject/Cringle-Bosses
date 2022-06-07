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
            "&4召唤祭坛",
            "",
            "&c一种多方块式的祭坛",
            "&c用于召唤各种Boss");

    public static final SlimefunItemStack SUMMONING_PEDESTAL = new SlimefunItemStack("CRINGLE_SUMMONING_PEDESTAL", Material.RESPAWN_ANCHOR,
            "&4召唤基座",
            "",
            "&c召唤祭坛的组成部分");
    
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

        ItemStack itemGroupItem = new CustomItemStack(Material.SOUL_CAMPFIRE, "&7混沌Boss", "", "&a> 单击打开");

        nestedItemGroup = new NestedItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_bosses"), itemGroupItem);
        resourcesItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "cringle_resources"), nestedItemGroup, new CustomItemStack(Material.LANTERN, "&6合成配方"));
        chaosItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "chaos"), nestedItemGroup, new CustomItemStack(Material.REDSTONE, "&4混沌"));
        primeItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "primordial"), nestedItemGroup, new CustomItemStack(Material.TRIDENT, "&b寒武"));
        rogueItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "rogue"), nestedItemGroup, new CustomItemStack(Material.WITHER_ROSE, "&2罪恶"));
        holyItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "holy"), nestedItemGroup, new CustomItemStack(Material.HONEYCOMB, "&e神圣"));
        corruptItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "corrupt"), nestedItemGroup, new CustomItemStack(Material.FIRE_CHARGE, "&5腐化"));
        oldItemGroup = new SubItemGroup(new NamespacedKey(CringleBosses.inst(), "old"), nestedItemGroup, new CustomItemStack(Material.TOTEM_OF_UNDYING, "&8古迹"));


        NamespacedKey researchKey = new NamespacedKey(CringleBosses.inst(), "Chaos_bosses_research");
        soulResearch = new Research(researchKey, 12600001, "灵魂的真理", 45);
        chaosResearch = new Research(researchKey, 12600002, "混沌的脚步声", 60);
        primeResearch = new Research(researchKey, 12600003, "祖上的手稿", 65);
        holyResearch = new Research(researchKey, 12600004, "上帝的言语", 56);
        oldResearch = new Research(researchKey, 12600005, "古老的故事", 82);
        rogueResearch = new Research(researchKey, 12600006, "遗忘在世间", 94);
        corruptResearch = new Research(researchKey, 12600007, "知识的禁果", 100);

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
