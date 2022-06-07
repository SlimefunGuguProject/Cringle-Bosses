package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import me.crashcringle.cringlebosses.CringleBoss;
import me.crashcringle.cringlebosses.CringleBosses;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Souls {
    public static final RecipeType REALIX_DROP = new RecipeType(new NamespacedKey(CringleBosses.inst(), "realix_drop"), new CustomItemStack(Material.NETHERITE_SWORD, "&bMob Drop", "", "&rKill the specified Mob Type to obtain this Item"));

    public static final SlimefunItemStack SOUL_OF_MADNESS = new SlimefunItemStack("CRINGLE_SOUL_OF_CHAOS", Material.ENDER_EYE,
                "&5&kll&4狂妄之魂&5&kll",
                        "",
                        "&5灵魂与你窃窃私语",
                        "&5你能听见他们的呼唤吗?",
                        "",
                        "&4&kTheSoulOfChaos");
    public static final SlimefunItemStack CRACKED_SOUL = new SlimefunItemStack("CRINGLE_CRACKED_SOUL", Material.FIRE_CHARGE,
            "&4破碎之魂",
            "",
            "&6灵魂中被玷污的碎片",
            "&6摸上去很烫",
            LoreBuilder.radioactive(Radioactivity.MODERATE),
            LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack ROYAL_GEL = new SlimefunItemStack("CRINGLE_ROYAL_GEL", Material.HEART_OF_THE_SEA,
            "&3皇家凝胶",
            "",
            "&6使粘液看上去高贵了起来");
    public static final SlimefunItemStack HARDENED_GEL = new SlimefunItemStack("CRINGLE_HARDENED_GEL", Material.NAUTILUS_SHELL,
            "&9硬化凝胶",
            "",
            "&b软硬兼施",
            "&b也许是某种收藏品");
    public static final SlimefunItemStack FADING_SOUL = new SlimefunItemStack("CRINGLE_FADING_SOUL", Material.FIREWORK_STAR,
            "&8隐匿之魂",
            "",
            "&7来自某些人的灵魂",
            "&7逐渐地被人淡忘");
    public static final SlimefunItemStack GLISTENING_SOUL = new SlimefunItemStack("CRINGLE_GLISTENING_SOUL", Material.NETHER_STAR,
            "&9&kll&b闪亮之魂&9&kll",
            "",
            "&5它的光是多么耀眼!",
            "&5但它是否真的可燃?");
    public static final SlimefunItemStack PURIFIED_SOUL = new SlimefunItemStack("CRINGLE_PURIFIED_SOUL", Material.HONEYCOMB,
            "&6&kll&e净化之魂&6&kll",
            "",
            "&6最纯粹的灵魂",
            "&6带给人间温暖");
    public static final SlimefunItemStack FADED_SOUL = new SlimefunItemStack("CRINGLE_FADED_SOUL", new ColoredFireworkStar(Color.WHITE,"&8Faded Soul"),
            "&8暗夜之魂",
            "",
            "&7当某人失足于黑暗中",
            "&7便成为了古老的遗迹");
    public Souls() {
    }
    public static void setup(CringleBosses cb, ItemGroup ig, Research soulsResearch) {
        List<SlimefunItem> souls = new ArrayList<>();
        souls.add(new SlimefunItem(ig, SOUL_OF_MADNESS, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(SlimefunUtils.getCustomHead(HeadTexture.CHEESE.getTexture()), "&5击杀混沌生物"), null,
                        null, null, null}));

        souls.add(new SlimefunItem(ig, CRACKED_SOUL, RecipeType.BARTER_DROP,
                new ItemStack[] {
                        null, null, null,
                        new CustomItemStack(SlimefunUtils.getCustomHead(HeadTexture.PIGLIN_HEAD.getTexture()), "猪灵"), null , new CustomItemStack(Material.NETHERITE_SWORD, "&b生物掉落物", "", "&c杀死腐败生物"),
                        null, null, null}));

        souls.add(new SlimefunItem(ig, FADING_SOUL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.WITHER_ROSE, "&5杀死失魂生物"), null,
                        null, null, null}));
        souls.add(new SlimefunItem(ig, FADED_SOUL, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL),
                        null,                                     FADING_SOUL,            null,
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL) }));

        souls.add(new SlimefunItem(ig, PURIFIED_SOUL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.LILY_OF_THE_VALLEY, "&5杀死神圣生物"), null,
                        null, null, null}));

        souls.add(new SlimefunItem(ig, ROYAL_GEL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.TRIDENT, "&5杀死寒武生物"), null,
                        null, null, null}));
        souls.add(new SlimefunItem(ig, HARDENED_GEL, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL),
                        null,                                     ROYAL_GEL,            null,
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL) }));

        souls.add(new SlimefunItem(ig, GLISTENING_SOUL, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        SlimefunItems.HEATING_COIL,         SlimefunItems.MAGIC_LUMP_3,                               SlimefunItems.HEATING_COIL,
                        SlimefunItems.HEATING_COIL,        CRACKED_SOUL,            SlimefunItems.HEATING_COIL,
                        SlimefunItems.HEATING_COIL,        SlimefunItems.MAGIC_LUMP_3,                               SlimefunItems.HEATING_COIL }));

        for (SlimefunItem soul : souls) {
            soul.register(cb);
            soulsResearch.addItems(soul);
        }
        soulsResearch.register();
    }
}
