package com.auqkwa.staff.bungeecord.config;

import com.auqkwa.staff.bungeecord.StaffPlugin;
import com.mongodb.MongoClient;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class StaffConfig {
    private static StaffConfig instance;

    private final Configuration config;

    private final Mongo mongo;

    private StaffConfig() {
        File pluginDataFolder = StaffPlugin.getInstance().getDataFolder();
        File file = new File(pluginDataFolder, "config.yml");

        if (!pluginDataFolder.exists())
            pluginDataFolder.mkdir();

        if (!file.exists()) {
            try (InputStream in = StaffPlugin.getInstance().getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration", e);
        }

        this.mongo = new Mongo(config.getString("mongo.connection-string"));
    }

    private void save() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(StaffPlugin.getInstance().getDataFolder(), "config.yml"));
    }

    public static StaffConfig getInstance() {
        return instance == null ? (instance = new StaffConfig()) : instance;
    }

    public Mongo getMongo() {
        return mongo;
    }

    public static final class Mongo {

        // Pool
        private MongoClient mongoClient;

        // Config
        private final String connectionString;

        Mongo(String connectionString) {
            this.connectionString = connectionString;
        }

        /**
         * @return connected mongoclient
         */
        public MongoClient connect() {
            return this.mongoClient = new MongoClient(this.connectionString);
        }

        /**
         * Closes the MongoClient connection
         */
        public void close() {
            this.mongoClient.close();
        }

        public String getConnectionString() {
            return connectionString;
        }

        public MongoClient getMongoClient() {
            return mongoClient;
        }
    }
}