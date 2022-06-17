package me.crashcringle.cringlebosses;

import com.elmakers.mine.bukkit.api.entity.EntityData;
import com.elmakers.mine.bukkit.api.magic.MageController;
import com.elmakers.mine.bukkit.utility.CompatibilityLib;
import com.elmakers.mine.bukkit.utility.ConfigurationUtils;
import com.google.gson.stream.JsonReader;
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
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

import static com.magmaguy.elitemobs.mobspawning.NaturalMobSpawnEventHandler.getNaturalMobLevel;
import static me.crashcringle.cringlebosses.CringleBosses.getGson;


public class CringleBoss extends SlimefunItem {

    private List<ItemStack> drops;

    private String name;

    private String magicInternalName;

    private String eliteInternalName;
    private EntityType entityType;

    public String getMagicInternalName() {
        return magicInternalName;
    }

    public void setMagicInternalName(String magicInternalName) {
        this.magicInternalName = magicInternalName;
    }

    public String getEliteInternalName() {
        return eliteInternalName;
    }

    public void setEliteInternalName(String eliteInternalName) {
        this.eliteInternalName = eliteInternalName;
    }

    public bossType getType() {
        return type;
    }

    public void setType(bossType type) {
        this.type = type;
    }

    public boolean isNaturalLevel() {
        return isNaturalLevel;
    }

    public void setNaturalLevel(boolean naturalLevel) {
        isNaturalLevel = naturalLevel;
    }

    public int getMobLevel() {
        return mobLevel;
    }

    public void setMobLevel(int mobLevel) {
        this.mobLevel = mobLevel;
    }

    public enum bossType {
        MAGIC,
        ELITE,

        ELITEBOSS,
        VANILLA,
        MAGIC_ELITE,
        MAGIC_ELITEBOSS,
    }

    private bossType type;

    private boolean isNaturalLevel;
    private int mobLevel;

//        Bell bell = new Bell("&bPrimordial Bell", potionEffects, ig, primordialBell, RecipeType.ANCIENT_ALTAR, recipe);
    public CringleBoss(String name, bossType type, EntityType entityType, ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe) {
        super(itemGroup, item, SummoningAltar.SUMMONING_ALTAR, recipe);
        this.setName(name);
        this.setDrops(getDrops());
        this.setType(type);
        this.setEntityType(entityType);
        this.setNaturalLevel(true);
    }

    public CringleBoss(String name, bossType type, EntityType entityType, int mobLevel, ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe) {
        super(itemGroup, item, SummoningAltar.SUMMONING_ALTAR, recipe);
        this.setName(name);
        this.setDrops(getDrops());
        this.setType(type);
        this.setEntityType(entityType);
        this.setNaturalLevel(false);
        this.setMobLevel(mobLevel);
    }

