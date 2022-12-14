package com.spiritlight.wynnuserapi.players;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * A mostly complete Player object representing a player's data.
 */
public class Player {
    private Rank rank;
    private UUID uuid;
    private String username;
    private Date firstJoin;
    private Date lastJoin;
    private int totalDiscoveries;
    private int mobsKilled;
    private long blocksWalked;
    private List<PlayerClass> playerClasses;
    private JsonObject json;
    private String world;
    private boolean isOnline;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Player() {
    }

    public Player(Player player) {
        this.rank = player.rank;
        this.uuid = player.uuid;
        this.username = player.username;
        this.firstJoin = player.firstJoin;
        this.lastJoin = player.lastJoin;
        this.totalDiscoveries = player.totalDiscoveries;
        this.playerClasses = player.playerClasses;
        this.json = player.json;
        this.blocksWalked = player.blocksWalked;
        this.mobsKilled = player.mobsKilled;
        this.isOnline = player.isOnline;
        this.world = player.world;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFirstJoin() {
        return firstJoin;
    }

    public void setFirstJoin(Date firstJoin) {
        this.firstJoin = firstJoin;
    }

    public Date getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(Date lastJoin) {
        this.lastJoin = lastJoin;
    }

    public int getTotalDiscoveries() {
        return totalDiscoveries;
    }

    public void setTotalDiscoveries(int totalDiscoveries) {
        this.totalDiscoveries = totalDiscoveries;
    }

    public int getMobsKilled() {
        return mobsKilled;
    }

    public void setMobsKilled(int mobsKilled) {
        this.mobsKilled = mobsKilled;
    }

    public long getBlocksWalked() {
        return blocksWalked;
    }

    public void setBlocksWalked(long blocksWalked) {
        this.blocksWalked = blocksWalked;
    }

    public List<PlayerClass> getPlayerClasses() {
        return playerClasses;
    }

    public void setPlayerClasses(List<PlayerClass> playerClasses) {
        this.playerClasses = playerClasses;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return totalDiscoveries == player.totalDiscoveries && mobsKilled == player.mobsKilled && blocksWalked == player.blocksWalked && isOnline == player.isOnline && rank == player.rank && uuid.equals(player.uuid) && username.equals(player.username) && firstJoin.equals(player.firstJoin) && lastJoin.equals(player.lastJoin) && playerClasses.equals(player.playerClasses) && json.equals(player.json) && world.equals(player.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, uuid, username, firstJoin, lastJoin, totalDiscoveries, mobsKilled, blocksWalked, playerClasses, json, world, isOnline);
    }

    @Override
    public String toString() {
        return "Player{" +
                "rank=" + rank +
                ", uuid=" + uuid +
                ", username='" + username + '\'' +
                ", firstJoin=" + firstJoin +
                ", lastJoin=" + lastJoin +
                ", totalDiscoveries=" + totalDiscoveries +
                ", mobsKilled=" + mobsKilled +
                ", blocksWalked=" + blocksWalked +
                ", playerClasses=" + playerClasses +
                ", json=" + json +
                ", world='" + world + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }
}
