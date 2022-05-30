package me.crashcringle.cringlebosses.other;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
/**
 * This {@link Event} is fired before an item is dropped by an {@link SummoningAltar}.
 * Cancelling this event will make the {@link SummoningAltar} drop no item after the recipe is finished.
 *
 * @author Tweep
 * @author CrashCringle
 *
 * @see SummoningAltar
 * @see SummoningAltarTask
 * @see SummoningAltarListener
 */
public class SummoningAltarCraftEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private ItemStack output;
    private boolean cancelled;

    /**
     * @param output
     *            The {@link ItemStack} that would be dropped by the ritual
     * @param block
     *            The altar {@link Block}
     * @param player
     *            The {@link Player} that started the ritual.
     */
    @ParametersAreNonnullByDefault
    public SummoningAltarCraftEvent(ItemStack output, Block block, Player player) {
        super(player);

        this.block = block;
        this.output = output;
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
     * This method returns the {@link ItemStack} that would be dropped by the {@link SummoningAltar }
     *
     * @return the {@link ItemStack} that would be dropped by the {@link SummoningAltar}
     */
    @Nonnull
    public ItemStack getItem() {
        return output;
    }

    /**
     * This method will change the item that would be dropped by the {@link SummoningAltar}
     *
     * @param output
     *            being the {@link ItemStack} you want to change the item to.
     */
    public void setItem(ItemStack output) {
        if (output == null || output.getType() == Material.AIR) {
            throw new IllegalArgumentException("A Summoning Altar cannot drop 'null' items");
        }

        this.output = output;
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