    public CringleBoss(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void setUpMob(Location location) {
        this.setMobLevel(isNaturalLevel() ? getNaturalMobLevel(location, PlayerScanner.getNearbyPlayers(location)) : this.getMobLevel());
        LivingEntity mob;
        switch(getType()) {
            case MAGIC_ELITE:
                mob = (LivingEntity) spawnMagicMob(getMagicInternalName(),location);
                if (mob != null) {
                    makeEntityElite(getName(), location, mob.getType(), getMobLevel(), (LivingEntity) mob, Optional.empty());
                    mob.setCustomName(name + " §rLvl§2 "+mobLevel);
                }
                break;
            case ELITE:
                spawnEliteEntityType(location, getEntityType(), getMobLevel(), Optional.empty());
                break;
            case ELITEBOSS:
                if (!getEliteInternalName().isEmpty()) {
                    mob = spawnCustomBoss(getEliteInternalName(), location);
                }
                break;
            case MAGIC:
                mob = (LivingEntity) spawnMagicMob(getMagicInternalName(),location);
                break;
            case VANILLA:
                mob = (LivingEntity) location.getWorld().spawnEntity(location, getEntityType());
                mob.setCustomName(getName());
            break;

        }
    }

    public static Entity spawnMagicMob(String mob, Location targetLocation) {
        MageController controller = CringleBosses.magicAPI.getController();
        Entity spawned = null;
        EntityData entityData = null;
        String mobKey = mob;
        int count = 1;
        int jsonStart = mobKey.indexOf('{');
        if (jsonStart > 0) {
            String fullKey = mobKey;
            mobKey = fullKey.substring(0, jsonStart);
            String json = fullKey.substring(jsonStart);
            try {
                JsonReader reader = new JsonReader(new StringReader(json));
                reader.setLenient(true);
                Map<String, Object> tags = getGson().fromJson(reader, Map.class);
                CompatibilityLib.getInventoryUtils().convertIntegers(tags);
                ConfigurationSection mobConfig = ConfigurationUtils.newConfigurationSection();
                mobConfig.set("type", mobKey);
                for (Map.Entry<String, Object> entry : tags.entrySet()) {
                    mobConfig.set(entry.getKey(), entry.getValue());
                }
                entityData = controller.getMob(mobConfig);
            } catch (Throwable ex) {
                controller.getLogger().warning("[Magic] Error parsing mob json: " + json + " : " + ex.getMessage());
            }
        }
        if (entityData == null) {
            entityData = controller.getMob(mobKey);
        }
        if (entityData == null) {
            CringleBosses.inst().getLogger().log(Level.WARNING,  ChatColor.RED + "Unknown mob type " + ChatColor.YELLOW + mobKey);
            return spawned;
        }
        if (entityData.isNPC()) {
            CringleBosses.inst().getLogger().log(Level.WARNING,  ChatColor.YELLOW + "Mob type " + ChatColor.GOLD + mobKey + ChatColor.YELLOW + " is meant to be an NPC");
            CringleBosses.inst().getLogger().log(Level.WARNING,  "  Spawning as a normal mob, use " + ChatColor.AQUA + "/mnpc add " + mobKey + ChatColor.WHITE + " to create as an NPC");
        }
        try {
            for (int i = 0; i < count; i++) {
                spawned = entityData.spawn(targetLocation);
            }
        } catch (Exception ex) {
            CringleBosses.inst().getLogger().log(Level.WARNING,  ChatColor.RED + "Failed to spawn mob of type " + ChatColor.YELLOW + mobKey + ChatColor.RED + ", an unexpected exception occurred, please check logs");
            controller.getLogger().log(Level.SEVERE, "Error spawning mob " + mobKey, ex);
            return spawned;
        }

        if (spawned == null) {
            CringleBosses.inst().getLogger().log(Level.WARNING,  ChatColor.RED + "Failed to spawn mob of type " + ChatColor.YELLOW + mobKey);
            return spawned;
        }

        //name = controller.getEntityDisplayName(spawned);
        CringleBosses.inst().getLogger().log(Level.WARNING,  ChatColor.AQUA + "Spawned mob: " + ChatColor.LIGHT_PURPLE + spawned.getCustomName());
        return spawned;
    }

    public static LivingEntity spawnCustomBoss(String fileName, Location location) {
        CustomBossesConfigFields customBossesConfigFields = CustomBossesConfig.getCustomBoss(fileName);
        if (customBossesConfigFields == null) {
            CringleBosses.inst().getLogger().log(Level.SEVERE, "Filename " + fileName + " is not valid! Make sure you are writing the name of a configuration file in the custombosses folder!");
            return null;
        }
        CustomBossEntity customBossEntity = new CustomBossEntity(customBossesConfigFields);
        customBossEntity.setSpawnLocation(location);
        customBossEntity.spawn(false);
        return customBossEntity.getLivingEntity();
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
        eliteEntity.setName(name + " §rLvl §2"+level, true);
        if (!mobPowers.isEmpty()) eliteEntity.applyPowers(mobPowers);
        else eliteEntity.randomizePowers(EliteMobProperties.getPluginData(livingEntity));
    }
    public static void makeEntityElite(String name, Location location,
                                            EntityType entityType,
                                            Integer level,
                                            LivingEntity livingEntity,
                                            Optional<String[]> powers) {
        if (!EliteMobProperties.getValidMobTypes().contains(entityType)) {
            CringleBosses.inst().getLogger().log(Level.SEVERE, ChatColorConverter.convert("&8[EliteMobs] &4Entity type " + entityType.toString() + " can't be an Elite!"));
            return;
        }
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
