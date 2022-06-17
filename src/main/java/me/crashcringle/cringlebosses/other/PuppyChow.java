package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Hounds;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PuppyChow extends SlimefunItem {

    List<PotionEffect> effects;


    Hounds.houndType type;
    public PuppyChow(List<PotionEffect> potionEffects, Hounds.houndType type, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.type = type;
        this.effects = potionEffects;
    }



    @Override
    public void preRegister() {
        EntityInteractHandler itemuseHandler = this::onItemUse;
        addItemHandler(itemuseHandler);

    }

    private void onItemUse(PlayerInteractEntityEvent event, ItemStack itemStack, boolean b) {
        if (event.getRightClicked() instanceof Wolf) {
            Location location = event.getPlayer().getLocation();
            String name;
            Wolf wolf = (Wolf) event.getRightClicked();
            if (!Hounds.hounds.contains(wolf.getEntityId())) {
                CringleBosses.inst().getLogger().log(Level.INFO, "Registering a new Hound...");
                location.getWorld().playSound(location, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 100, (float) 1);
                switch(type) {
                    case HELLHOUND:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_GROWL, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName().length() > 1)
                            wolf.setCustomName("§4" +wolf.getCustomName());
                        else
                            wolf.setCustomName("§4Hellhound");
                        break;
                    case OREO:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_WHINE, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName().length() > 1) {
                            name = wolf.getCustomName();
                            name = "§f§l" + name.substring(0,name.length()/2) + "§0§l" +  name.substring(name.length()/2);
                            wolf.setCustomName(name);
                        } else {
                            wolf.setCustomName("§f§lOr§0§leo");
                        }
                        break;
                    case ANGEL_PUP:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_HOWL, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName().length() > 1)
                            wolf.setCustomName("§e" +wolf.getCustomName());
                        else
                            wolf.setCustomName("§eAngelPup");
                        break;
                }
                wolf.addPotionEffects(effects);
                Hounds.playerHounds.computeIfAbsent(event.getPlayer().getName(), k -> new ArrayList<>()).add(wolf.getEntityId());
                Hounds.playerHounds.computeIfPresent(event.getPlayer().getName(), (key, val) -> val).add(wolf.getEntityId());
                CringleBosses.inst().getLogger().log(Level.INFO, event.getPlayer().getName() + " has " + Hounds.playerHounds.get(event.getPlayer().getName()).size() + " hounds!");
            }
        }
    }



}
