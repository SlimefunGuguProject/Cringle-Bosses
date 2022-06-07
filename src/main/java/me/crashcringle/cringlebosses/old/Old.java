package me.crashcringle.cringlebosses.old;
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
public class Old {
    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {

        SlimefunItemStack oldBell = new SlimefunItemStack("CRINGLE_OLD_BELL", Material.BELL,
                "&8古钟",
                "",
                "&f削弱并显示附近玩家",
                "",
                "&7不影响敲钟人自身");

        ItemStack[] recipe = {
                Souls.HARDENED_GEL,              SlimefunItems.TALISMAN_WISE,       Souls.PURIFIED_SOUL,
                Souls.FADED_SOUL,               new ItemStack(Material.BELL),         Souls.CRACKED_SOUL,
                Souls.SOUL_OF_MADNESS,           SlimefunItems.GOLD_24K,     Souls.GLISTENING_SOUL};

        List<PotionEffect> potionEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.WEAKNESS, 800, 2),
                new PotionEffect(PotionEffectType.SLOW_DIGGING, 2400, 3),
                new PotionEffect(PotionEffectType.HUNGER, 200, 3),
                new PotionEffect(PotionEffectType.SLOW, 400, 2)
        );

        Bell bell = new Bell("&8古钟", potionEffects, ig, oldBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        research.addItems(bell);
        research.register();
    }
}
