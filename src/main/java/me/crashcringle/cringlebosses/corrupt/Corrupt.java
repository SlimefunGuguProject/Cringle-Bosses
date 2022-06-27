package me.crashcringle.cringlebosses.corrupt;
import com.elmakers.mine.bukkit.api.magic.MageController;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.crashcringle.cringlebosses.CringleBoss;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.other.Bell;
import me.crashcringle.cringlebosses.other.Souls;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Corrupt {

    public static SlimefunItemStack DEMON_BLADE = new SlimefunItemStack("CRINGLE_DEMON_BLADE", CringleBosses.magicAPI.getController().createItem("demonBlade"),
            "§4Demon Blade",
            "",
            "&cCorrupted blade forged by man",
            "",
            "&bCapable of slaying Demons");
    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {
        MageController controller = CringleBosses.magicAPI.getController();

        SlimefunItemStack spectreOfCorruption = new SlimefunItemStack("CRINGLE_SPECTRE_OF_CORRUPTION", Material.ENDERMAN_SPAWN_EGG,
                "&5Spectre of Corruption",
                "",
                "&7Summons the Spectre of Corruption",
                "",
                "&cMust be used at the appropriate Summoning Altar");

        ItemStack[] spectre2Recipe = {
                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL),
                SlimefunItems.ENDER_LUMP_3,                  Souls.CRACKED_SOUL,                     SlimefunItems.ENDER_LUMP_3,
                new ItemStack(Material.END_CRYSTAL),        SlimefunItems.ENDER_RUNE,                               new ItemStack(Material.END_CRYSTAL) };
        CringleBoss spectre2 = new CringleBoss(spectreOfCorruption.getDisplayName(), CringleBoss.bossType.MAGIC_ELITE,  EntityType.ENDERMAN, 100, ig, spectreOfCorruption, spectre2Recipe);
        spectre2.setMagicInternalName("spectreofchaos");
        spectre2.register(cb);



        SlimefunItemStack corruptBell = new SlimefunItemStack("CRINGLE_CORRUPT_BELL", Material.BELL,
                "&5Twisted &4Bell",
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
        DEMON_BLADE.getItemMeta().setCustomModelData(173651);
        SlimefunItem demonBlade = new SlimefunItem(ig, DEMON_BLADE, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        null,              Souls.CRACKED_SOUL,       null,
                        controller.createItem("SoulIngot"),  Souls.CRACKED_SOUL,        controller.createItem("SoulIngot"),
                        null,              new ItemStack(Material.STONE_SWORD),        null});
        demonBlade.setRecipeOutput(controller.createItem("demonBlade"));
        demonBlade.register(cb);
        Bell bell = new Bell("&5Twisted &4Bell", potionEffects, ig, corruptBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);
        Map<String, CringleBoss> corruptBosses = new HashMap<>();

        corruptBosses.put("corruptcrash",
                new CringleBoss("§5§lCorrupt §4§lCrash", CringleBoss.bossType.MAGIC_ELITE, CringleBoss.Species.ANGEL, 200, EntityType.POLAR_BEAR, ig,
                        new SlimefunItemStack("CRINGLE_CORRUPT_CRASH", Objects.requireNonNull(controller.createItem("CorruptCrashHead")),
                                "&4&lCorr&5upt &4Cr&5ash",
                                "",
                                "&7Summons the root of corruption",
                                "&4Corr&5upt &4Cra&5sh",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                            Souls.CRACKED_SOUL,        Souls.GLISTENING_SOUL,        Souls.CRACKED_SOUL,
                                Souls.GLISTENING_SOUL,        controller.createItem("AncientTome"),  Souls.GLISTENING_SOUL,
                                Souls.CRACKED_SOUL,        Souls.GLISTENING_SOUL,    Souls.CRACKED_SOUL }
                ));
        corruptBosses.get("corruptcrash").setMagicInternalName("corruptcrash");

        for (Map.Entry<String, CringleBoss> bossEntry : corruptBosses.entrySet()) {
            bossEntry.getValue().register(cb);
            research.addItems(bossEntry.getValue());
        }
        research.addItems(bell, demonBlade);
        research.register();
    }
}
