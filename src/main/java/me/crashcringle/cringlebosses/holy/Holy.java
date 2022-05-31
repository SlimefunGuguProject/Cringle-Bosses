package me.crashcringle.cringlebosses.holy;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.other.Bell;
import me.crashcringle.cringlebosses.other.Souls;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class Holy {
    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {

        SlimefunItemStack holyBell = new SlimefunItemStack("CRINGLE_HOLY_BELL", Material.BELL,
                "&eHoly Bell",
                "",
                "&fGrants blessing to nearby players",
                "",
                "&7Grand bells do not effect the ringer");

        ItemStack[] recipe = {
                Souls.PURIFIED_SOUL,              SlimefunItems.TALISMAN_KNIGHT,       Souls.PURIFIED_SOUL,
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),  new ItemStack(Material.BELL),        new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                Souls.PURIFIED_SOUL,              SlimefunItems.GOLD_24K,        Souls.PURIFIED_SOUL};

        List<PotionEffect> potionEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.HEAL, 1, 20),
                new PotionEffect(PotionEffectType.REGENERATION, 1200, 1),
                new PotionEffect(PotionEffectType.HEALTH_BOOST, 600, 40)
        );

        Bell bell = new Bell("&eHoly Bell", potionEffects, ig, holyBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        research.addItems(bell);
        research.register();
    }
}
