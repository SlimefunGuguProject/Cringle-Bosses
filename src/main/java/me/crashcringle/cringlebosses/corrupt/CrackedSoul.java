package me.crashcringle.cringlebosses.corrupt;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Tag;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.PiglinBarterDrop;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.magical.runes.VillagerRune;

/**
 * This {@link SlimefunItem} can only be obtained via bartering with a {@link Piglin}
 *
 * @author Crash Cringle
 *
 * @see PiglinBarterDrop
 *
 */
public class CrackedSoul extends SimpleSlimefunItem<ItemUseHandler> implements PiglinBarterDrop {

    private final ItemSetting<Integer> chance = new IntRangeSetting(this, "barter-chance", 0, 5, 20);

    @ParametersAreNonnullByDefault
    public CrackedSoul(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(chance);
        addItemHandler(onRightClickEntity());
    }

    @Override
    public int getBarteringLootChance() {
        return chance.getValue();
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> block = e.getClickedBlock();

            if (block.isPresent() && Tag.SIGNS.isTagged(block.get().getType())) {
                e.cancel();
            }
        };
    }

    private ItemUseHandler onRightClickEntity() {
        return (e) -> {
            if (e.getClickedBlock().isPresent()) {
                e.getClickedBlock().get().setBiome(Biome.BASALT_DELTAS);
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    ItemUtils.consumeItem(e.getItem(), false);
                }
            }
        };
    }
}