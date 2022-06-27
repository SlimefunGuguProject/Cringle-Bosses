package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Hounds;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class PuppyChow extends SlimefunItem {

    private List<PotionEffect> effects;


    private Hounds.houndType type = Hounds.houndType.ANGEL_PUP;
    public PuppyChow(List<PotionEffect> potionEffects, Hounds.houndType type, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.setType(type);
        this.setEffects(potionEffects);
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
            if (!Hounds.getHounds().contains(wolf.getEntityId())) {
                location.getWorld().playSound(location, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 100, (float) 1);
                switch(getType()) {
                    case HELLHOUND:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_GROWL, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName() != null)
                            wolf.setCustomName("§4" +wolf.getCustomName());
                        else
                            wolf.setCustomName("§4Hellhound");
                        wolf.setCollarColor(DyeColor.RED);
                        break;
                    case OREO:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_WHINE, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName() != null) {
                            name = wolf.getCustomName();
                            name = "§f§l" + name.substring(0,name.length()/2) + "§0§l" +  name.substring(name.length()/2);
                            wolf.setCustomName(name);
                        } else {
                            wolf.setCustomName("§f§lOr§0§leo");
                        }
                        wolf.setCollarColor(DyeColor.BLACK);
                        break;
                    case ANGEL_PUP:
                        location.getWorld().playSound(location, Sound.ENTITY_WOLF_HOWL, SoundCategory.NEUTRAL, 100, (float) 2);
                        if (wolf.getCustomName() != null)
                            wolf.setCustomName("§e" +wolf.getCustomName());
                        else
                            wolf.setCustomName("§eAngelPup");
                        wolf.setCollarColor(DyeColor.YELLOW);
                        break;
                }
                wolf.setCustomNameVisible(true);
                wolf.addPotionEffects(getEffects());
                CringleBosses.inst().getLogger().log(Level.INFO, "Registering a new Hound...");
                Objects.requireNonNull(Hounds.getPlayerHounds().computeIfPresent(event.getPlayer().getName(), (key, val) -> val)).add(wolf.getEntityId());
                Hounds.getPlayerHounds().computeIfAbsent(event.getPlayer().getName(), k -> new ArrayList<>()).add(wolf.getEntityId());
                CringleBosses.inst().getLogger().log(Level.INFO, event.getPlayer().getName() + " has " + Hounds.getPlayerHounds().get(event.getPlayer().getName()).size() + " hounds!");
            }
        }
    }


    public List<PotionEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<PotionEffect> effects) {
        this.effects = effects;
    }

    public Hounds.houndType getType() {
        return type;
    }

    public void setType(Hounds.houndType type) {
        this.type = type;
    }
}
