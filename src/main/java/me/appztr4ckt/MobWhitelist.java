package me.appztr4ckt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MobWhitelist extends JavaPlugin {

    private static final List<EntityType> nonMobEntities = Arrays.asList(
            EntityType.ITEM,
            EntityType.PLAYER,
            EntityType.ARROW,
            EntityType.SNOWBALL,
            EntityType.FIREBALL,
            EntityType.SMALL_FIREBALL,
            EntityType.TRIDENT,
            EntityType.EGG,
            EntityType.ENDER_PEARL,
            EntityType.POTION,
            EntityType.EXPERIENCE_ORB
    );

    @Override
    public void onEnable() {
        saveDefaultConfig();

        validateConfig();

        int interval = getConfig().getInt("check-interval", 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                removeUnlistedEntities();
            }
        }.runTaskTimer(this, 0, interval);
    }

    @Override
    public void onDisable() {
    }

    private void validateConfig() {
        List<String> blacklist = getConfig().getStringList("blacklist");

        for (String entityName : blacklist) {
            EntityType entityType;
            try {
                entityType = EntityType.valueOf(entityName);
            } catch (IllegalArgumentException e) {
                getLogger().warning("Invalid EntityType in blacklist: " + entityName);
                continue;
            }

            if (nonMobEntities.contains(entityType)) {
                getLogger().warning("WARNING: " + entityName + " is not a mob entity and will not be filtered.");
            }
        }
    }

    private void removeUnlistedEntities() {
        Bukkit.getWorlds().forEach(world -> {
            List<Entity> entities = new ArrayList<>(world.getEntities());
            entities.forEach(entity -> {
                if (!isWhitelisted(entity)) {
                    if (!(entity instanceof Player)) {
                        entity.remove();
                    } else {
                        getLogger().warning("Attempted to remove a player, which is not supported.");
                    }
                }
            });
        });
    }

    private boolean isWhitelisted(Entity entity) {
        EntityType type = entity.getType();
        String typeName = type.name();

        if (nonMobEntities.contains(type)) {
            if (type == EntityType.ITEM && !getConfig().getBoolean("remove-dropped-items", false)) {
                return true;
            }
            if (type == EntityType.PLAYER && !getConfig().getBoolean("remove-players", false)) {
                return true;
            }
            if (type == EntityType.ARROW && !getConfig().getBoolean("remove-arrows", false)) {
                return true;
            }
            if (type == EntityType.FIREBALL && !getConfig().getBoolean("remove-fireballs", false)) {
                return true;
            }
            if (type == EntityType.EXPERIENCE_ORB && !getConfig().getBoolean("remove-experience-orbs", false)) {
                return true;
            }
            if (type == EntityType.TRIDENT && !getConfig().getBoolean("remove-tridents", false)) {
                return true;
            }
        }

        boolean turnBlacklistToWhitelist = getConfig().getBoolean("turn-blacklist-to-whitelist", false);
        List<String> list = getConfig().getStringList(turnBlacklistToWhitelist ? "whitelist" : "blacklist");

        if (turnBlacklistToWhitelist) {
            return list.contains(typeName);
        } else {
            return !list.contains(typeName);
        }
    }
}
