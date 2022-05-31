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
                "&5&kll&4Soul of Madness&5&kll",
                        "",
                        "&5The soul whispers to you",
                        "&5Do you hear its call?",
                        "",
                        "&4&kTheSoulOfChaos");
    public static final SlimefunItemStack CRACKED_SOUL = new SlimefunItemStack("CRINGLE_CRACKED_SOUL", Material.FIRE_CHARGE,
            "&4Cracked Soul",
            "",
            "&6A tainted fragment of a soul",
            "&6Hot to the touch",
            LoreBuilder.radioactive(Radioactivity.MODERATE),
            LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack ROYAL_GEL = new SlimefunItemStack("CRINGLE_ROYAL_GEL", Material.HEART_OF_THE_SEA,
            "&3Royal Gel",
            "",
            "&6Does not make slimes become friendly");
    public static final SlimefunItemStack HARDENED_GEL = new SlimefunItemStack("CRINGLE_HARDENED_GEL", Material.NAUTILUS_SHELL,
            "&9Hardened Gel",
            "",
            "&bSimultaneously hard and soft",
            "&bPerhaps some sort of collectible");
    public static final SlimefunItemStack FADING_SOUL = new SlimefunItemStack("CRINGLE_FADING_SOUL", Material.FIREWORK_STAR,
            "&8Fading Soul",
            "",
            "&7A piece of someone's soul",
            "&7Slowly fading away");
    public static final SlimefunItemStack GLISTENING_SOUL = new SlimefunItemStack("CRINGLE_GLISTENING_SOUL", Material.NETHER_STAR,
            "&9&kll&bGlistening Soul&9&kll",
            "",
            "&5The light shines so bright",
            "&5It almost burns?");
    public static final SlimefunItemStack PURIFIED_SOUL = new SlimefunItemStack("CRINGLE_PURIFIED_SOUL", Material.HONEYCOMB,
            "&6&kll&ePurified Soul&6&kll",
            "",
            "&6A Soul in its purest form",
            "&6It feels so warm");
    public static final SlimefunItemStack FADED_SOUL = new SlimefunItemStack("CRINGLE_FADED_SOUL", new ColoredFireworkStar(Color.WHITE,"&8Faded Soul"),
            "&8Faded Soul",
            "",
            "&7A piece of someone's soul",
            "&7Once fading into darkness",
            "&7Now a relic of the past");
    public Souls() {
    }
    public static void setup(CringleBosses cb, ItemGroup ig, Research soulsResearch) {
        List<SlimefunItem> souls = new ArrayList<>();
        souls.add(new SlimefunItem(ig, SOUL_OF_MADNESS, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(SlimefunUtils.getCustomHead(HeadTexture.CHEESE.getTexture()), "&5Kill Mobs of Chaos"), null,
                        null, null, null}));

        souls.add(new SlimefunItem(ig, CRACKED_SOUL, RecipeType.BARTER_DROP,
                new ItemStack[] {
                        null, null, null,
                        new CustomItemStack(SlimefunUtils.getCustomHead(HeadTexture.PIGLIN_HEAD.getTexture()), "Piglin"), null , new CustomItemStack(Material.NETHERITE_SWORD, "&bMob Drop", "", "&cKill Corrupted Mobs"),
                        null, null, null}));

        souls.add(new SlimefunItem(ig, FADING_SOUL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.WITHER_ROSE, "&5Kill Soulless Mobs"), null,
                        null, null, null}));
        souls.add(new SlimefunItem(ig, FADED_SOUL, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL),
                        null,                                     FADING_SOUL,            null,
                        new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL) }));

        souls.add(new SlimefunItem(ig, PURIFIED_SOUL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.LILY_OF_THE_VALLEY, "&5Kill Holy Mobs"), null,
                        null, null, null}));

        souls.add(new SlimefunItem(ig, ROYAL_GEL, REALIX_DROP,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItemStack(Material.TRIDENT, "&5Kill Primordial Mobs"), null,
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
