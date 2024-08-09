package me.appztr4ckt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class MobWhitelist extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new BukkitRunnable() {
            @Override
            public void run() {
                removeUnlistedEntities();
            }
        }.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
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
        return getConfig().getStringList("whitelist").contains(typeName);
    }
}
