package me.crashcringle.cringlebosses.chaos;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.CringleBoss;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.other.Bell;
import me.crashcringle.cringlebosses.other.Souls;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class Chaos {

    public static void setup(CringleBosses cb, ItemGroup ig, Research chaosResearch) {

        SlimefunItemStack spectreOfChaos = new SlimefunItemStack("CRINGLE_SPECTRE_OF_CHAOS", Material.ENDERMAN_SPAWN_EGG,
                "&5混沌幽灵丹",
                "",
                "&7用于召唤混沌幽灵",
                "",
                "&c必须在合适的召唤祭坛上使用");

        ItemStack[] spectreRecipe = {
                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL),
                SlimefunItems.ENDER_LUMP_3,                  Souls.SOUL_OF_MADNESS,                     SlimefunItems.ENDER_LUMP_3,
                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL) };

        CringleBoss spectre = new CringleBoss(ig, spectreOfChaos, SummoningAltar.SUMMONING_ALTAR, spectreRecipe);
        spectre.register(cb);


        SlimefunItemStack chaosBell = new SlimefunItemStack("CRINGLE_CHAOS_BELL", Material.BELL,
                "&4幽魂钟",
                "",
                "&f迷惑并显示附近玩家",
                "",
                "&7但不影响敲钟人本身");

        ItemStack[] recipe = {
                Souls.SOUL_OF_MADNESS,              SlimefunItems.TALISMAN_MAGICIAN,        Souls.SOUL_OF_MADNESS,
                SlimefunItems.CHEESE,               new ItemStack(Material.BELL),         SlimefunItems.CHEESE,
                Souls.SOUL_OF_MADNESS,              SlimefunItems.GOLD_24K,        Souls.SOUL_OF_MADNESS};

        List<PotionEffect> potionEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.LEVITATION, 60, 2),
                new PotionEffect(PotionEffectType.GLOWING, 220, 1),
                new PotionEffect(PotionEffectType.LUCK, 220, 1),
                new PotionEffect(PotionEffectType.CONFUSION, 600, 2),
                new PotionEffect(PotionEffectType.BLINDNESS, 100, 2)
        );

        Bell bell = new Bell("&4幽魂钟", potionEffects, ig, chaosBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        chaosResearch.addItems(spectre, bell);
        chaosResearch.register();


    }
}
