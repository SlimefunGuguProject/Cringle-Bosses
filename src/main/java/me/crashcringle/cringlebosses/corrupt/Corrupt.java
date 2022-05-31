package me.crashcringle.cringlebosses.corrupt;
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
public class Corrupt {
    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {

        SlimefunItemStack corruptBell = new SlimefunItemStack("CRINGLE_CORRUPT_BELL", Material.BELL,
                "&eTwisted Bell",
                "",
                "&fGrants the mark of corruption",
                "&fto nearby players",
                "",
                "&7Grand bells do not effect the ringer");

        ItemStack[] recipe = {
                Souls.CRACKED_SOUL,              SlimefunItems.TALISMAN_LAVA,       Souls.CRACKED_SOUL,
                Souls.GLISTENING_SOUL,  new ItemStack(Material.BELL),                  Souls.GLISTENING_SOUL,
                Souls.CRACKED_SOUL,              SlimefunItems.GOLD_24K_BLOCK,        Souls.CRACKED_SOUL};

        List<PotionEffect> potionEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 1),
                new PotionEffect(PotionEffectType.UNLUCK, 2400, 3),
                new PotionEffect(PotionEffectType.SATURATION, 1200, 20),
                new PotionEffect(PotionEffectType.ABSORPTION, 1200, 20)
        );

        Bell bell = new Bell("&5Twisted &4Bell", potionEffects, ig, corruptBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        research.addItems(bell);
        research.register();
    }
}
