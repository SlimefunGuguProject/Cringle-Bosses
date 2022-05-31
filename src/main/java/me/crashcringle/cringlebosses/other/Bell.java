package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.WitherProof;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Bell extends SlimefunItem implements WitherProof {

    String name;
    List<PotionEffect> effects;
    public Bell(String bellName, List<PotionEffect> potionEffects, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.name = bellName;
        this.effects = potionEffects;
    }

    @Override
    public void onAttack(Block block, Wither wither) {
        Location location = wither.getLocation();
        location.getWorld().playSound(location, Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.NEUTRAL, 100, (float) 1.5);

        //Give the effect to the players in the specified radius
        for (Entity e : location.getWorld().getNearbyEntities(location, 40, 40, 40)) {
            if (e instanceof Player) {
                String color = name.charAt(0) == '&' ? name.substring(0,2) : "";
                ((Player) e).sendTitle("&6A Grand Bell tolls", color + "The "+name + color + " Bell Rings for thee", 10, 50, 20);
                ((LivingEntity) e).addPotionEffects(effects);
                ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            }
        }
    }
    @Override
    public void preRegister() {
        BlockUseHandler blockuseHandler = this::onBlockRightClick;
        addItemHandler(blockuseHandler);

    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
        //This will prevent the bell from being used
        event.cancel();
        //Get the location of the player
        Location location = event.getPlayer().getLocation();
        //Play sound at the player's location
        location.getWorld().playSound(location, Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.NEUTRAL, 100, (float) 1.5);
       // event.getPlayer().sendMessage("§3§oPrime hears your call and rings the grand bell in your name...");

        //Give the effect to the players in the specified radius
        for (Entity e : location.getWorld().getNearbyEntities(location, 40, 40, 40)) {
            if (e instanceof Player && e.getEntityId() != event.getPlayer().getEntityId()) {
                String color = name.charAt(0) == '&' ? name.substring(0,2) : "";
                ((Player) e).sendTitle("&6A Grand Bell tolls", color + "The "+name + color + " Rings for thee", 10, 50, 20);
                ((LivingEntity) e).addPotionEffects(effects);
            }
        }
    }


}
