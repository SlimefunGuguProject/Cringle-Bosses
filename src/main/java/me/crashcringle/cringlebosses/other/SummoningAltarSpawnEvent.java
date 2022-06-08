package me.crashcringle.cringlebosses.other;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import me.crashcringle.cringlebosses.CringleBoss;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
/**
 * This {@link Event} is fired before a mob is spawned by an {@link SummoningAltar}.
 * Cancelling this event will make the {@link SummoningAltar} spawn no mob after the recipe is finished.
 *
 * @author Tweep
 * @author CrashCringle
 *
 * @see SummoningAltar
 * @see SummoningAltarTask
 * @see SummoningAltarListener
 */
public class SummoningAltarSpawnEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private CringleBoss mob;
    private boolean cancelled;

    /**
     * @param mob
     *            The {@link CringleBoss} that would be dropped by the ritual
     * @param block
     *            The altar {@link Block}
     * @param player
     *            The {@link Player} that started the ritual.
     */
    @ParametersAreNonnullByDefault
    public SummoningAltarSpawnEvent(CringleBoss mob, Block block, Player player) {
        super(player);

        this.block = block;
        this.mob = mob;
    }

    /**
     * This method returns the main altar's block {@link Block}
     *
     * @return the main altar's block {@link Block}
     */
    @Nonnull
    public Block getAltarBlock() {
        return block;
    }

    /**
     * This method returns the {@link CringleBoss} that would be spawned by the {@link SummoningAltar }
     *
     * @return the {@link CringleBoss} that would be dropped by the {@link SummoningAltar}
     */
    @Nonnull
    public CringleBoss getMob() {
        return mob;
    }

    /**
     * This method will change the mob that would be spawned by the {@link SummoningAltar}
     *
     * @param mob
     *            being the {@link CringleBoss} you want to change the item to.
     */
    public void setMob(CringleBoss mob) {
        if (mob == null) {
            throw new IllegalArgumentException("A Summoning Altar cannot spawn 'null' mobs");
        }

        this.mob = mob;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Nonnull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }

}