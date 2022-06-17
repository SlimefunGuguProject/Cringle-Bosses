package me.crashcringle.cringlebosses.chaos;

import com.elmakers.mine.bukkit.api.magic.MageController;
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
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Chaos {

    public static void setup(CringleBosses cb, ItemGroup ig, Research chaosResearch) {
        MageController controller = CringleBosses.magicAPI.getController();
        Map<String, CringleBoss> chaosBosses = new HashMap<>();

        chaosBosses.put("chaoscrash",
                new CringleBoss("§5§lChaos Crash", CringleBoss.bossType.MAGIC_ELITE, EntityType.VEX, 250, ig,
                        new SlimefunItemStack("CRINGLE_CHAOS_CRASH", Objects.requireNonNull(controller.createItem("ChaosCrashHead")),
                                "&5&lChaos Crash",
                                "",
                                "&7Summons the ruler of chaos....",
                                "&5&lChaos Crash",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                Souls.SOUL_OF_MADNESS,        Souls.SOUL_OF_MADNESS,                 Souls.SOUL_OF_MADNESS,
                                Souls.SOUL_OF_MADNESS,        controller.createItem("AncientTome"),                     Souls.SOUL_OF_MADNESS,
                                Souls.SOUL_OF_MADNESS,        Souls.SOUL_OF_MADNESS,                                Souls.SOUL_OF_MADNESS }
                ));
        chaosBosses.get("chaoscrash").setMagicInternalName("imageofchaos");

//        chaosBosses.put("Sheogorath",
//                new CringleBoss("§4Sheo§5gorath", CringleBoss.bossType.MAGIC_ELITE, EntityType.ZOMBIE, ig,
//                        new SlimefunItemStack("CRINGLE_SHEOGORATH", Material.VILLAGER_SPAWN_EGG,
//                                "§4Sheo§5gorath&7:&c Daedric Prince of Madness",
//                                "",
//                                "&7Summons §4Sheo§5gorath",
//                                "",
//                                "&cMust be used at the appropriate Summoning Altar"),
//                        new ItemStack[]{
//                                SlimefunItems.CHEESE,        SlimefunItems.MAGIC_LUMP_3,                SlimefunItems.GILDED_IRON_CHESTPLATE,
//                                controller.createItem("talisman"),                  Souls.SOUL_OF_MADNESS,                     controller.createItem("talisman"),
//                                SlimefunItems.GILDED_IRON_BOOTS,        SlimefunItems.MAGIC_LUMP_3,                                SlimefunItems.GILDED_IRON_HELMET }
//                ));
//        chaosBosses.get("ironman").setMagicInternalName("ironman");

        chaosBosses.put("grummitemagusdeathdealer",
                new CringleBoss("§aGrummite Magus Deathdealer", CringleBoss.bossType.MAGIC_ELITE, EntityType.DROWNED, ig,
                        new SlimefunItemStack("CRINGLE_GRUMMITE_DEATHDEALER", Material.DROWNED_SPAWN_EGG,
                                "&aGrummite Magus Deathdealer",
                                "",
                                "&7Summons the &aGrummite Magus Deathdealer",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                new ItemStack(Material.TUBE_CORAL),        SlimefunItems.STAFF_WATER,                 new ItemStack(Material.TUBE_CORAL_FAN),
                                controller.createItem("talisman"),                  Souls.SOUL_OF_MADNESS,                     controller.createItem("talisman"),
                                new ItemStack(Material.TUBE_CORAL),        SlimefunItems.TALISMAN_WATER,                                new ItemStack(Material.TUBE_CORAL_FAN) }
                ));
        chaosBosses.get("grummitemagusdeathdealer").setMagicInternalName("grummitemagusdeathdealer");

        chaosBosses.put("ironman",
                new CringleBoss("§eIron §2Man", CringleBoss.bossType.MAGIC_ELITE, EntityType.ZOMBIE, ig,
                        new SlimefunItemStack("CRINGLE_IRON_MAN", Material.PLAYER_HEAD,
                                "&eIron &4Man",
                                "",
                                "&7Summons....&dIron Man..?",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                SlimefunItems.GILDED_IRON_LEGGINGS,        SlimefunItems.MAGIC_LUMP_3,                SlimefunItems.GILDED_IRON_CHESTPLATE,
                                controller.createItem("talisman"),                  Souls.SOUL_OF_MADNESS,                     controller.createItem("talisman"),
                                SlimefunItems.GILDED_IRON_BOOTS,        SlimefunItems.MAGIC_LUMP_3,                                SlimefunItems.GILDED_IRON_HELMET }
                ));
        chaosBosses.get("ironman").setMagicInternalName("ironman");

        chaosBosses.put("piratecaptain",
                new CringleBoss("§dDeranged Pirate Captain", CringleBoss.bossType.MAGIC_ELITE, EntityType.VINDICATOR, ig,
                        new SlimefunItemStack("CRINGLE_PIRATE_CAPTAIN", Material.PLAYER_HEAD,
                                "&dDeranged Pirate Captain",
                                "",
                                "&7Summons The Deranged Pirate Captain",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                new ItemStack(Material.BUNDLE),        SlimefunItems.WATER_RUNE,                            new ItemStack(Material.BUNDLE),
                                SlimefunItems.FISH_JERKY,                  Souls.SOUL_OF_MADNESS,                               SlimefunItems.FISH_JERKY,
                                new ItemStack(Material.BUNDLE),        SlimefunItems.WATER_RUNE,                                new ItemStack(Material.BUNDLE) }
                ));
        chaosBosses.get("piratecaptain").setMagicInternalName("piratecaptain");

        chaosBosses.put("madscientist",
                new CringleBoss("§dMad Scientist", CringleBoss.bossType.MAGIC_ELITE, EntityType.PIGLIN, ig,
                        new SlimefunItemStack("CRINGLE_MAD_SCIENTIST", Material.PLAYER_HEAD,
                                "&dThe Mad Scientist",
                                "",
                                "&7Summons The Mad Scientist",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                new ItemStack(Material.ROTTEN_FLESH),        controller.createItem("magichat"),          new ItemStack(Material.TOTEM_OF_UNDYING),
                                controller.createItem("talisman"),       Souls.SOUL_OF_MADNESS,                     controller.createItem("talisman"),
                                new ItemStack(Material.TOTEM_OF_UNDYING),        SlimefunItems.TALISMAN_WIZARD,                                new ItemStack(Material.ROTTEN_FLESH) }
                ));
        chaosBosses.get("madscientist").setMagicInternalName("madscientist");


        chaosBosses.put("royalpiglinwizard",
                new CringleBoss("§5Royal §ePiglin Wizard", CringleBoss.bossType.MAGIC_ELITE, EntityType.PIGLIN, ig,
                        new SlimefunItemStack("CRINGLE_ROYAL_PIGLIN_WIZARD", Material.PIGLIN_SPAWN_EGG,
                                "&5Royal Piglin Wizard",
                                "",
                                "&7Summons the Royal Piglin Wizard",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                SlimefunItems.STRANGE_NETHER_GOO,        controller.createItem("PiggyWand"),                              SlimefunItems.STRANGE_NETHER_GOO,
                                controller.createItem("talisman"),                  Souls.SOUL_OF_MADNESS,                     controller.createItem("talisman"),
                                SlimefunItems.STRANGE_NETHER_GOO,        SlimefunItems.TALISMAN_WIZARD,                                SlimefunItems.STRANGE_NETHER_GOO }
                ));
        chaosBosses.get("royalpiglinwizard").setMagicInternalName("royalpiglinwizard");


        chaosBosses.put("spectreofchaos",
                new CringleBoss("§5Spectre of Chaos", CringleBoss.bossType.MAGIC_ELITE, EntityType.ENDERMAN, ig,
                     new SlimefunItemStack("CRINGLE_SPECTRE_OF_CHAOS", Material.ENDERMAN_SPAWN_EGG,
                                "&5Spectre of Chaos",
                                "",
                                "&7Summons the Spectre of Chaos",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                    new ItemStack[]{
                                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL),
                                SlimefunItems.ENDER_LUMP_3,                  Souls.SOUL_OF_MADNESS,                     SlimefunItems.ENDER_LUMP_3,
                                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL) }
        ));
        chaosBosses.get("spectreofchaos").setMagicInternalName("spectreofchaos");

        for (Map.Entry<String, CringleBoss> bossEntry : chaosBosses.entrySet()) {
            bossEntry.getValue().register(cb);
            chaosResearch.addItems(bossEntry.getValue());
        }

        SlimefunItemStack chaosBell = new SlimefunItemStack("CRINGLE_CHAOS_BELL", Material.BELL,
                "&4Bell of Chaos",
                "",
                "&fDisorients and shows nearby players",
                "",
                "&7Grand bells do not effect the ringer");

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

        Bell bell = new Bell("&4The Bell of Chaos", potionEffects, ig, chaosBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        chaosResearch.addItems(bell);
        chaosResearch.register();


    }
}
