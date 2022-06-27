package me.crashcringle.cringlebosses.holy;

import com.elmakers.mine.bukkit.api.magic.MageController;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
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

public class Holy {

    public static SlimefunItemStack ANGEL_BLADE = new SlimefunItemStack("CRINGLE_ANGEL_BLADE", CringleBosses.magicAPI.getController().createItem("angelBlade"),
            "&bAngel Blade",
            "",
            "&eMighty blade from the skys of heaven",
            "",
            "&bCapable of slaying Angels and Demons");


    public static void setup(CringleBosses cb, ItemGroup ig, Research research) {
        MageController controller = CringleBosses.magicAPI.getController();
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


        ANGEL_BLADE.getItemMeta().setCustomModelData(1736501);
        SlimefunItem angelBlade = new SlimefunItem(ig, ANGEL_BLADE, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        null,              Souls.PURIFIED_SOUL,       null,
                        controller.createItem("SoulIngot"),  Souls.PURIFIED_SOUL,        controller.createItem("SoulIngot"),
                        null,              new ItemStack(Material.GOLDEN_SWORD),        null});
        angelBlade.register(cb);
        Bell bell = new Bell("&eHoly Bell", potionEffects, ig, holyBell, RecipeType.ANCIENT_ALTAR, recipe);
        bell.register(cb);

        Map<String, CringleBoss> holyBosses = new HashMap<>();

        holyBosses.put("holycrash",
                new CringleBoss("§e§lHoly Crash", CringleBoss.bossType.MAGIC_ELITE, CringleBoss.Species.ANGEL, 200, EntityType.POLAR_BEAR, ig,
                        new SlimefunItemStack("CRINGLE_HOLY_CRASH", Objects.requireNonNull(controller.createItem("HolyCrashHead")),
                                "&6&lHoly Crash",
                                "",
                                "&7Summons the ruler of the divine",
                                "&e&lHoly Crash",
                                "",
                                "&cMust be used at the appropriate Summoning Altar"),
                        new ItemStack[]{
                                Souls.PURIFIED_SOUL,        Souls.PURIFIED_SOUL,        Souls.PURIFIED_SOUL,
                                Souls.PURIFIED_SOUL,        controller.createItem("AncientTome"),  Souls.PURIFIED_SOUL,
                                Souls.PURIFIED_SOUL,        Souls.PURIFIED_SOUL,    Souls.PURIFIED_SOUL }
                ));
        holyBosses.get("holycrash").setMagicInternalName("holycrash");

        for (Map.Entry<String, CringleBoss> bossEntry : holyBosses.entrySet()) {
            bossEntry.getValue().register(cb);
            research.addItems(bossEntry.getValue());
        }
        research.addItems(bell, angelBlade);
        research.register();
    }
}
