package com.auqkwa.staff.bungeecord;

import com.auqkwa.staff.bungeecord.config.StaffConfig;
import com.mongodb.MongoClient;
import net.md_5.bungee.api.plugin.Plugin;

public final class StaffPlugin extends Plugin {

    private static StaffPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        StaffConfig.Mongo mongo = StaffConfig.getInstance().getMongo();
        MongoClient mongoClient = mongo.connect();


    }

    @Override
    public void onDisable() {
        StaffConfig.getInstance().getMongo().close();
        instance = null;
    }

    public static StaffPlugin getInstance() {
        return instance;
    }
}
