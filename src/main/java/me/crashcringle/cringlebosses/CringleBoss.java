package me.crashcringle.cringlebosses;

import com.magmaguy.elitemobs.ChatColorConverter;
import com.magmaguy.elitemobs.config.custombosses.CustomBossesConfig;
import com.magmaguy.elitemobs.config.custombosses.CustomBossesConfigFields;
import com.magmaguy.elitemobs.mobconstructor.EliteEntity;
import com.magmaguy.elitemobs.mobconstructor.SuperMobConstructor;
import com.magmaguy.elitemobs.mobconstructor.custombosses.CustomBossEntity;
import com.magmaguy.elitemobs.mobconstructor.mobdata.aggressivemobs.EliteMobProperties;
import com.magmaguy.elitemobs.powers.meta.ElitePower;
import com.magmaguy.elitemobs.utils.PlayerScanner;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.crashcringle.cringlebosses.other.SummoningAltar;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import static com.magmaguy.elitemobs.mobspawning.NaturalMobSpawnEventHandler.getNaturalMobLevel;


public class CringleBoss extends SlimefunItem {

    private List<ItemStack> drops;

    private String name;

    private EntityType entityType;

    public enum bossType {
        MAGIC,
        ELITE,
        VANILLA,
        BOTH
    }

    private bossType type;

    private int mobLevel;

//        Bell bell = new Bell("&bPrimordial Bell", potionEffects, ig, primordialBell, RecipeType.ANCIENT_ALTAR, recipe);
    public CringleBoss(String name, bossType type, EntityType entityType, List<ItemStack> drops, ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe) {
        super(itemGroup, item, SummoningAltar.SUMMONING_ALTAR, recipe);
        this.name = name;
        this.drops = drops;
        this.type = type;
        this.entityType = entityType;
    }
    public CringleBoss(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void setUpMob(Location location) {
        this.mobLevel = getNaturalMobLevel(location, PlayerScanner.getNearbyPlayers(location));
        spawnEliteEntityType(name, location, entityType, mobLevel, Optional.empty());
    }

    public static void spawnCustomBossCommand(String fileName, Location location) {
        CustomBossesConfigFields customBossesConfigFields = CustomBossesConfig.getCustomBoss(fileName);
        if (customBossesConfigFields == null) {
            CringleBosses.inst().getLogger().log(Level.SEVERE, "Filename " + fileName + " is not valid! Make sure you are writing the name of a configuration file in the custombosses folder!");
            return;
        }
        CustomBossEntity customBossEntity = new CustomBossEntity(customBossesConfigFields);
        customBossEntity.setSpawnLocation(location);
        customBossEntity.spawn(false);
    }

    private static void spawnSuperMob(EntityType entityType, Location location) {
        SuperMobConstructor.constructSuperMob((LivingEntity) location.getWorld().spawnEntity(location, entityType));
    }
    public static void spawnEliteEntityType(Location location,
                                                   EntityType entityType,
                                                   Integer level,
                                                   Optional<String[]> powers) {
        if (!EliteMobProperties.getValidMobTypes().contains(entityType)) {
            CringleBosses.inst().getLogger().log(Level.SEVERE, ChatColorConverter.convert("&8[EliteMobs] &4Entity type " + entityType.toString() + " can't be an Elite!"));
            return;
        }
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        HashSet<ElitePower> mobPowers = new HashSet<>();
        if (powers.isPresent())
            mobPowers = getPowers(powers.get());
        EliteEntity eliteEntity = new EliteEntity();
        eliteEntity.setLevel(level);
        eliteEntity.setLivingEntity(livingEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        if (!mobPowers.isEmpty()) eliteEntity.applyPowers(mobPowers);
        else eliteEntity.randomizePowers(EliteMobProperties.getPluginData(livingEntity));
    }

    public static void spawnEliteEntityType(String name, Location location,
                                            EntityType entityType,
                                            Integer level,
                                            Optional<String[]> powers) {
        if (!EliteMobProperties.getValidMobTypes().contains(entityType)) {
            CringleBosses.inst().getLogger().log(Level.SEVERE, ChatColorConverter.convert("&8[EliteMobs] &4Entity type " + entityType.toString() + " can't be an Elite!"));
            return;
        }
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        HashSet<ElitePower> mobPowers = new HashSet<>();
        if (powers.isPresent())
            mobPowers = getPowers(powers.get());
        EliteEntity eliteEntity = new EliteEntity();
        eliteEntity.setLevel(level);
        eliteEntity.setLivingEntity(livingEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        eliteEntity.setName(name + " Level "+level, true);
        if (!mobPowers.isEmpty()) eliteEntity.applyPowers(mobPowers);
        else eliteEntity.randomizePowers(EliteMobProperties.getPluginData(livingEntity));
    }
    private static HashSet<ElitePower> getPowers(String[] mobPowers) {

        HashSet<ElitePower> elitePowers = new HashSet<>();

        if (mobPowers.length > 0)
            for (String string : mobPowers) {
                ElitePower elitePower = ElitePower.getElitePower(string);
                if (elitePower == null) {
                    CringleBosses.inst().getLogger().log(Level.WARNING,"[EliteMobs] Power " + string + " is not a valid power! Valid powers:");
                    StringBuilder allPowers = new StringBuilder();
                    for (ElitePower iteratedPower : ElitePower.getElitePowers())
                        allPowers.append(iteratedPower.getName()).append(", ");
                    allPowers.append("custom");
                    CringleBosses.inst().getLogger().log(Level.INFO,allPowers.toString());
                    return new HashSet<>();
                }
                elitePowers.add(elitePower);
            }
        return elitePowers;
    }

    @Override
    public void preRegister() {

    }
    public List<ItemStack> getDrops() {
    return drops;
}

    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
}
