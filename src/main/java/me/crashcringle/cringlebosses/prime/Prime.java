package me.crashcringle.cringlebosses.prime;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.other.Bell;
import me.crashcringle.cringlebosses.other.Souls;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;


public class Prime {

    public static void setup(CringleBosses cb, ItemGroup ig, Research primeResearch) {

        SlimefunItemStack primordialBell = new SlimefunItemStack("CRINGLE_PRIMORDIAL_BELL", Material.BELL,
                "&b寒武钟",
                "",
                "&f鼓舞附近的玩家",
                "",
                "&7不影响敲钟人自身");

        ItemStack[] recipe = {
                Souls.HARDENED_GEL,              SlimefunItems.TALISMAN_WARRIOR,       Souls.HARDENED_GEL,
                Souls.ROYAL_GEL,                 new ItemStack(Material.BELL),        Souls.ROYAL_GEL,
                Souls.HARDENED_GEL,              SlimefunItems.GOLD_24K,        Souls.HARDENED_GEL};

        List<PotionEffect> potionEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.CONDUIT_POWER, 600, 3),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 2),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1)
        );

        Bell bell = new Bell("&bPrimordial Bell", potionEffects, ig, primordialBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);


        primeResearch.addItems(bell);
        primeResearch.register();
    }
}
