package me.crashcringle.cringlebosses.other;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.crashcringle.cringlebosses.CringleBoss;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Setup;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

public class SummoningAltarTask implements Runnable {
    private final SummoningAltarListener listener;
    private final SummoningPedestal pedestalItem = (SummoningPedestal) Setup.SUMMONING_PEDESTAL.getItem();
    private final Block altar;
    private final int stepDelay;
    private final Location spawnLocation;
    private final CringleBoss mob;
    private final List<Block> pedestals;
    private final List<ItemStack> items;

    private final Collection<Location> particleLocations = new LinkedList<>();
    private final Map<Item, Location> positionLock = new HashMap<>();

    private boolean running;
    private int stage;
    private final Player player;

    @ParametersAreNonnullByDefault
    public SummoningAltarTask(SummoningAltarListener listener, Block altar, int stepDelay, CringleBoss mob, List<Block> pedestals, List<ItemStack> items, Player player) {
        this.listener = listener;
        this.spawnLocation = altar.getLocation().add(0.5, 1.3, 0.5);
        this.stepDelay = stepDelay;
        this.altar = altar;
        this.mob = mob;
        this.pedestals = pedestals;
        this.items = items;
        this.player = player;

        this.running = true;
        this.stage = 0;

        for (Block pedestal : pedestals) {
            Optional<Item> item = pedestalItem.getPlacedItem(pedestal);

            if (item.isPresent()) {
                Item entity = item.get();
                positionLock.put(entity, entity.getLocation().clone());
            }
        }
    }

    @Override
    public void run() {
        idle();

        if (!checkLockedItems()) {
            abort();
            return;
        }

        if (this.stage == 36) {
            finish();
            return;
        }

        if (this.stage > 0 && this.stage % 4 == 0) {
            RespawnAnchor anchor = ((RespawnAnchor) pedestals.get(this.stage / 4 - 1).getBlockData());
            anchor.setCharges(4);
            pedestals.get(this.stage / 4 - 1).setBlockData(anchor);
            checkPedestal(pedestals.get(this.stage / 4 - 1));
        }

        this.stage += 1;
        CringleBosses.runSync(this, stepDelay);
    }

    private boolean checkLockedItems() {
        for (Map.Entry<Item, Location> entry : positionLock.entrySet()) {
            if (entry.getKey().getLocation().distanceSquared(entry.getValue()) > 0.1) {
                return false;
            }
        }

        return true;
    }

    private void idle() {
        spawnLocation.getWorld().spawnParticle(Particle.SOUL, spawnLocation, 16, 1.2F, 0F, 1.2F);
        spawnLocation.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, spawnLocation, 8, 0.2F, 0F, 0.2F);

        for (Location loc : particleLocations) {
            spawnLocation.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, loc, 16, 0.3F, 0.2F, 0.3F);
            spawnLocation.getWorld().spawnParticle(Particle.CRIT_MAGIC, loc, 8, 0.3F, 0.2F, 0.3F);
        }
    }

    private void checkPedestal(@Nonnull Block pedestal) {
        Optional<Item> item = pedestalItem.getPlacedItem(pedestal);

        if (!item.isPresent() || positionLock.remove(item.get()) == null) {
            abort();
        } else {
            Item entity = item.get();
            particleLocations.add(pedestal.getLocation().add(0.5, 1.5, 0.5));
            items.add(pedestalItem.getOriginalItemStack(entity));
            pedestal.getWorld().playSound(pedestal.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1F, 1.8F);

            spawnLocation.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, pedestal.getLocation().add(0.5, 1.5, 0.5), 16, 0.3F, 0.2F, 0.3F);
            spawnLocation.getWorld().spawnParticle(Particle.SOUL, pedestal.getLocation().add(0.5, 1.5, 0.5), 8, 0.3F, 0.2F, 0.3F);

            positionLock.remove(entity);
            entity.remove();
            entity.removeMetadata("no_pickup", CringleBosses.inst());
        }
    }

    private void abort() {
        running = false;

        for (Block b : pedestals) {
            listener.getAltarsInUse().remove(b.getLocation());
            RespawnAnchor anchor = ((RespawnAnchor) b.getBlockData());
            anchor.setCharges(0);
            b.setBlockData(anchor);
        }

        // This should re-enable altar blocks on craft failure.
        listener.getAltarsInUse().remove(altar.getLocation());
        spawnLocation.getWorld().playSound(spawnLocation, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1F, 1F);
        positionLock.clear();
        listener.getAltars().remove(altar);
    }

    private void finish() {
        if (running) {

            SummoningAltarSpawnEvent event = new SummoningAltarSpawnEvent(mob, altar, player);
            Bukkit.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                spawnLocation.getWorld().playSound(spawnLocation, Sound.ENTITY_WITHER_SPAWN, 1F, 1.8F);
                spawnLocation.getWorld().playEffect(spawnLocation, Effect.STEP_SOUND, Material.EMERALD_BLOCK);
                spawnLocation.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, altar.getLocation().add(0.5, 1.5, 0.5), 8, 0.3F, 0.2F, 0.3F);
                event.getMob().setUpMob(spawnLocation);
            }

            for (Block b : pedestals) {
                RespawnAnchor anchor = ((RespawnAnchor) b.getBlockData());
                anchor.setCharges(0);
                b.setBlockData(anchor);
                listener.getAltarsInUse().remove(b.getLocation());
            }

            // This should re-enable altar blocks on craft completion.
            listener.getAltarsInUse().remove(altar.getLocation());
            listener.getAltars().remove(altar);
        } else {
            spawnLocation.getWorld().playSound(spawnLocation, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1F, 1F);
        }
    }
}