package com.redrixone.tasks;

import com.redrixone.storage.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.xml.crypto.Data;
import java.util.UUID;

public class Playsound extends BukkitRunnable {

    Player player = Bukkit.getServer().getOnlinePlayers().stream().findFirst().orElse(null);
    UUID playerUUID = player.getUniqueId();
    Sound sound = Sound.valueOf(DataManager.getSound(playerUUID));
    int volume = DataManager.getVolume(playerUUID);
    int pitch = DataManager.getPitch(playerUUID);


    @Override
    public void run() {
        if (DataManager.getPlaysoundRequest(playerUUID, true)) {
            player.playSound(player.getLocation(), sound, volume, pitch);
            DataManager.setPlaysoundRequest(playerUUID, false);
        }
    }
}
