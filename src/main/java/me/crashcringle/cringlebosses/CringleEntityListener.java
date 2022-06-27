package me.crashcringle.cringlebosses;

import com.magmaguy.elitemobs.MetadataHandler;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.crashcringle.cringlebosses.CringleBosses;
import me.crashcringle.cringlebosses.Hounds;
import me.crashcringle.cringlebosses.corrupt.Corrupt;
import me.crashcringle.cringlebosses.holy.Holy;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import me.crashcringle.cringlebosses.other.SummoningPedestal;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.logging.Level;

public class CringleEntityListener implements Listener  {
    private static final String DEMON_ENTITY = "DemonEntity";
    private static final String ANGEL_ENTITY = "AngelEntity";

    @ParametersAreNonnullByDefault
    public CringleEntityListener(CringleBosses plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void mobSpawnEvent(EntitySpawnEvent event) {
        if (event.getEntity().getCustomName() == null)
            return;
        if (event.getEntity().getCustomName().contains("§eAngel") || event.getEntity().getName().contains("§eAngel")) {
            CringleBoss.tag((LivingEntity) event.getEntity(), ANGEL_ENTITY, event.getEntityType().toString());
            event.getEntity().setInvulnerable(true);
            CringleBosses.inst().getLogger().log(Level.INFO, "Angel Spawned");
        }
        if (event.getEntity().getCustomName().contains("§4Demon") || event.getEntity().getName().contains("§4Demon")) {
            CringleBosses.inst().getLogger().log(Level.INFO, "Demon Spawned");
            CringleBoss.tag((LivingEntity) event.getEntity(), DEMON_ENTITY, event.getEntityType().toString());
        }
    }
    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent event) {
        if (!isDemonEntity(event.getEntity()) && !isAngelEntity(event.getEntity()))
            return;
        if ((event.getDamager() instanceof Player) && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            Entity entity = event.getEntity();
            Player player = (Player) event.getDamager();
            // Demon Blade
            if (SlimefunUtils.isItemSimilar(player.getInventory().getItemInMainHand(), Corrupt.DEMON_BLADE, false)) {
                if (isDemonEntity(entity)) {
                    if (entity.isInvulnerable())
                        entity.setInvulnerable(false);
                    event.setDamage(event.getDamage());
                    CringleBosses.inst().getLogger().log(Level.INFO, "Demon with Blade");

                }
            }
            //Angel Blade
            if (SlimefunUtils.isItemSimilar(player.getInventory().getItemInMainHand(), Holy.ANGEL_BLADE, true)) {
                if (isDemonEntity(entity)) {
                    if (entity.isInvulnerable())
                        entity.setInvulnerable(false);
                    event.setDamage(event.getDamage() * 2);
                    CringleBosses.inst().getLogger().log(Level.INFO, "Demon with Angel Blade");
                }
                if (isAngelEntity(entity)) {
                    if (entity.isInvulnerable())
                        entity.setInvulnerable(false);
                    event.setDamage(event.getDamage());
                    CringleBosses.inst().getLogger().log(Level.INFO, "Angel with Angel Blade");

                }
            }
        } else {
            event.setCancelled(true);
        }
    }
    public static boolean isDemonEntity(Entity entity) {
        if (entity == null) return false;
        return entity.getPersistentDataContainer().has(new NamespacedKey(MetadataHandler.PLUGIN, DEMON_ENTITY), PersistentDataType.STRING);
    }
    public static boolean isAngelEntity(Entity entity) {
        if (entity == null) return false;
        return entity.getPersistentDataContainer().has(new NamespacedKey(MetadataHandler.PLUGIN, ANGEL_ENTITY), PersistentDataType.STRING);
    }

}