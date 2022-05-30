package me.crashcringle.cringlebosses.prime;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static me.crashcringle.cringlebosses.CringleBosses.SUMMONING_ALTAR;

public class Prime {

    public static void setup(CringleBosses cb, ItemGroup ig, Research primeResearch) {
        /*
         * 2. Create a new SlimefunItemStack
         * This class has many constructors, it is very important
         * that you give each item a unique id.
         */
        SlimefunItemStack primordialBell = new SlimefunItemStack("CRINGLE_PRIMORDIAL_BELL", Material.BELL,
                "&bPrimordial Bell",
                "",
                "&7Disorients and shows nearby players",
                "",
                "&3Requires Hardened Gel to activate");


        /*
         * 3. Creating a Recipe
         * The Recipe is an ItemStack Array with a length of 9.
         * It represents a Shaped Recipe in a 3x3 crafting grid.
         * The machine in which this recipe is crafted in is specified
         * further down as the RecipeType.
         */
        ItemStack[] recipe = {
                new ItemStack(Material.BONE_MEAL),        new ItemStack(Material.BONE_MEAL),  new ItemStack(Material.BONE_MEAL),
                new ItemStack(Material.BONE_MEAL),        SlimefunItems.SIFTED_ORE,    new ItemStack(Material.BONE_MEAL),
                new ItemStack(Material.BONE_MEAL),        new ItemStack(Material.BONE_MEAL),        new ItemStack(Material.BONE_MEAL) };

        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from Slimefun itself will automatically add the recipe to that machine.
         */
        PrimordialBell bell = new PrimordialBell(ig, primordialBell, SummoningAltar.SUMMONING_ALTAR, recipe);
        bell.register(cb);

        primeResearch.addItems(bell);
        primeResearch.register();
    }
}
