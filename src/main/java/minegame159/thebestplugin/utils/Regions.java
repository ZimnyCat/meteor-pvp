package minegame159.thebestplugin.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import minegame159.thebestplugin.duels.Duels;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Regions {
    public static ProtectedRegion KITCREATOR;

    public static ProtectedRegion OW_SPAWN;
    public static ProtectedRegion OW_PVP;

    public static void onEnable() {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager OW = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        RegionManager NETHER = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world_nether")));

        KITCREATOR = OW.getRegion("kitcreator");

        OW_SPAWN = OW.getRegion("spawn");
        OW_PVP = OW.getRegion("pvp");
    }

    public static boolean isIn(ProtectedRegion region, Entity entity) {
        Location pos = entity.getLocation();
        return region.contains(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }

    public static boolean isInAnyPvp(Player player) {
        if (isIn(Regions.OW_PVP, player)) return true;

        return Duels.INSTANCE.getDuel(player) != null;
    }

    public static Region toWERegion(ProtectedRegion region) {
        return new CuboidRegion(region.getMinimumPoint(), region.getMaximumPoint());
    }
}
