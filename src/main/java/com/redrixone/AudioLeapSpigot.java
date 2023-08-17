package com.redrixone;

import com.redrixone.events.JoinEvent;
import com.redrixone.storage.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.bukkit.Bukkit.getPluginManager;

public class AudioLeapSpigot extends JavaPlugin {

    private DataManager dataManager;
    private Connection connection;
    private PreparedStatement statement;

    public void onEnable() {
        databaseConnection();
        getPluginManager().registerEvents(new JoinEvent(), this);
        getLogger().info("AudioLeapSpigot has been enabled!");
    }

    public void onDisable() {
        getLogger().info("AudioLeapSpigot has been disabled!");
    }

    public void databaseConnection() {
        String username = "yourdb";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/audioleap";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement("SELECT * FROM audioleap WHERE uuid = ?");
            dataManager = new DataManager(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
