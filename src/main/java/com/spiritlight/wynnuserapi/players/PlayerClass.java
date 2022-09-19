package com.spiritlight.wynnuserapi.players;

import java.util.List;
import java.util.Map;

/**
 * An object representing a player's class info.
 */
public class PlayerClass {
    private boolean hardcore;
    private boolean ironman;
    private boolean craftsman;
    private boolean hunted;
    private int level;
    private int totalLevel;
    private int mobsKilled;
    private int discoveries;
    private int loginCount;
    private int deathCount;
    private int playTime;
    private long blocksWalked;
    private String name;
    private Map<String, Integer> stats;
    private List<String> completedQuests;

    public boolean isHardcore() {
        return hardcore;
    }

    public boolean isIronman() {
        return ironman;
    }

    public boolean isCraftsman() {
        return craftsman;
    }

    public boolean isHunted() {
        return hunted;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public int getMobsKilled() {
        return mobsKilled;
    }

    public int getDiscoveries() {
        return discoveries;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int getPlayTime() {
        return playTime;
    }

    public long getBlocksWalked() {
        return blocksWalked;
    }

    public String getName() {
        return name;
    }

    /**
     * It's recommended that one uses {@link com.spiritlight.wynnuserapi.utils.Dungeon} and {@link com.spiritlight.wynnuserapi.utils.Raid} for sake of compatibility
     * @return The stat map of this class.
     */
    public Map<String, Integer> getStats() {
        return stats;
    }

    public List<String> getCompletedQuests() {
        return completedQuests;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public void setIronman(boolean ironman) {
        this.ironman = ironman;
    }

    public void setCraftsman(boolean craftsman) {
        this.craftsman = craftsman;
    }

    public void setHunted(boolean hunted) {
        this.hunted = hunted;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTotalLevel(int totalLevel) {
        this.totalLevel = totalLevel;
    }

    public void setMobsKilled(int mobsKilled) {
        this.mobsKilled = mobsKilled;
    }

    public void setDiscoveries(int discoveries) {
        this.discoveries = discoveries;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void setBlocksWalked(long blocksWalked) {
        this.blocksWalked = blocksWalked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public void setCompletedQuests(List<String> completedQuests) {
        this.completedQuests = completedQuests;
    }
}
