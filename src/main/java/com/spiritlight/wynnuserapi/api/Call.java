package com.spiritlight.wynnuserapi.api;

import com.google.gson.*;
import com.spiritlight.wynnuserapi.connections.HttpHandler;
import com.spiritlight.wynnuserapi.players.Player;
import com.spiritlight.wynnuserapi.players.PlayerClass;
import com.spiritlight.wynnuserapi.utils.Converter;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.*;

public class Call {
    public static final String PLAYER_STAT = "https://api.wynncraft.com/v2/player/$PLAYER$/stats";

    /**
     * Utility method to fetch the player data<br>
     * If using UUID, it must be formatted first (That is, with dashes)<br>
     * @param username The username or UUID of player to fetch
     * @return A {@link Player} object containing this player's info
     */
    public static @Nullable Player getPlayer(String username) {
        final Player ret = new Player();
        final List<PlayerClass> classes = new ArrayList<>();
        String parse = HttpHandler.get(PLAYER_STAT.replace("$PLAYER$", username)).getBody();
        JsonObject jsonObject = new Gson().fromJson(parse, JsonObject.class);
        if(jsonObject == null) return null;
        JsonObject dataEntry = jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject metaEntry = dataEntry.getAsJsonObject("meta");
        JsonObject locationEntry = metaEntry.get("location").getAsJsonObject();
        JsonObject globalEntry = dataEntry.getAsJsonObject("global");
        JsonArray classEntry = dataEntry.get("classes").getAsJsonArray();
        ret.setJson(jsonObject.deepCopy());
        // Player object
        ret.setUsername(dataEntry.get("username").getAsString());
        ret.setUuid(UUID.fromString(dataEntry.get("uuid").getAsString()));
        ret.setRank(Converter.getRankFromString(dataEntry.get("rank").getAsString()));
        ret.setFirstJoin(Date.from(Instant.parse(metaEntry.get("firstJoin").getAsString())));
        ret.setLastJoin(Date.from(Instant.parse(metaEntry.get("lastJoin").getAsString())));
        ret.setOnline(locationEntry.get("online").getAsBoolean());
        JsonElement j = locationEntry.get("server");
        ret.setWorld(j instanceof JsonNull ? "Offline" : j.getAsString());
        ret.setBlocksWalked(globalEntry.get("blocksWalked").getAsLong());
        ret.setMobsKilled(globalEntry.get("mobsKilled").getAsInt());
        ret.setTotalDiscoveries(globalEntry.get("discoveries").getAsInt());
        // Classes
        for(JsonElement jsonElement : classEntry) {
            JsonObject classBase = jsonElement.getAsJsonObject();
            final PlayerClass pc = new PlayerClass();
            final Map<String,Integer> completionMap = new HashMap<>();
            pc.setName(classBase.get("name").getAsString());
            pc.setTotalLevel(classBase.get("level").getAsInt());
            pc.setBlocksWalked(classBase.get("blocksWalked").getAsLong());
            pc.setMobsKilled(classBase.get("mobsKilled").getAsInt());
            pc.setLoginCount(classBase.get("logins").getAsInt());
            pc.setDeathCount(classBase.get("deaths").getAsInt());
            pc.setPlayTime(classBase.get("playtime").getAsInt());
            pc.setDiscoveries(classBase.get("discoveries").getAsInt());
            JsonArray dungeonCompletions = classBase.getAsJsonObject("dungeons").getAsJsonArray("list");
            for(JsonElement element : dungeonCompletions) {
                JsonObject o = element.getAsJsonObject();
                completionMap.put(o.get("name").getAsString(), o.get("completed").getAsInt());
            }
            JsonArray raidCompletions = classBase.getAsJsonObject("raids").getAsJsonArray("list");
            for(JsonElement element : raidCompletions) {
                JsonObject o = element.getAsJsonObject();
                completionMap.put(o.get("name").getAsString(), o.get("completed").getAsInt());
            }
            pc.setStats(completionMap);
            classes.add(pc);
        }
        ret.setPlayerClasses(classes);
        return ret;
    }
}
