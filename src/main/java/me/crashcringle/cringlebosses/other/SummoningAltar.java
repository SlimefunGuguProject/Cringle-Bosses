package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import me.crashcringle.cringlebosses.CringleBosses;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummoningAltar extends SlimefunItem {
    /**
     * This number represents a delay in ticks between two ritual steps.
     * The whole ritual process consists of 36 steps, an item is consumed every 4 steps (8 times)
     * and the output is spawned after the 36th step completes.
     */
    private static final int DEFAULT_STEP_DELAY = 12;

    private final List<SummoningAltarRecipe> recipes = new ArrayList<>();

    private final ItemSetting<Integer> stepDelay = new IntRangeSetting(this, "step-delay", 0, DEFAULT_STEP_DELAY, Integer.MAX_VALUE);

    public static final RecipeType SUMMONING_ALTAR = new RecipeType(new NamespacedKey(CringleBosses.inst(), "CRINGLE_SUMMONING_ALTAR"), CringleBosses.SUMMONING_ALTAR, (recipe, output) -> {
        SummoningAltarRecipe altarRecipe = new SummoningAltarRecipe(Arrays.asList(recipe), output);
        SummoningAltar altar = ((SummoningAltar) CringleBosses.SUMMONING_ALTAR.getItem());
        altar.getRecipes().add(altarRecipe);
    });
    @ParametersAreNonnullByDefault
    public SummoningAltar(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(stepDelay);
    }

    @Override
    public void preRegister() {

    }
    /**
     * This returns the delay of this {@link SummoningAltar}.
     * This number determines how many ticks happen in between a step in the ritual animation.
     * The default is {@value #DEFAULT_STEP_DELAY} ticks.
     *
     * @return The delay between two ritual steps of this {@link SummoningAltar}
     */
    public int getStepDelay() {
        return stepDelay.getValue();
    }

    @Nonnull
    public List<SummoningAltarRecipe> getRecipes() {
        return recipes;
    }
}
