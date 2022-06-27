package me.crashcringle.cringlebosses.prime;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Hounds;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ControlRod extends SlimefunItem {
    public ControlRod(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemuseHandler = this::useControlRod;
        addItemHandler(itemuseHandler);

    }

    private void useControlRod(PlayerRightClickEvent event) {
        if (Hounds.getPlayerHounds().containsKey(event.getPlayer().getName())) {
            List<Entity> nearbyEntities = event.getPlayer().getNearbyEntities(30, 30, 30);
            event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.NEUTRAL, 100, (float) 0.5);
            for (Entity entity : nearbyEntities) {
                if (entity instanceof Wolf) {
                    Wolf wolf = (Wolf) entity;
                    if (Hounds.getPlayerHounds().get(event.getPlayer().getName()).contains(entity.getEntityId())) {
                        CringleBosses.runSync(() -> ((Wolf) entity).setSitting(!wolf.isSitting()), 1L);
                    }
                }
            }
        }
    }

}
