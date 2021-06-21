package com.auqkwa.staff.shared;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractStaffUser {

    private final UUID uuid;
    private String displayName;
    private String rank;
    private boolean fired;
    private long lastLogin;
    private long databasePlayTime;

    public AbstractStaffUser(UUID uuid) {
        this.uuid = uuid;
    }

    public abstract boolean isOnline();

    /**
     * Method called when the user logs out
     */
    public void logout() {
    }

    /**
     * Method called when the user logs in
     */
    public void login() {
    }

    /**
     * @return the user's uuid
     */
    @NotNull
    public final UUID getUUID() {
        return this.uuid;
    }

    /**
     * @return the user's display name (ign)
     */
    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the user's staff rank
     */
    @NotNull
    public final String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @return the most recent login in minutes (epoch time)
     */
    public long getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return the database's play time in minutes
     */
    public long getDatabasePlayTime() {
        return this.databasePlayTime;
    }

    public void setDatabasePlayTime(long databasePlayTime) {
        this.databasePlayTime = databasePlayTime;
    }

    /**
     * @return whether or not the user is fired
     */
    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
        //TODO: Add more business logic, possibly implement luckperms and discord hook
    }

    /**
     * @return calculated playtime in minutes
     */
    public long getPlayTime() {
        long lastLogin = getLastLogin();
        long databasePlayTime = getDatabasePlayTime();
        long currentTime = System.currentTimeMillis() / 60000;
        if (isOnline()) {
            return databasePlayTime + (currentTime - Math.max(databasePlayTime, lastLogin));
        } else {
            return databasePlayTime;
        }
    }
}
