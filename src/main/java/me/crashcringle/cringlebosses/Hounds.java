package me.crashcringle.cringlebosses;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.crashcringle.cringlebosses.other.PuppyChow;
import me.crashcringle.cringlebosses.other.Souls;
import me.crashcringle.cringlebosses.prime.ControlRod;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Hounds {
    private static Map<String, ArrayList<Integer>> playerHounds = new HashMap<>();
    private static List<Integer> hounds = new ArrayList<>();
    public static final SlimefunItemStack ANGEL_PUP_CHOW = new SlimefunItemStack("CRINGLE_ANGEL_PUP_CHOW", Material.ROTTEN_FLESH,
            "&eAngelic Puppy Chow",
            "",
            "&fFeed this to your wolf to",
            "&ftransform it into an Angel Pup");
    public static final SlimefunItemStack HELLHOUND_CHOW = new SlimefunItemStack("CRINGLE_HELLHOUND_CHOW", Material.ROTTEN_FLESH,
            "&4Demonic Puppy Chow",
            "",
            "&fFeed this to your wolf to",
            "&ftransform it into a Hellhound");
    public static final SlimefunItemStack OREO_FLAVORED_CHOW = new SlimefunItemStack("CRINGLE_OREO_FLAVORED_CHOW", Material.ROTTEN_FLESH,
            "&fOr&0eo &fFlavored &0Puppy &fChow?",
            "",
            "&fFeed this to your wolf to...",
            "&fI...don't know what this will do");
    public static final SlimefunItemStack HOUND_CONTROL_ROD = new SlimefunItemStack("CRINGLE_HOUND_CONTROL_ROD", Material.BLAZE_ROD,
            "&bHound Control Rod",
            "",
            LoreBuilder.RIGHT_CLICK_TO_USE,
            "&7Command your Hounds to sit",
            "&or rise");
    public static Map<String, ArrayList<Integer>> getPlayerHounds() {
        return playerHounds;
    }

    public static void setPlayerHounds(Map<String, ArrayList<Integer>> playerHounds) {
        Hounds.playerHounds = playerHounds;
    }

    public static List<Integer> getHounds() {
        return hounds;
    }

    public static void setHounds(List<Integer> hounds) {
        Hounds.hounds = hounds;
    }

    public enum houndType {
        HELLHOUND,
        ANGEL_PUP,
        OREO
    };
    public Hounds() {

    }

    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {
        List<PotionEffect> angelEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.HEAL, 10, 300),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 5),
                new PotionEffect(PotionEffectType.REGENERATION, 10000, 2),
                new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000, 200),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000, 1),
                new PotionEffect(PotionEffectType.SPEED, 10000, 4),
                new PotionEffect(PotionEffectType.JUMP, 10000, 5)
        );
        List<PotionEffect> hellEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.SPEED, 10000, 1),
                new PotionEffect(PotionEffectType.HEAL, 10, 300),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 10),
                new PotionEffect(PotionEffectType.REGENERATION, 10000, 1),
                new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000, 100),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000, 1),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000, 2)
        );
        List<PotionEffect> oreoEffects = Arrays.asList(
                new PotionEffect(PotionEffectType.SPEED, 10000, 5),
                new PotionEffect(PotionEffectType.JUMP, 10000, 5),
                new PotionEffect(PotionEffectType.HEAL, 10, 300),
                new PotionEffect(PotionEffectType.CONDUIT_POWER, 10000, 2),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 20),
                new PotionEffect(PotionEffectType.REGENERATION, 10000, 1),
                new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000, 100),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000, 1),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000, 2)
        );
        PuppyChow angelPupChow = new PuppyChow(angelEffects, houndType.ANGEL_PUP, ig, ANGEL_PUP_CHOW, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[]{
                        SlimefunItems.FISH_JERKY,              new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),       SlimefunItems.MONSTER_JERKY,
                        SlimefunItems.BEEF_JERKY,               Souls.PURIFIED_SOUL,        SlimefunItems.PORK_JERKY,
                        SlimefunItems.CHICKEN_JERKY,           SlimefunItems.MUTTON_JERKY,     SlimefunItems.RABBIT_JERKY});

        PuppyChow hellHoundChow = new PuppyChow(hellEffects, houndType.HELLHOUND, ig, HELLHOUND_CHOW, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[]{
                        SlimefunItems.FISH_JERKY,              new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),       SlimefunItems.MONSTER_JERKY,
                        SlimefunItems.BEEF_JERKY,               Souls.CRACKED_SOUL,        SlimefunItems.PORK_JERKY,
                        SlimefunItems.CHICKEN_JERKY,           SlimefunItems.MUTTON_JERKY,     SlimefunItems.RABBIT_JERKY});

        PuppyChow oreoChow = new PuppyChow(oreoEffects, houndType.OREO, ig, OREO_FLAVORED_CHOW, RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{
                        Souls.PURIFIED_SOUL,          SlimefunItems.ENCHANTMENT_RUNE,       Souls.PURIFIED_SOUL,
                        ANGEL_PUP_CHOW,               Souls.GLISTENING_SOUL,          HELLHOUND_CHOW,
                        Souls.PURIFIED_SOUL,          SlimefunItems.MUTTON_JERKY,     Souls.PURIFIED_SOUL});

        ControlRod controlRod = new ControlRod(ig, HOUND_CONTROL_ROD, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.WOLF_SPAWN_EGG),          null,       null,
                        null,               Souls.HARDENED_GEL,          null,
                        null,          null,     SlimefunItems.INFUSED_MAGNET});
        angelPupChow.register(cb);
        hellHoundChow.register(cb);
        controlRod.register(cb);
        oreoChow.register(cb);
        research.addItems(angelPupChow,hellHoundChow,oreoChow, controlRod);
        research.register();
    }

}
