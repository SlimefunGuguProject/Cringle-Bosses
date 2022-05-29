package me.crashcringle.cringlebosses.chaos;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.CringleBoss;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.prime.PrimordialBell;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Chaos {

    public static void setup(CringleBosses cb, ItemGroup ig, Research chaosResearch) {

        SlimefunItemStack spectreOfChaos = new SlimefunItemStack("CRINGLE_SPECTRE_OF_CHAOS", Material.ENDERMAN_SPAWN_EGG,
                "&5Spectre of Chaos",
                "",
                "&7Summons the Spectre of Chaos",
                "",
                "&cMust be used at the appropriate Summoning Altar");

        ItemStack[] spectreRecipe = {
                new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL),
                null,                                   SlimefunItems.SIFTED_ORE,    null,
                new ItemStack(Material.BONE_MEAL),        null,                               new ItemStack(Material.BONE_MEAL) };

        CringleBoss spectre = new CringleBoss(ig, spectreOfChaos, RecipeType.ENHANCED_CRAFTING_TABLE, spectreRecipe);
        spectre.register(cb);


        chaosResearch.addItems(spectre);
        chaosResearch.register();


    }
}
