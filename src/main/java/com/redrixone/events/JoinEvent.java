package com.redrixone.events;

import com.redrixone.storage.DataManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.xml.crypto.Data;
import java.util.UUID;

public class JoinEvent implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        boolean request = DataManager.getRequest(playerUUID);
        Location location = new Location(player.getWorld(), DataManager.getX(playerUUID), DataManager.getY(playerUUID), DataManager.getZ(playerUUID));
        if (request == true) {
            player.teleport(location);
            DataManager.setRequest(playerUUID, false);
        }
    }

}
